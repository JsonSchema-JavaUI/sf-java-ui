package io.asfjava.ui.core.schema;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.types.StringSchema;

class CustomStringSchema extends StringSchema {

	private final SchemaDecoratorHandler schemaDecoratorHandler;

	CustomStringSchema(SchemaDecoratorHandler schemaDecoratorHandler) {
		this.schemaDecoratorHandler = schemaDecoratorHandler;
	}

	@Override
	public void enrichWithBeanProperty(BeanProperty beanProperty) {
		super.enrichWithBeanProperty(beanProperty);
		schemaDecoratorHandler.decorate(beanProperty, this);
	}
}
