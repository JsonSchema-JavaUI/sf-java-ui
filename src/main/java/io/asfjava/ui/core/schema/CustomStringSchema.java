package io.asfjava.ui.core.schema;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import com.fasterxml.jackson.module.jsonSchema.types.StringSchema;

import io.asfjava.ui.core.form.DatePicker;
import io.asfjava.ui.core.form.TextField;

class CustomStringSchema extends StringSchema {

	@Override
	public void enrichWithBeanProperty(BeanProperty beanProperty) {
		super.enrichWithBeanProperty(beanProperty);
		TextField annotation = beanProperty.getAnnotation(TextField.class);
		if (annotation != null && !annotation.pattern().isEmpty()) {
			this.setPattern(annotation.pattern());
		}
		DatePicker datePicker = beanProperty.getAnnotation(DatePicker.class);
		if (datePicker != null) {
			this.setFormat(JsonValueFormat.DATE);
		}
	}
}
