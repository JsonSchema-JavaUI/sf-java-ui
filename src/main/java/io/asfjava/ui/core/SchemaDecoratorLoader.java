package io.asfjava.ui.core;

import static io.asfjava.ui.core.logging.ErrorCode.ASF02;

import java.util.Set;

import org.reflections.Reflections;

import io.asfjava.ui.core.logging.ASFUILogger;
import io.asfjava.ui.core.schema.decorator.SchemaDecorator;

final class SchemaDecoratorLoader {

	private static final String PACKAGESCAN = "io.asfjava.ui.schema.decorator";
	private static Reflections reflections = new Reflections(PACKAGESCAN);

	void load() {

		Set<Class<? extends SchemaDecorator>> subTypes = reflections.getSubTypesOf(SchemaDecorator.class);
		for (Class<? extends SchemaDecorator> subtype : subTypes) {
			SchemaDecorator schemaDecorator;
			try {
				schemaDecorator = (SchemaDecorator) Class.forName(subtype.getName()).newInstance();
				SchemaDecoratorFactory.getInstance().register(schemaDecorator.getAnnotation(), schemaDecorator);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				ASFUILogger.getLogger().error(ASF02, e);
			}
		}
	}

	void unload() {
		System.out.println("I'm unloader");
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
