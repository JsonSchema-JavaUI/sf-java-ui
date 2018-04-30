package io.asfjava.ui.core;

import io.asfjava.ui.core.generators.FormDefinitionGenerator;

public final class FormDefinitionGeneratorFactory extends SimpleFactory<String, FormDefinitionGenerator> {

	private static FormDefinitionGeneratorFactory instance;

	public static FormDefinitionGeneratorFactory getInstance() {
		return (instance == null)
				? instance = new FormDefinitionGeneratorFactory()
				: instance;
	}

	private FormDefinitionGeneratorFactory() {}
}
