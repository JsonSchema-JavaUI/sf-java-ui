package io.asfjava.ui.schema.generators.loader;

import java.util.Set;

import org.reflections.Reflections;

import io.asfjava.ui.schema.generators.factory.GeneratorFactory;
import io.asfjava.ui.schema.interfaces.SchemaGenerator;

public class GeneratorsLoader {

	private static final String PACKAGESCAN = "io.asfjava.ui.schema.generators";
	private static Reflections reflections = new Reflections(PACKAGESCAN);

	public static void loadGenerators() {
		Set<Class<? extends SchemaGenerator>> subTypes = reflections.getSubTypesOf(SchemaGenerator.class);
		for (Class<? extends SchemaGenerator> subtype : subTypes)
		{   SchemaGenerator schemaGenerator;
		try {
			schemaGenerator = (SchemaGenerator) Class.forName(subtype.getName()).newInstance();
			GeneratorFactory.registerDriver(subtype,schemaGenerator);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		}
	}

}
