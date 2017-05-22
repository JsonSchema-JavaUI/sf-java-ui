package io.asfjava.ui.core;

import java.lang.annotation.Annotation;
import java.util.HashMap;

public final class FormDefinitionGeneratorFactory {

	public FormDefinitionGenerator getGenerator(Annotation annotation) {
		return GENERATORS.get(annotation.annotationType().getName());
	}

	void register(Annotation annotation, FormDefinitionGenerator generator) {
		GENERATORS.put(annotation.annotationType().getName(), generator);
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
