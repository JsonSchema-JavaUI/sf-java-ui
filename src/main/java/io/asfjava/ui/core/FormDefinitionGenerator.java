package io.asfjava.ui.core;

import java.lang.reflect.Field;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface FormDefinitionGenerator {

	void generate(ObjectNode node, Field field);
}
