package io.asfjava.ui.schema.generators.factory;

import java.util.concurrent.ConcurrentHashMap;

import io.asfjava.ui.schema.generators.DefaultGenerator;
import io.asfjava.ui.schema.interfaces.SchemaGenerator;

public class GeneratorFactory {

	private static ConcurrentHashMap<String, SchemaGenerator> generators = new ConcurrentHashMap<>();

	public static SchemaGenerator getGenerator(Class annotation) {
		if (!generators.contains(annotation.getName())) {
			return DefaultGenerator.getDefaultGenerator();
		}
		return generators.get(annotation.getName());
	}

	public static void registerDriver(Class annotation, SchemaGenerator generator) {
		if (annotation != null && generator != null) {
			generators.put(annotation.getName(), generator);
		}

	}

}
