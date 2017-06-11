package io.asfjava.ui.core.schema;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.types.IntegerSchema;

class CustomIntegerSchema extends IntegerSchema {
	@Override
	public void enrichWithBeanProperty(BeanProperty beanProperty) {
		super.enrichWithBeanProperty(beanProperty);
		SchemaDecoratorUtil.get().decorate(beanProperty, this);
	}
}
