package io.asfjava.ui.core.generators;

import java.lang.reflect.Field;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface FormDefinitionGenerator {

	void generate(ObjectNode fieldFormDefinition, Field field);

	String getAnnoation();
}
