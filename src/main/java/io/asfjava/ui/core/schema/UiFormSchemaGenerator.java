package io.asfjava.ui.core.schema;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;

import io.asfjava.ui.core.FormDefinitionGeneratorFactory;
import io.asfjava.ui.core.form.Action;
import io.asfjava.ui.core.form.ActionsGroup;
import io.asfjava.ui.core.form.FieldSet;
import io.asfjava.ui.core.form.Index;
import io.asfjava.ui.core.form.Tab;
import io.asfjava.ui.dto.UiForm;

public final class UiFormSchemaGenerator {

	private static UiFormSchemaGenerator instance;

	public UiForm generate(Class<? extends Serializable> formDto) throws JsonMappingException {
		Field[] declaredFields = formDto.getDeclaredFields();
		ObjectMapper mapper = new ObjectMapper();

		JsonSchemaGenerator schemaGen = initSchemaGen(mapper);
		JsonSchema schema = generateSchema(formDto, schemaGen);

		Map<Field, JsonNode> nodes = initFieldsFormDefinition(mapper, declaredFields);

		Map<Field, JsonNode> sortedNodes = reorderFieldsBasedOnIndex(nodes);

		handlerGroupedFields(mapper, declaredFields, sortedNodes);

		Optional<ObjectNode> tabbedFields = Optional
				.ofNullable(handleTabbedFields(mapper, declaredFields, sortedNodes));

		ArrayNode formDefinition = mapper.createArrayNode();
		tabbedFields.ifPresent(formDefinition::add);
		sortedNodes.entrySet().stream().forEach(nodesElement -> formDefinition.add(nodesElement.getValue()));

		handleActionsAnnotation(mapper, formDto, formDefinition);

		return new UiForm(schema, formDefinition);
	}

	private void handleActionsAnnotation(ObjectMapper mapper, Class<? extends Serializable> formDto,
			ArrayNode formDefinition) {
		ObjectNode groupedActionsNode = mapper.createObjectNode();

		buildActions(mapper, formDto, formDefinition);
		buildGroupedActions(mapper, formDto, groupedActionsNode, formDefinition);
	}

	private void buildActions(ObjectMapper mapper, Class<? extends Serializable> formDto, ArrayNode formDefinition) {

		Action[] actionsAnnotations = formDto.getAnnotationsByType(Action.class);
		Arrays.stream(actionsAnnotations).forEach(action -> formDefinition.add(buildActionNode(mapper, action)));
	}

	private void buildGroupedActions(ObjectMapper mapper, Class<? extends Serializable> formDto, ObjectNode actionsNode,
			ArrayNode formDefinition) {
		Optional<ActionsGroup> actionsAnnotation = Optional.ofNullable(formDto.getAnnotation(ActionsGroup.class));
		actionsAnnotation.ifPresent(actions -> {
			actionsNode.put("type", "actions");
			ArrayNode items = mapper.createArrayNode();
			Arrays.stream(actions.value()).forEach(action -> {
				ObjectNode node = buildActionNode(mapper, action);
				items.add(node);
			});
			actionsNode.set("items", items);

			formDefinition.add(actionsNode);
		});
	}

	private ObjectNode buildActionNode(ObjectMapper mapper, Action action) {
		ObjectNode node = mapper.createObjectNode();
		node.put("type", action.type());
		node.put("title", action.title());
		node.put("onClick", action.onClick());
		return node;
	}

	private ObjectNode handlerGroupedFields(ObjectMapper mapper, Field[] declaredFields,
			Map<Field, JsonNode> sortedNodes) {
		Predicate<? super Field> checkFieldSetAnnotation = field -> field.isAnnotationPresent(FieldSet.class);

		Map<String, List<JsonNode>> groupedFields = new LinkedHashMap<>();

		Arrays.stream(declaredFields).filter(checkFieldSetAnnotation)
				.forEach(field -> groupFieldsByTab(sortedNodes, field, groupedFields));

		ArrayNode groups = mapper.createArrayNode();

		ObjectNode tabsNode = mapper.createObjectNode();
		tabsNode.put("type", "fieldset");
		tabsNode.set("items", groups);
		return tabsNode;

	}

	private ObjectNode handleTabbedFields(ObjectMapper mapper, Field[] declaredFields, Map<Field, JsonNode> nodes) {
		Predicate<? super Field> checkTabAnnotation = field -> field.isAnnotationPresent(Tab.class);

		Comparator<? super Field> tabIndexComparator = (field1, field2) -> Integer
				.compare(field1.getAnnotation(Tab.class).index(), field2.getAnnotation(Tab.class).index());

		Comparator<? super Field> fieldIndexComparator = (entry1, entry2) -> {
			Index field1Index = entry1.getAnnotation(Index.class);
			Index field2Index = entry2.getAnnotation(Index.class);
			return Integer.compare((field1Index != null ? field1Index.value() : Integer.MAX_VALUE),
					field2Index != null ? field2Index.value() : Integer.MAX_VALUE);
		};

		Map<String, List<JsonNode>> groupedFieldsByTab = new LinkedHashMap<>();

		Arrays.stream(declaredFields).filter(checkTabAnnotation).sorted(fieldIndexComparator).sorted(tabIndexComparator)
				.forEach(field -> groupFieldsByTab(nodes, field, groupedFieldsByTab));

		ArrayNode tabs = mapper.createArrayNode();

		groupedFieldsByTab.entrySet().stream().forEachOrdered(tabElements -> {
			ObjectNode tabNode = mapper.createObjectNode();
			tabNode.put("title", tabElements.getKey());
			ArrayNode tabItems = mapper.createArrayNode();
			tabElements.getValue().stream().forEach(tabItems::add);
			tabNode.set("items", tabItems);
			tabs.add(tabNode);
		});
		if (tabs.size() > 0) {
			ObjectNode tabsNode = mapper.createObjectNode();
			tabsNode.put("type", "tabs");
			tabsNode.set("tabs", tabs);
			return tabsNode;
		}
		return null;

	}

	private Map<Field, JsonNode> initFieldsFormDefinition(ObjectMapper mapper, Field[] declaredFields) {
		Map<Field, JsonNode> nodes = new HashMap<>();

		Arrays.stream(declaredFields).forEach(field -> buildFormDefinition(nodes, mapper, field));

		return nodes;
	}

	private JsonSchema generateSchema(Class<? extends Serializable> formDto, JsonSchemaGenerator schemaGen)
			throws JsonMappingException {
		return schemaGen.generateSchema(formDto);
	}

	private JsonSchemaGenerator initSchemaGen(ObjectMapper mapper) {
		return new JsonSchemaGenerator(mapper, new CustomSchemaFactoryWrapper());
	}

	private void groupFieldsByTab(Map<Field, JsonNode> nodes, Field field, Map<String, List<JsonNode>> groupedFields) {
		Tab tab = field.getAnnotation(Tab.class);
		List<JsonNode> fieldsGroupedByTab = groupedFields.get(tab.title());
		if (fieldsGroupedByTab == null) {
			fieldsGroupedByTab = new ArrayList<>();
			groupedFields.put(tab.title(), fieldsGroupedByTab);
		}
		fieldsGroupedByTab.add(nodes.get(field));
		nodes.remove(field);
	}

	private void buildFormDefinition(Map<Field, JsonNode> nodes, ObjectMapper mapper, Field field) {
		Arrays.stream(field.getAnnotations())
				.forEach(annotation -> buildFieldDefinition(field, annotation, mapper, nodes));
	}

	private void buildFieldDefinition(Field field, Annotation annotation, ObjectMapper mapper,
			Map<Field, JsonNode> nodes) {
		ObjectNode fieldFormDefinition = mapper.createObjectNode();
		FormDefinitionGeneratorFactory.getInstance().getGenerator(annotation.annotationType().getName())
				.ifPresent(generator -> {
					generator.generate(fieldFormDefinition, field);
					nodes.put(field, fieldFormDefinition);
				});
	}

	private Map<Field, JsonNode> reorderFieldsBasedOnIndex(Map<Field, JsonNode> nodes) {

		Comparator<? super Entry<Field, JsonNode>> tabIndexComparator = (entry1, entry2) -> {

			Index field1Index = entry1.getKey().getAnnotation(Index.class);
			Index field2Index = entry2.getKey().getAnnotation(Index.class);

			return Integer.compare((field1Index != null ? field1Index.value() : Integer.MAX_VALUE),
					field2Index != null ? field2Index.value() : Integer.MAX_VALUE);
		};

		return nodes.entrySet().stream().sorted(tabIndexComparator).collect(Collectors.toMap(Map.Entry::getKey,
				Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

	}

	public static UiFormSchemaGenerator get() {
		if (instance == null) {
			instance = new UiFormSchemaGenerator();
		}
		return instance;
	}

	private UiFormSchemaGenerator() {
	}
}
