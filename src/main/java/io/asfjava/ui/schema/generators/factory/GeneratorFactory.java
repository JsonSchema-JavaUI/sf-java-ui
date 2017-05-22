package io.asfjava.ui.schema.generators.factory;

import java.util.concurrent.ConcurrentHashMap;

import io.asfjava.ui.schema.generators.DefaultGenerator;
import io.asfjava.ui.schema.interfaces.SchemaGenerator;

public class GeneratorFactory {

	private static ConcurrentHashMap<String, SchemaGenerator> generators = new ConcurrentHashMap<>();

	private static DefaultGenerator defaultGenerator = new DefaultGenerator();

	public static SchemaGenerator getGenerator(Class<? extends SchemaGenerator>  genertor) {
		if (!generators.contains(genertor.getName())) {
			return defaultGenerator;
		}
		return generators.get(genertor.getName());
	}

	public static void registerDriver(Class<? extends SchemaGenerator>  genertor, SchemaGenerator schemaGenerator) {
		if (genertor != null && schemaGenerator != null) {
			generators.put(genertor.getName(), schemaGenerator);
		}

	}

}
