package io.asfjava.ui.core.schema.decorators;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface SchemaDecorator {

	void customizeSchema(BeanProperty property,JsonSchema jsonschema);
	String getAnnotation();
}
