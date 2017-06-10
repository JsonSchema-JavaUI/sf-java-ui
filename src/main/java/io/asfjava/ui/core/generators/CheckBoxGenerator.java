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

public class CheckBoxGenerator implements FormDefinitionGenerator{

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

			try {
				Map<String, String> map = (annotation.titleMap()).newInstance().getValues();
				map.entrySet().stream().forEach(mapEntry -> {
					ObjectNode entryNode = checkBoxMapper.createObjectNode();
					entryNode.put("name", mapEntry.getKey());
					entryNode.putPOJO("value", mapEntry.getValue());
					titlesMap.add(entryNode);
				});
				fieldFormDefinition.set("titleMap", titlesMap);
			} catch (InstantiationException | IllegalAccessException e) {
				ASFUILogger.getLogger().error(e.getMessage());
			}
		}
	}

	private void buildValueDefinition(ObjectMapper checkBoxMapper, ArrayNode titlesMap, String value) {
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

	@Override
	public String getAnnotation() {
		return CheckBox.class.getName();
	}

}
