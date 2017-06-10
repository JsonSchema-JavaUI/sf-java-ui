package io.asfjava.ui.core;

import static io.asfjava.ui.core.logging.ErrorCode.ASF01;

import org.reflections.Reflections;

import io.asfjava.ui.core.generators.FormDefinitionGenerator;
import io.asfjava.ui.core.logging.ASFUILogger;

final class GeneratorFactoryLoader {
	private static final String PACKAGESCAN = "io.asfjava.ui.core.generators";
	private static Reflections reflections = new Reflections(PACKAGESCAN);

	void load() {
		reflections.getSubTypesOf(FormDefinitionGenerator.class).stream().forEach(subtype -> register(subtype));
	}

	private void register(Class<? extends FormDefinitionGenerator> subtype) {
		try {
			FormDefinitionGenerator formDefinitionGenerator = subtype.newInstance();
			FormDefinitionGeneratorFactory.getInstance().register(formDefinitionGenerator::getAnnotation,
					formDefinitionGenerator);
		} catch (InstantiationException | IllegalAccessException e) {
			ASFUILogger.getLogger().error(ASF01, e);
		}
	}

	void unload() {
		ASFUILogger.getLogger().info("I'm unloader");
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
