package io.asfjava.ui.core.generators;

import java.lang.reflect.Field;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.asfjava.ui.core.form.TextField;

public class TextFieldGenerator implements FormDefinitionGenerator {

	@Override
	public void generate(ObjectNode fieldFormDefinition, Field field) {
		TextField annotation = field.getAnnotation(TextField.class);

		fieldFormDefinition.put("key", field.getName());
		fieldFormDefinition.put("title", annotation.title());

		String description = annotation.description();
		if (!description.isEmpty()) {
			fieldFormDefinition.put("description", description);
		}

		String placeHolder = annotation.placeHolder();
		if (!placeHolder.isEmpty()) {
			fieldFormDefinition.put("placeholder", placeHolder);
		}

		boolean noTitle = annotation.noTitle();
		if (noTitle) {
			fieldFormDefinition.put("notitle", noTitle);
		}

		String validationMessage = annotation.validationMessage();
		if (!validationMessage.isEmpty()) {
			fieldFormDefinition.put("validationMessage", validationMessage);
		}

		boolean readOnly = annotation.readOnly();
		if (readOnly) {
			fieldFormDefinition.put("readonly", readOnly);
		}
	}

	@Override
	public String getAnnoation() {
		return TextField.class.getName();
	}

	public TextFieldGenerator() {
	}
}
