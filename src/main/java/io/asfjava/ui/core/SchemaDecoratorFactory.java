package io.asfjava.ui.core;

import io.asfjava.ui.core.schema.decorators.SchemaDecorator;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public final class SchemaDecoratorFactory {

	private static SchemaDecoratorFactory instance;

	public static SchemaDecoratorFactory getInstance() {
		return (instance == null)
				? instance = new SchemaDecoratorFactory()
				: instance;
	}

	private final Map<String, SchemaDecorator> decorators = new ConcurrentHashMap<>();

	private SchemaDecoratorFactory() {}

	public Optional<SchemaDecorator> getDecorator(String annotationName) {
		return Optional.ofNullable(decorators.get(annotationName));
	}

	void register(Supplier<String> annotationName, SchemaDecorator generator) {
		decorators.put(annotationName.get(), generator);
	}
}
