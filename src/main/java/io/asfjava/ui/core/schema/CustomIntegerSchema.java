package io.asfjava.ui.core.schema;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.types.IntegerSchema;

class CustomIntegerSchema extends IntegerSchema {

	private final SchemaDecoratorHandler schemaDecoratorHandler;

	CustomIntegerSchema(SchemaDecoratorHandler schemaDecoratorHandler) {
		this.schemaDecoratorHandler = schemaDecoratorHandler;
	}

	@Override
	public void enrichWithBeanProperty(BeanProperty beanProperty) {
		super.enrichWithBeanProperty(beanProperty);
		schemaDecoratorHandler.decorate(beanProperty, this);
	}
}
