package io.asfjava.ui.core.generators;

import java.lang.reflect.Field;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.asfjava.ui.core.form.RadioBox;
import io.asfjava.ui.core.logging.ASFUILogger;

public class RadioBoxGenerator implements FormDefinitionGenerator {

	@Override
	public void generate(ObjectNode fieldFormDefinition, Field field) {

		RadioBox annotation = field.getAnnotation(RadioBox.class);
		fieldFormDefinition.put("key", field.getName());
		fieldFormDefinition.put("readOnly", annotation.readOnly());
		fieldFormDefinition.put("type", "radios");

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
			ASFUILogger.getLogger().error(e.getMessage());
		}

		((ObjectNode) radioFieldFormDefinition).set("titleMap", titlesMap);

	}

	@Override
	public String getAnnotation() {
		return RadioBox.class.getName();
	}

}
