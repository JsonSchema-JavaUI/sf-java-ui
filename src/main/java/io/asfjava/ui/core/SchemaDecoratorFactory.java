package io.asfjava.ui.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.asf.java.ui.schema.decorator.SchemaDecorator;

public final class SchemaDecoratorFactory {
	public SchemaDecorator getGenerator(String annotationName) {
		return GENERATORS.get(annotationName);
	}

	void register(String annotationName, SchemaDecorator generator) {
		GENERATORS.put(annotationName, generator);
	}

	public static SchemaDecoratorFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SchemaDecoratorFactory();
		}
		return INSTANCE;
	}

	private static final Map<String, SchemaDecorator> GENERATORS = new ConcurrentHashMap<>();

	private static SchemaDecoratorFactory INSTANCE;

	private SchemaDecoratorFactory() {
	}
}
