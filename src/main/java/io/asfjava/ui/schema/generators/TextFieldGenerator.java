package io.asfjava.ui.schema.generators;

import java.lang.reflect.Field;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.asfjava.ui.form.TextField;
import io.asfjava.ui.schema.interfaces.SchemaGenerator;

public final class TextFieldGenerator implements SchemaGenerator {

	private static SchemaGenerator textFieldGenerator = new TextFieldGenerator();

	private TextFieldGenerator() {

	}

	@Override
	public ArrayNode generate(ObjectNode fieldFormDefinition, ArrayNode formDefinition, Field field) {
		TextField annotation = field.getAnnotation(TextField.class);

		fieldFormDefinition.put("key", field.getName());

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

		formDefinition.add(fieldFormDefinition);
		return formDefinition;
	}

	public static SchemaGenerator getTextFieldGenerator() {
		return textFieldGenerator;
	}

}
