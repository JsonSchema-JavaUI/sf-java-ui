package io.asfjava.ui.demo.screen;

import java.util.HashMap;
import java.util.Map;

import io.asfjava.ui.core.form.ValuesContainer;

public class CivilStateTitelsMap implements ValuesContainer {

	@Override
	public Map<String, String> getValues() {

		HashMap<String, String> myMap = new HashMap<>();

		myMap.put("Maried", "COMMITTED");
		myMap.put("Single", "HAPPY");
		myMap.put("Devorced", "RELEASED");

		return myMap;
	}
}
