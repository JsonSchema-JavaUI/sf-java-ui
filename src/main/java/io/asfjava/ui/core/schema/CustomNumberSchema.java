package io.asfjava.ui.core.schema;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.types.NumberSchema;

class CustomNumberSchema extends NumberSchema {

	private final SchemaDecoratorHandler schemaDecoratorHandler;

	CustomNumberSchema(SchemaDecoratorHandler schemaDecoratorHandler) {
		this.schemaDecoratorHandler = schemaDecoratorHandler;
	}

	@Override
	public void enrichWithBeanProperty(BeanProperty beanProperty) {
		super.enrichWithBeanProperty(beanProperty);
		schemaDecoratorHandler.decorate(beanProperty, this);
	}
}
