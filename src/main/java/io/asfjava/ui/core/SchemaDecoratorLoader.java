package io.asfjava.ui.core;

import static io.asfjava.ui.core.logging.ErrorCode.ASF01;

import org.reflections.Reflections;

import io.asfjava.ui.core.logging.ASFUILogger;
import io.asfjava.ui.core.schema.decorators.SchemaDecorator;

final class SchemaDecoratorLoader {

	private static final String PACKAGESCAN = "io.asfjava.ui.core.schema.decorators";
	private static Reflections reflections = new Reflections(PACKAGESCAN);

	void load() {
		reflections.getSubTypesOf(SchemaDecorator.class).stream().forEach(subtype -> register(subtype));
	}

	private void register(Class<? extends SchemaDecorator> subtype) {
		try {
			SchemaDecorator decorator = subtype.newInstance();
			SchemaDecoratorFactory.getInstance().register(decorator::getAnnotation, decorator);
		} catch (InstantiationException | IllegalAccessException e) {
			ASFUILogger.getLogger().error(ASF01, e);
		}
	}

	void unload() {
		ASFUILogger.getLogger().info("I'm unloader");
	}

	static SchemaDecoratorLoader getInstance() {
		if (INSTANCE == null)
			INSTANCE = new SchemaDecoratorLoader();
		return INSTANCE;
	}

	private static SchemaDecoratorLoader INSTANCE;

	private SchemaDecoratorLoader() {
	}
}
