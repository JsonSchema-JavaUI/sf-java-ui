package io.asfjava.ui.core.generators;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.asfjava.ui.core.form.CheckBox;
import io.asfjava.ui.core.form.ValuesContainer;
import io.asfjava.ui.core.logging.ASFUILogger;

public class CheckBoxGenerator extends ListGenerator implements FormDefinitionGenerator{

	@Override
	public void generate(ObjectNode fieldFormDefinition, Field field) {
		CheckBox annotation = field.getAnnotation(CheckBox.class);
		fieldFormDefinition.put("key", field.getName());
		fieldFormDefinition.put("type", "checkboxes");
		fieldFormDefinition.put("multiple", annotation.multiple());
		fieldFormDefinition.put("required", annotation.required());
		ObjectMapper checkBoxMapper = new ObjectMapper();
		ArrayNode titlesMap = checkBoxMapper.createArrayNode();
		if (annotation.values().length > 0) {
			Arrays.stream(annotation.values()).forEach(value -> buildValueDefinition(checkBoxMapper, titlesMap, value));
			fieldFormDefinition.set("titleMap", titlesMap);
		} else if (!annotation.titleMap().equals(ValuesContainer.class)) {
			buildValues(checkBoxMapper, titlesMap, annotation.titleMap());
			fieldFormDefinition.set("titleMap", titlesMap);
		}
	}

	@Override
	public String getAnnotation() {
		return CheckBox.class.getName();
	}

}
