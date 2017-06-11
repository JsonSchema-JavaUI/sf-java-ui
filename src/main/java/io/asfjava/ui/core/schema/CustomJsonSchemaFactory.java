package io.asfjava.ui.core.schema;

import com.fasterxml.jackson.module.jsonSchema.factories.JsonSchemaFactory;
import com.fasterxml.jackson.module.jsonSchema.types.IntegerSchema;
import com.fasterxml.jackson.module.jsonSchema.types.NumberSchema;
import com.fasterxml.jackson.module.jsonSchema.types.StringSchema;

class CustomJsonSchemaFactory extends JsonSchemaFactory {
	@Override
	public StringSchema stringSchema() {
		return new CustomStringSchema();
	}

	@Override
	public NumberSchema numberSchema() {
		return new CustomNumberSchema();
	}

	@Override
	public IntegerSchema integerSchema() {
		return new CustomIntegerSchema();
	}
}
