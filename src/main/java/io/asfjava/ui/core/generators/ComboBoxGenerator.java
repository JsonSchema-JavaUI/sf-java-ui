package io.asfjava.ui.core.generators;

import java.lang.reflect.Field;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.asfjava.ui.core.form.ComboBox;
import io.asfjava.ui.core.form.TitleMapsAdapter;

public class ComboBoxGenerator implements FormDefinitionGenerator {

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
		ObjectMapper comboMapper = new ObjectMapper();
		ArrayNode titlesMap = comboMapper.createArrayNode();
		String[] comboValues = annotation.values();
		if (comboValues.length != 0) {
			for (String value : comboValues) {
				ObjectNode entry = comboMapper.createObjectNode();
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
		} else if (!annotation.titleMap().equals(TitleMapsAdapter.class)) {

			try {
				Map<String, Object> map = (annotation.titleMap()).newInstance().getPossibleValues();

				for (Map.Entry<String, Object> iterator : map.entrySet()) {
					ObjectNode entry = comboMapper.createObjectNode();
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
		return ComboBox.class.getName();
	}

}
