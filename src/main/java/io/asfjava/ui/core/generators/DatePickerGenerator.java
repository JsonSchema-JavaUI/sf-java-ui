package io.asfjava.ui.core.generators;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.asfjava.ui.core.form.DatePicker;

public class DatePickerGenerator implements FormDefinitionGenerator {

	@Override
	public void generate(ObjectNode fieldFormDefinition, Field field) {
		DatePicker annotation = field.getAnnotation(DatePicker.class);

		fieldFormDefinition.put("key", field.getName());
		fieldFormDefinition.put("title", annotation.title());
		fieldFormDefinition.put("minDate", annotation.minDate());

		String maxDate = annotation.maxDate();
		if ("new Date()".equals(maxDate)) {
			Date currentDate = new Date();
			fieldFormDefinition.put("maxDate", new SimpleDateFormat(annotation.format()).format(currentDate));
		} else {
			fieldFormDefinition.put("maxDate", annotation.maxDate());
		}

		fieldFormDefinition.put("format", annotation.format());

		boolean noTitle = annotation.noTitle();
		if (noTitle) {
			fieldFormDefinition.put("notitle", noTitle);
		}

		boolean readOnly = annotation.readOnly();
		if (readOnly) {
			fieldFormDefinition.put("readonly", readOnly);
		}
	}

	@Override
	public String getAnnoation() {
		return DatePicker.class.getName();
	}
}