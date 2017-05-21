package io.asfjava.ui.schema;

import com.fasterxml.jackson.module.jsonSchema.factories.JsonSchemaFactory;
import com.fasterxml.jackson.module.jsonSchema.types.StringSchema;

class CustomJsonSchemaFactory extends JsonSchemaFactory {
	@Override
	public StringSchema stringSchema() {
		return new CustomStringSchema();
	}
}
