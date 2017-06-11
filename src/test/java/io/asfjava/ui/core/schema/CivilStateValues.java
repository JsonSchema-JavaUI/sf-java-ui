package io.asfjava.ui.core.schema;

import java.util.HashMap;
import java.util.Map;

import io.asfjava.ui.core.form.ValuesContainer;

public class CivilStateValues implements ValuesContainer {

	@Override
	public Map<String, String> getValues() {

		HashMap<String, String> myMap = new HashMap<>();

		myMap.put("Maried", "COMMITTED");
		myMap.put("Single", "HAPPY");
		myMap.put("Divorced", "RELEASED");

		return myMap;
	}
}
