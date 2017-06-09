package io.asfjava.ui.core.schema.decorator;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.types.StringSchema;

import io.asfjava.ui.core.form.TextArea;

public class TextAreaSchemaDecorator implements SchemaDecorator {

	@Override
	public void customizeSchema(BeanProperty property, JsonSchema jsonschema) {
		TextArea annotation = property.getAnnotation(TextArea.class);
		if (annotation != null && annotation.title() != null) {
			((StringSchema) jsonschema).setTitle(annotation.title());
		}
	}

	@Override
	public String getAnnotation() {
		return TextArea.class.getName();
	}

}
