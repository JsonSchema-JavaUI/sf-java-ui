package io.asfjava.ui.core.schema;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;

import io.asfjava.ui.core.FormDefinitionGeneratorFactory;
import io.asfjava.ui.core.generators.FormDefinitionGenerator;
import io.asfjava.ui.dto.UiForm;

public final class UiFormSchemaGenerator {

	private static UiFormSchemaGenerator INSTANCE;

	public UiForm generate(Class<? extends Serializable> formDto) throws JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper, new CustomSchemaFactoryWrapper());
		JsonSchema schema = schemaGen.generateSchema(formDto);

		ArrayNode formDefinition = mapper.createArrayNode();

		Arrays.asList(formDto.getDeclaredFields()).stream()
				.forEach(field -> buildFormDefinition(mapper, formDefinition, field));

		return new UiForm(schema, formDefinition);
	}

	private void buildFormDefinition(ObjectMapper mapper, ArrayNode formDefinition, Field field) {
		List<Annotation> annoations = Arrays.asList(field.getAnnotations());
		for (Annotation annotation : annoations) {
			formDefinition.add(buildFieldDefinition(field, annotation, mapper));
		}
	}

	private JsonNode buildFieldDefinition(Field field, Annotation annotation, ObjectMapper mapper) {
		ObjectNode fieldFormDefinition = mapper.createObjectNode();
		FormDefinitionGenerator generator = FormDefinitionGeneratorFactory.getInstance()
				.getGenerator(annotation.annotationType().getName());
		generator.generate(fieldFormDefinition, field);
		return fieldFormDefinition;

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
