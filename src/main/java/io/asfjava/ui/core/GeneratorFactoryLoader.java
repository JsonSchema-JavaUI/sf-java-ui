package io.asfjava.ui.core;

import java.util.Set;

import org.reflections.Reflections;

import io.asfjava.ui.core.generators.FormDefinitionGenerator;

final class GeneratorFactoryLoader {
	private static final String PACKAGESCAN = "io.asfjava.ui.core.generators";
	private static Reflections reflections = new Reflections(PACKAGESCAN);

	void load() {

		Set<Class<? extends FormDefinitionGenerator>> subTypes = reflections
				.getSubTypesOf(FormDefinitionGenerator.class);
		for (Class<? extends FormDefinitionGenerator> subtype : subTypes) {
			FormDefinitionGenerator formDefinitionGenerator;
			try {
				formDefinitionGenerator = (FormDefinitionGenerator) Class.forName(subtype.getName()).newInstance();
				FormDefinitionGeneratorFactory.getInstance().register(formDefinitionGenerator.getAnnoation(),
						formDefinitionGenerator);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	void unload() {
		System.out.println("I'm unloader");
	}

	static GeneratorFactoryLoader getInstance() {
		if (INSTANCE == null)
			INSTANCE = new GeneratorFactoryLoader();
		return INSTANCE;
	}

	private static GeneratorFactoryLoader INSTANCE;

	private GeneratorFactoryLoader() {
	}
}
