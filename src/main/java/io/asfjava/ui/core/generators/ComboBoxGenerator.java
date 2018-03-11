package io.asfjava.ui.core.generators;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.asfjava.ui.core.form.ComboBox;
import io.asfjava.ui.core.form.ValuesContainer;
import io.asfjava.ui.core.logging.ASFUILogger;

public class ComboBoxGenerator extends ListGenerator implements FormDefinitionGenerator {

	@Override
	public void generate(ObjectNode fieldFormDefinition, Field field) {
		ComboBox annotation = field.getAnnotation(ComboBox.class);
		fieldFormDefinition.put("key", field.getName());
		fieldFormDefinition.put("type", "select");
		fieldFormDefinition.put("autofocus", annotation.autofocus());
		fieldFormDefinition.put("disabled", annotation.disabled());
		fieldFormDefinition.put("multiple", annotation.multiple());
		fieldFormDefinition.put("required", annotation.required());
		fieldFormDefinition.put("size", annotation.size());
		if (!annotation.refURL().isEmpty()) {
			fieldFormDefinition.put("$ref", annotation.refURL());
		}

		ObjectMapper comboMapper = new ObjectMapper();
		ArrayNode titlesMap = comboMapper.createArrayNode();
		if (annotation.values().length != 0) {
			Arrays.stream(annotation.values()).forEach(value -> buildValueDefinition(comboMapper, titlesMap, value));
			fieldFormDefinition.set("titleMap", titlesMap);
		} else if (!annotation.titleMap().equals(ValuesContainer.class)) {
			buildValues(comboMapper, titlesMap, annotation.titleMap());
			fieldFormDefinition.set("titleMap", titlesMap);
		}

	}

	@Override
	public String getAnnotation() {
		return ComboBox.class.getName();
	}

}
