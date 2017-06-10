package io.asfjava.ui.schema.decorator;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.types.StringSchema;

public class NumberSchemaDecorator implements SchemaDecorator{

	@Override
	public void customizeSchema(BeanProperty property, JsonSchema jsonschema) {
		io.asfjava.ui.core.form.Number annotation = property.getAnnotation(io.asfjava.ui.core.form.Number.class);
		if (annotation != null && annotation.title() != null) {
			((StringSchema) jsonschema).setTitle(annotation.title());
		}
	}

	@Override
	public String getAnnotation() {
		return io.asfjava.ui.core.form.Number.class.getName();
	}

}
