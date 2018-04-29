package io.asfjava.ui.core;

import io.asfjava.ui.core.generators.FormDefinitionGenerator;
import io.asfjava.ui.core.logging.ASFUILogger;
import org.reflections.Reflections;

import static io.asfjava.ui.core.logging.ErrorCode.ASF01;

final class GeneratorFactoryLoader {

	private static final String[] PACKAGES_TO_SCAN = {
			"io.asfjava.ui.core.generators",
			"io.asfjava.ui.addons.generators"
	};
	private static Reflections reflections = new Reflections(PACKAGES_TO_SCAN);
	private static GeneratorFactoryLoader instance;

	static GeneratorFactoryLoader getInstance() {
		return (instance == null)
				? instance = new GeneratorFactoryLoader()
				: instance;
	}

	private GeneratorFactoryLoader() {}

	void load() {
		reflections.getSubTypesOf(FormDefinitionGenerator.class).forEach(this::register);
	}

	void unload() {
		ASFUILogger.getLogger().info("I'm unloader");
	}

	private void register(Class<? extends FormDefinitionGenerator> subType) {
		try {
			FormDefinitionGenerator formDefinitionGenerator = subType.newInstance();
			FormDefinitionGeneratorFactory.getInstance().register(formDefinitionGenerator::getAnnotation,
					formDefinitionGenerator);
		} catch (InstantiationException | IllegalAccessException e) {
			ASFUILogger.getLogger().error(ASF01, e);
		}
	}
}
