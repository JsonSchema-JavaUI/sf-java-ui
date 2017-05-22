package io.asfjava.ui.core.generators;

import java.lang.reflect.Field;

import com.fasterxml.jackson.databind.node.ObjectNode;

public final class DefaultGenerator implements FormDefinitionGenerator {
	@Override
	public void generate(ObjectNode node, Field field) {

	}

	@Override
	public String getAnnoation() {
		return "";
	}

	public DefaultGenerator() {
	}
}
