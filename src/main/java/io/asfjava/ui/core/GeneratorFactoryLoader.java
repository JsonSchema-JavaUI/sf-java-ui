package io.asfjava.ui.core;

import java.util.Set;

import org.reflections.Reflections;

import io.asfjava.ui.core.generators.FormDefinitionGenerator;
import io.asfjava.ui.core.logging.ASFUILogger;
import static io.asfjava.ui.core.logging.ErrorCode.*;

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
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				ASFUILogger.getLogger().error(ASF01, e);
			}
		}
	}

	void unload() {
		System.out.println("I'm unloader");
	}

	static GeneratorFactoryLoader getInstance() {
		if (instance == null)
			instance = new GeneratorFactoryLoader();
		return instance;
	}

	private static GeneratorFactoryLoader instance;

	private GeneratorFactoryLoader() {
	}
}
