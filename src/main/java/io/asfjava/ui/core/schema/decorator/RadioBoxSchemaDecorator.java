package io.asfjava.ui.core.schema.decorator;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.types.StringSchema;

import io.asfjava.ui.core.form.RadioBox;

public class RadioBoxSchemaDecorator implements SchemaDecorator {

	@Override
	public void customizeSchema(BeanProperty property, JsonSchema jsonschema) {
		RadioBox annotation = property.getAnnotation(RadioBox.class);
		if (annotation != null && annotation.title() != null) {
			((StringSchema) jsonschema).setTitle(annotation.title());
		}
	}

	@Override
	public String getAnnotation() {
		return RadioBox.class.getName();
	}

}
