package io.asfjava.ui.core.schema;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.types.NumberSchema;

class CustomNumberSchema extends NumberSchema {
	@Override
	public void enrichWithBeanProperty(BeanProperty beanProperty) {
		super.enrichWithBeanProperty(beanProperty);
		SchemaDecoratorUtil.get().decorate(beanProperty, this);
	}
}
