package io.asfjava.ui.core.schema;

import java.lang.annotation.Annotation;
import java.util.Optional;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

import io.asfjava.ui.core.SchemaDecoratorFactory;
import io.asfjava.ui.core.schema.decorators.SchemaDecorator;

final class SchemaDecoratorHandler {

	SchemaDecoratorHandler() {}

	void decorate(BeanProperty beanProperty, JsonSchema simpleTypeSchema) {
		beanProperty.getMember().annotations()
				.forEach(annotation -> decorate(beanProperty, simpleTypeSchema, annotation));
	}

	private void decorate(BeanProperty beanProperty, JsonSchema simpleTypeSchema, Annotation annotation) {
		getDecorator(annotation)
				.ifPresent(decorator -> decorator.customizeSchema(beanProperty, simpleTypeSchema));
	}

	private Optional<SchemaDecorator> getDecorator(Annotation annotation) {
		return SchemaDecoratorFactory.getInstance().get(annotation.annotationType().getName());
	}
}
