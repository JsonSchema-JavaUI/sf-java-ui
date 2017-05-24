package io.asfjava.ui.core.generators;

import java.lang.reflect.Field;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.asfjava.ui.core.form.ComboBox;

public class ComboBoxGenerator implements FormDefinitionGenerator {

	@Override
	public void generate(ObjectNode fieldFormDefinition, Field field) {
		ComboBox annotation = field.getAnnotation(ComboBox.class);
		fieldFormDefinition.put("key", field.getName());
		fieldFormDefinition.put("autofocus", annotation.autofocus());
		fieldFormDefinition.put("disabled", annotation.disabled());
		fieldFormDefinition.put("multiple", annotation.multiple());
		fieldFormDefinition.put("required", annotation.required());
		fieldFormDefinition.put("size", annotation.size());
	}

	@Override
	public String getAnnoation() {
		return ComboBox.class.getName();
	}

}
