package io.asfjava.ui.core;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import io.asfjava.ui.core.generators.FormDefinitionGenerator;

public final class FormDefinitionGeneratorFactory {

	private static FormDefinitionGeneratorFactory instance;

	public static FormDefinitionGeneratorFactory getInstance() {
		return (instance == null)
				? instance = new FormDefinitionGeneratorFactory()
				: instance;
	}

	private final Map<String, FormDefinitionGenerator> generators = new ConcurrentHashMap<>();

	private FormDefinitionGeneratorFactory() {}

	public Optional<FormDefinitionGenerator> getGenerator(String annotationName) {
		return Optional.ofNullable(generators.get(annotationName));
	}

	void register(Supplier<String> annotationName, FormDefinitionGenerator generator) {
		generators.put(annotationName.get(), generator);
	}
}
