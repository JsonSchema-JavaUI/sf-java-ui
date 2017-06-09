package io.asfjava.ui.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.asfjava.ui.core.generators.FormDefinitionGenerator;

public final class FormDefinitionGeneratorFactory {

	public FormDefinitionGenerator getGenerator(String annotationName) {
		return GENERATORS.get(annotationName);
	}

	void register(String annotationName, FormDefinitionGenerator generator) {
		GENERATORS.put(annotationName, generator);
	}

	public static FormDefinitionGeneratorFactory getInstance() {
		if (instance == null) {
			instance = new FormDefinitionGeneratorFactory();
		}
		return instance;
	}

	private static final Map<String, FormDefinitionGenerator> GENERATORS = new ConcurrentHashMap<>();

	private static FormDefinitionGeneratorFactory instance;

	private FormDefinitionGeneratorFactory() {
	}
}
