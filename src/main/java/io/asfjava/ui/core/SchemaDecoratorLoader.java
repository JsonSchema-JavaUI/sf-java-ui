package io.asfjava.ui.core;

import static io.asfjava.ui.core.logging.ErrorCode.ASF01;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.reflections.Reflections;

import io.asfjava.ui.core.logging.ASFUILogger;
import io.asfjava.ui.core.schema.decorators.SchemaDecorator;

final class SchemaDecoratorLoader {

	private static final List<String> PACKAGESCAN = Stream
			.of("io.asfjava.ui.core.schema.decorators", "io.asfjava.ui.addons.schema.decorators")
			.collect(Collectors.toList());
	private static Reflections reflections = new Reflections(PACKAGESCAN);

	void load() {
		for (Class<? extends SchemaDecorator> subType : reflections.getSubTypesOf(SchemaDecorator.class)) {
			register(subType);
		}
	}

	private void register(Class<? extends SchemaDecorator> subType) {
		try {
			SchemaDecorator decorator = subType.newInstance();
			SchemaDecoratorFactory.getInstance().register(decorator::getAnnotation, decorator);
		} catch (InstantiationException | IllegalAccessException e) {
			ASFUILogger.getLogger().error(ASF01, e);
		}
	}

	void unload() {
		ASFUILogger.getLogger().info("I'm unloader");
	}

	static SchemaDecoratorLoader getInstance() {
		if (instance == null)
			instance = new SchemaDecoratorLoader();
		return instance;
	}

	private static SchemaDecoratorLoader instance;

	private SchemaDecoratorLoader() {
	}
}
