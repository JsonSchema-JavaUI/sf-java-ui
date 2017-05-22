package io.asfjava.ui.schema.generators.factory;

import java.security.GeneralSecurityException;
import java.util.concurrent.ConcurrentHashMap;

import io.asfjava.ui.schema.generators.DefaultGenerator;
import io.asfjava.ui.schema.interfaces.SchemaGenerator;

public class GeneratorFactory {

	private static ConcurrentHashMap<String, SchemaGenerator> generators = new ConcurrentHashMap<>();

	public static SchemaGenerator getGenerator(Class genertor) {
		if (!generators.contains(genertor.getName())) {
			return DefaultGenerator.getDefaultGenerator();
		}
		return generators.get(genertor.getName());
	}

	public static void registerDriver(Class genertor, SchemaGenerator schemaGenerator) {
		if (genertor != null && schemaGenerator != null) {
			generators.put(genertor.getName(), schemaGenerator);
		}

	}

}
