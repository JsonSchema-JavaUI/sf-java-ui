package io.asfjava.ui.core;

import io.asfjava.ui.core.logging.ASFUILogger;
import io.asfjava.ui.core.schema.decorators.SchemaDecorator;
import org.reflections.Reflections;

import static io.asfjava.ui.core.logging.ErrorCode.ASF01;

final class SchemaDecoratorLoader {

	private static final String[] PACKAGES_TO_SCAN = {
			"io.asfjava.ui.core.schema.decorators",
			"io.asfjava.ui.addons.schema.decorators"
	};
	private static Reflections reflections = new Reflections(PACKAGES_TO_SCAN);
	private static SchemaDecoratorLoader instance;

	static SchemaDecoratorLoader getInstance() {
		return (instance == null)
				? instance = new SchemaDecoratorLoader()
				: instance;
	}

	private SchemaDecoratorLoader() {}

	void load() {
		reflections.getSubTypesOf(SchemaDecorator.class).forEach(this::register);
	}

	void unload() {
		ASFUILogger.getLogger().info("I'm unloader");
	}

	private void register(Class<? extends SchemaDecorator> subType) {
		try {
			SchemaDecorator decorator = subType.newInstance();
			SchemaDecoratorFactory.getInstance().register(decorator::getAnnotation, decorator);
		} catch (InstantiationException | IllegalAccessException e) {
			ASFUILogger.getLogger().error(ASF01, e);
		}
	}
}
