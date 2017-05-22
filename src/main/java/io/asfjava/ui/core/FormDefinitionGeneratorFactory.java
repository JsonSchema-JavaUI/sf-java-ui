package io.asfjava.ui.core;

import java.util.HashMap;

import io.asfjava.ui.core.generators.FormDefinitionGenerator;

public final class FormDefinitionGeneratorFactory {

	public FormDefinitionGenerator getGenerator(String annotationName) {
		return GENERATORS.get(annotationName);
	}

	void register(String annotationName, FormDefinitionGenerator generator) {
		GENERATORS.put(annotationName, generator);
	}

	public static FormDefinitionGeneratorFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new FormDefinitionGeneratorFactory();
		}
		return INSTANCE;
	}

	private static final HashMap<String, FormDefinitionGenerator> GENERATORS = new HashMap<>();

	private static FormDefinitionGeneratorFactory INSTANCE;

	private FormDefinitionGeneratorFactory() {
	}
}
