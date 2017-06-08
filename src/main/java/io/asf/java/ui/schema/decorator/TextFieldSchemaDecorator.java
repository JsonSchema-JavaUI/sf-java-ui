package io.asf.java.ui.schema.decorator;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.types.StringSchema;

import io.asfjava.ui.core.form.TextField;

public class TextFieldSchemaDecorator implements SchemaDecorator{

	@Override
	public void customizeSchema(BeanProperty property, JsonSchema jsonschema) {
		TextField annotation = property.getAnnotation(TextField.class);
		if (annotation != null) {
			if(annotation.title()!=null){
			((StringSchema) jsonschema).setTitle(annotation.title());
			}
			if(annotation.pattern()!=null)
			{
			((StringSchema) jsonschema).setPattern(annotation.pattern());
			}
		}
	}

	@Override
	public String getAnnotation() {
		return TextField.class.getName();
	}

}
