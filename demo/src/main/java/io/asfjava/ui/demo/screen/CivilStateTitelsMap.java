package io.asfjava.ui.demo.screen;

import java.util.HashMap;

import io.asfjava.ui.core.form.TitleMapsAdapter;

public class CivilStateTitelsMap extends TitleMapsAdapter{

	@Override
	public HashMap<String,Object> getPossibleValues(){
		
		HashMap<String, Object> myMap = new HashMap<>();
		
		myMap.put("Maried", "COMMITTED");
		myMap.put("Single", "HAPPY");
		myMap.put("Devorced", "RELEASED");
		
		return myMap;
	}
}
