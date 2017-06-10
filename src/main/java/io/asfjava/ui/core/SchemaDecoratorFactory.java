package io.asfjava.ui.core;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import io.asfjava.ui.core.schema.decorators.SchemaDecorator;

public final class SchemaDecoratorFactory {
	public Optional<SchemaDecorator> getDecorator(String annotationName) {
		return Optional.ofNullable(decorators.get(annotationName));
	}

	void register(Supplier<String> annotationName, SchemaDecorator generator) {
		decorators.put(annotationName.get(), generator);
	}

	public static SchemaDecoratorFactory getInstance() {
		if (instance == null) {
			instance = new SchemaDecoratorFactory();
		}
		return instance;
	}

	private static final Map<String, SchemaDecorator> decorators = new ConcurrentHashMap<>();

	private static SchemaDecoratorFactory instance;

	private SchemaDecoratorFactory() {
	}
}
