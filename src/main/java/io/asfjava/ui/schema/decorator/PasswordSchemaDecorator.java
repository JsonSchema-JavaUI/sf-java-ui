package io.asfjava.ui.schema.decorator;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.types.StringSchema;

import io.asfjava.ui.core.form.Password;

public class PasswordSchemaDecorator implements SchemaDecorator {

	@Override
	public void customizeSchema(BeanProperty property, JsonSchema jsonschema) {
		Password annotation = property.getAnnotation(Password.class);
		if (annotation != null && annotation.title() != null) {
			((StringSchema) jsonschema).setTitle(annotation.title());
		}
	}

	@Override
	public String getAnnotation() {
		return Password.class.getName();
	}

}
