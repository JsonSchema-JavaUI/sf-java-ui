package io.asfjava.ui.core;

import java.util.Set;

import org.reflections.Reflections;

import io.asf.java.ui.schema.decorator.SchemaDecorator;



final class SchemaDecoratorLoader {

	private static final String PACKAGESCAN = "io.asfjava.ui.schema.decorator";
	private static Reflections reflections = new Reflections(PACKAGESCAN);
	void load() {

		Set<Class<? extends SchemaDecorator>> subTypes = reflections
				.getSubTypesOf(SchemaDecorator.class);
		for (Class<? extends SchemaDecorator> subtype : subTypes) {
			SchemaDecorator schemaDecorator;
			try {
				schemaDecorator = (SchemaDecorator) Class.forName(subtype.getName()).newInstance();
				SchemaDecoratorFactory.getInstance().register(schemaDecorator.getAnnotation(),
						schemaDecorator);
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

	static SchemaDecoratorLoader getInstance() {
		if (INSTANCE == null)
			INSTANCE = new SchemaDecoratorLoader();
		return INSTANCE;
	}

	private static SchemaDecoratorLoader INSTANCE;

	private SchemaDecoratorLoader() {
	}
}
