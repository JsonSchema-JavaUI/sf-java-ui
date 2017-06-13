package io.asfjava.ui.core.schema.decorators;

import java.util.Optional;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.types.StringSchema;

import io.asfjava.ui.core.form.Password;

public class PasswordSchemaDecorator implements SchemaDecorator {

	@Override
	public void customizeSchema(BeanProperty property, JsonSchema jsonschema) {
		Optional.ofNullable(property.getAnnotation(Password.class)).ifPresent(annotation -> {
			if (annotation.title() != null) {
				((StringSchema) jsonschema).setTitle(annotation.title());
			}
			if (annotation.pattern() != null) {
				((StringSchema) jsonschema).setPattern(annotation.pattern());
			}
			if (annotation.minLenght() != 0) {
				((StringSchema) jsonschema).setMinLength(annotation.minLenght());
			}
			if (annotation.maxLenght() != Integer.MAX_VALUE) {
				((StringSchema) jsonschema).setMaxLength(annotation.maxLenght());
			}
		});
	}

	@Override
	public String getAnnotation() {
		return Password.class.getName();
	}

}
