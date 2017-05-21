package io.asfjava.ui.schema;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;

import io.asfjava.ui.form.TextField;
import io.asfjava.ui.schema.dto.UiForm;

public final class UiFormSchemaGenerator {

	private static UiFormSchemaGenerator INSTANCE;

	public UiForm generate(Class<? extends Serializable> formDto) throws JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper, new CustomSchemaFactoryWrapper());
		JsonSchema schema = schemaGen.generateSchema(formDto);

		ArrayNode formDefinition = mapper.createArrayNode();

		Arrays.asList(formDto.getDeclaredFields()).stream().filter(field -> field.isAnnotationPresent(TextField.class))
				.forEach(field -> buildFormDefinition(mapper, formDefinition, field));

		return new UiForm(schema, formDefinition);
	}

	private void buildFormDefinition(ObjectMapper mapper, ArrayNode formDefinition, Field field) {
		ObjectNode fieldFormDefinition = mapper.createObjectNode();
		TextField annotation = field.getAnnotation(TextField.class);

		fieldFormDefinition.put("key", field.getName());
		// fieldFormDefinition.put("type", annotation.value().getLayout());

		String description = annotation.description();
		if (!description.isEmpty()) {
			fieldFormDefinition.put("description", description);
		}

		String placeHolder = annotation.placeHolder();
		if (!placeHolder.isEmpty()) {
			fieldFormDefinition.put("placeholder", placeHolder);
		}

		boolean noTitle = annotation.noTitle();
		if (noTitle) {
			fieldFormDefinition.put("notitle", noTitle);
		}

		String validationMessage = annotation.validationMessage();
		if (!validationMessage.isEmpty()) {
			fieldFormDefinition.put("validationMessage", validationMessage);
		}

		boolean readOnly = annotation.readOnly();
		if (readOnly) {
			fieldFormDefinition.put("readonly", readOnly);
		}

		formDefinition.add(fieldFormDefinition);
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
