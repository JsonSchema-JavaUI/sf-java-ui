package io.asfjava.ui.core.schema;

import java.util.HashMap;
import java.util.Map;

import io.asfjava.ui.core.form.ValuesContainer;

public class MyCheckBoxValues implements ValuesContainer {
	
	@Override
	public Map<String, String> getValues() {
		Map<String, String> values = new HashMap<>();
		values.put("Red", "red");
		values.put("Green", "green");
		values.put("Blue", "blue");
		return values;
	}
}
