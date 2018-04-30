package io.asfjava.ui.core;

import io.asfjava.ui.core.schema.decorators.SchemaDecorator;

public final class SchemaDecoratorFactory extends SimpleFactory<String, SchemaDecorator> {

	private static SchemaDecoratorFactory instance;

	public static SchemaDecoratorFactory getInstance() {
		return (instance == null)
				? instance = new SchemaDecoratorFactory()
				: instance;
	}

	private SchemaDecoratorFactory() {}
}
