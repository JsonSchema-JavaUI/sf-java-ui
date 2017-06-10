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
import java.util.function.Predicate;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;

import io.asfjava.ui.core.FormDefinitionGeneratorFactory;
import io.asfjava.ui.core.form.Tab;
import io.asfjava.ui.dto.UiForm;

public final class UiFormSchemaGenerator {

	private static UiFormSchemaGenerator INSTANCE;

	public UiForm generate(Class<? extends Serializable> formDto) throws JsonMappingException {
		Field[] declaredFields = formDto.getDeclaredFields();
		ObjectMapper mapper = new ObjectMapper();

		JsonSchemaGenerator schemaGen = initSchemaGen(mapper);
		JsonSchema schema = generateSchema(formDto, schemaGen);
		Map<Field, JsonNode> nodes = initFieldFormDefinition(mapper, formDto, declaredFields);

		handlerGroupedFields();

		ObjectNode tabbedFields = handleTabbedFields(mapper, declaredFields, nodes);

		ArrayNode formDefinition = mapper.createArrayNode();
		formDefinition.add(tabbedFields);

		return new UiForm(schema, formDefinition);
	}

	private void handlerGroupedFields() {
		// TODO Grouping fieldset must handle it

	}

	private ObjectNode handleTabbedFields(ObjectMapper mapper, Field[] declaredFields, Map<Field, JsonNode> nodes) {
		Predicate<? super Field> checkTabAnnotation = field -> field.isAnnotationPresent(Tab.class);
		Comparator<? super Field> tabIndexComparator = (field1, field2) -> Integer
				.compare(field1.getAnnotation(Tab.class).index(), field2.getAnnotation(Tab.class).index());

		Map<String, List<JsonNode>> groupedFieldsByTab = new LinkedHashMap<>();

		Arrays.stream(declaredFields).filter(checkTabAnnotation).sorted(tabIndexComparator)
				.forEach(field -> groupFieldsByTab(nodes, field, groupedFieldsByTab));

		ArrayNode tabs = mapper.createArrayNode();

		groupedFieldsByTab.entrySet().stream().forEachOrdered(tabElements -> {
			ObjectNode tabNode = mapper.createObjectNode();
			tabNode.put("title", tabElements.getKey());
			ArrayNode tabItems = mapper.createArrayNode();
			tabElements.getValue().stream().forEach(fieldNode -> tabItems.add(fieldNode));
			tabNode.put("items", tabItems);
			tabs.add(tabNode);
		});

		ObjectNode tabsNode = mapper.createObjectNode();
		tabsNode.put("type", "tabs");
		tabsNode.put("tabs", tabs);
		return tabsNode;

	}

	private Map<Field, JsonNode> initFieldFormDefinition(ObjectMapper mapper, Class<? extends Serializable> formDto,
			Field[] declaredFields) {
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

	public static UiFormSchemaGenerator get() {
		if (INSTANCE == null) {
			INSTANCE = new UiFormSchemaGenerator();
		}
		return INSTANCE;
	}

	private UiFormSchemaGenerator() {
	}

}
