package io.asfjava.ui.core;

import static io.asfjava.ui.core.logging.ErrorCode.ASF01;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.reflections.Reflections;

import io.asfjava.ui.core.generators.FormDefinitionGenerator;
import io.asfjava.ui.core.logging.ASFUILogger;

final class GeneratorFactoryLoader {
	private static final List<String> PACKAGESCAN = Stream
			.of("io.asfjava.ui.core.generators", "io.asfjava.ui.addons.generators").collect(Collectors.toList());
	private static Reflections reflections = new Reflections(PACKAGESCAN);

	void load() {
		for (Class<? extends FormDefinitionGenerator> subType : reflections
				.getSubTypesOf(FormDefinitionGenerator.class)) {
			register(subType);
		}
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
