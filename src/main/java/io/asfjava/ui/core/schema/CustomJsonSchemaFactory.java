package io.asfjava.ui.core.schema;

import com.fasterxml.jackson.module.jsonSchema.factories.JsonSchemaFactory;
import com.fasterxml.jackson.module.jsonSchema.types.IntegerSchema;
import com.fasterxml.jackson.module.jsonSchema.types.NumberSchema;
import com.fasterxml.jackson.module.jsonSchema.types.StringSchema;

class CustomJsonSchemaFactory extends JsonSchemaFactory {

	private final SchemaDecoratorHandler schemaDecoratorHandler;

	CustomJsonSchemaFactory(SchemaDecoratorHandler schemaDecoratorHandler) {
		this.schemaDecoratorHandler = schemaDecoratorHandler;
	}

	@Override
	public StringSchema stringSchema() {
		return new CustomStringSchema(schemaDecoratorHandler);
	}

	@Override
	public NumberSchema numberSchema() {
		return new CustomNumberSchema(schemaDecoratorHandler);
	}

	@Override
	public IntegerSchema integerSchema() {
		return new CustomIntegerSchema(schemaDecoratorHandler);
	}
}
