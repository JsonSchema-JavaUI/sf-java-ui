package io.asfjava.ui.schema.generators;

import java.lang.reflect.Field;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.asfjava.ui.schema.interfaces.SchemaGenerator;

public final class DefaultGenerator implements SchemaGenerator{
	
	private static SchemaGenerator defaultGenerator=new DefaultGenerator();
	
	private  DefaultGenerator() {
		
	}

	@Override
	public ArrayNode generate(ObjectNode fieldFormDefinition, ArrayNode formDefinition, Field field) {
		return formDefinition;
	}
	
	public static SchemaGenerator getDefaultGenerator()
	{
		return defaultGenerator;
	}

}
