package io.asfjava.ui.schema.interfaces;

import java.lang.reflect.Field;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface SchemaGenerator {

	ArrayNode generate(ObjectNode fieldFormDefinition,ArrayNode formDefinition,Field field);
}
