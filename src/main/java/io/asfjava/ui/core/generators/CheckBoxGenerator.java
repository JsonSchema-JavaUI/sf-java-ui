package io.asfjava.ui.core.generators;

import java.lang.reflect.Field;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.asfjava.ui.core.form.CheckBox;
import io.asfjava.ui.core.form.ValuesContainer;

public class CheckBoxGenerator implements FormDefinitionGenerator {

	@Override
	public void generate(ObjectNode fieldFormDefinition, Field field) {
		CheckBox annotation = field.getAnnotation(CheckBox.class);
		fieldFormDefinition.put("key", field.getName());
		fieldFormDefinition.put("type", "checkboxes");
		fieldFormDefinition.put("multiple", annotation.multiple());
		fieldFormDefinition.put("required", annotation.required());
		ObjectMapper checkBoxMapper = new ObjectMapper();
		ArrayNode titlesMap = checkBoxMapper.createArrayNode();
		String[] checkBoxValues = annotation.values();
		if (checkBoxValues.length != 0) {
			for (String value : checkBoxValues) {
				ObjectNode entry = checkBoxMapper.createObjectNode();
				if (value.equals(value.toUpperCase())) {
					entry.put("name", value.toLowerCase());
				} else if (value.equals(value.toLowerCase())) {
					entry.put("name", value.replace(value.substring(0, 1), value.substring(0, 1).toUpperCase()));
				} else {
					entry.put("name", value);
				}
				entry.put("value", value);
				titlesMap.add(entry);
			}
			fieldFormDefinition.set("titleMap", titlesMap);
		} else if (!annotation.titleMap().equals(ValuesContainer.class)) {

			try {
				Map<String, String> map = (annotation.titleMap()).newInstance().getValues();

				for (Map.Entry<String, String> iterator : map.entrySet()) {
					ObjectNode entry = checkBoxMapper.createObjectNode();
					entry.put("name", iterator.getKey());
					entry.putPOJO("value", iterator.getValue());
					titlesMap.add(entry);
				}
				fieldFormDefinition.set("titleMap", titlesMap);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}		
	}

	@Override
	public String getAnnoation() {
		return CheckBox.class.getName();
	}

}
