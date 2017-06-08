package io.asfjava.ui.core.generators;

import java.lang.reflect.Field;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.asfjava.ui.core.form.RadioBox;

public class RadioBoxGenerator implements FormDefinitionGenerator {

	@Override
	public void generate(ObjectNode fieldFormDefinition, Field field) {

		RadioBox annotation = field.getAnnotation(RadioBox.class);
		fieldFormDefinition.put("key", field.getName());
		fieldFormDefinition.put("readOnly", annotation.readOnly());
		fieldFormDefinition.put("type", "radios");
//		fieldFormDefinition.put("title", annotation.title());

		JsonNode radioFieldFormDefinition = ((JsonNode) fieldFormDefinition);

		ObjectMapper radioMapper = new ObjectMapper();

		ArrayNode titlesMap = radioMapper.createArrayNode();

		Map<String, String> map;

		try {
			map = (annotation.titleMap()).newInstance().getValues();

			for (Map.Entry<String, String> iterator : map.entrySet()) {
				ObjectNode entry = radioMapper.createObjectNode();
				entry.put("name", iterator.getKey());
				entry.putPOJO("value", iterator.getValue());
				titlesMap.add(entry);

			}
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Log It into a Logger
			e.printStackTrace();
		}

		((ObjectNode) radioFieldFormDefinition).set("titleMap", titlesMap);

	}

	@Override
	public String getAnnoation() {
		return RadioBox.class.getName();
	}

}
