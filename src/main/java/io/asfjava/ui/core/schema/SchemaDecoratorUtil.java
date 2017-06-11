package io.asfjava.ui.core.schema;

import java.lang.annotation.Annotation;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

import io.asfjava.ui.core.SchemaDecoratorFactory;

final class SchemaDecoratorUtil {

	void decorate(BeanProperty beanProperty, JsonSchema simpleTypeSchema) {
		Iterable<Annotation> it = beanProperty.getMember().annotations();
		it.forEach(
				annotation -> SchemaDecoratorFactory.getInstance().getDecorator(annotation.annotationType().getName())
						.ifPresent(decorator -> decorator.customizeSchema(beanProperty, simpleTypeSchema)));
	}

	static SchemaDecoratorUtil get() {
		if (instance == null) {
			instance = new SchemaDecoratorUtil();
		}
		return instance;
	}

	private static SchemaDecoratorUtil instance;

	private SchemaDecoratorUtil() {
	}
}
