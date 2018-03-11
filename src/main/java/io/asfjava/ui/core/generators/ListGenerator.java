package io.asfjava.ui.core.generators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.asfjava.ui.core.form.ValuesContainer;
import io.asfjava.ui.core.logging.ASFUILogger;

public class ListGenerator {

	protected void buildValueDefinition(ObjectMapper listMapper, ArrayNode titlesMap, String value) {
		ObjectNode entry = listMapper.createObjectNode();
		String upperCasedValue = value.toUpperCase();
		String lowerCasedValue = value.toLowerCase();
		if (value.equals(upperCasedValue)) {
			entry.put("name", value.toLowerCase());
		} else if (value.equals(lowerCasedValue)) {
			entry.put("name", value.replace(value.substring(0, 1), value.substring(0, 1).toUpperCase()));
		} else {
			entry.put("name", value);
		}
		entry.put("value", value);
		titlesMap.add(entry);
	}

	protected void buildValues(ObjectMapper listMapper, ArrayNode titlesMap, Class<? extends ValuesContainer> valuesContainer) {
		try {
			valuesContainer.newInstance().getValues().forEach((key, value) -> {
				ObjectNode entryNode = listMapper.createObjectNode();
				entryNode.put("name", key);
				entryNode.putPOJO("value", value);
				titlesMap.add(entryNode);
			});
		} catch (InstantiationException | IllegalAccessException e) {
			ASFUILogger.getLogger().error(e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
