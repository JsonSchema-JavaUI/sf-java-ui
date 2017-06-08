package io.asf.java.ui.schema.decorator;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public interface SchemaDecorator {

	void customizeSchema(BeanProperty property,JsonSchema jsonschema);
	String getAnnotation();
}
