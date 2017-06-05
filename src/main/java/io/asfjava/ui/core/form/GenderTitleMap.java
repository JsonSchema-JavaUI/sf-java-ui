package io.asfjava.ui.demo.screen;

import java.util.HashMap;

import io.asfjava.ui.core.form.TitleMapsAdapter;

public class GenderTitleMap extends TitleMapsAdapter{

	@Override
	public Map<String, Object> getPossibleValues() {
		HashMap<String, Object> values=new HashMap<>();
		values.put("Male", "M");
		values.put("Female", "F");
		return values;
	}

}
