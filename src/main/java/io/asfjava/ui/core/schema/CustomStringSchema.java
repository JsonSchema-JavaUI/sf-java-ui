package io.asfjava.ui.core.schema;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.types.StringSchema;

import io.asfjava.ui.core.form.ComboBox;
import io.asfjava.ui.core.form.Password;
import io.asfjava.ui.core.form.TextArea;
import io.asfjava.ui.core.form.TextField;
import io.asfjava.ui.core.form.Number;

class CustomStringSchema extends StringSchema {
	@JsonProperty
	private String layout;

	@Override
	public void enrichWithBeanProperty(BeanProperty beanProperty) {
		super.enrichWithBeanProperty(beanProperty);
		TextField annotation = beanProperty.getAnnotation(TextField.class);
		if (annotation != null) {
			// this.setLayout(annotation.value().toString());
			this.setTitle(annotation.title());
			this.setPattern(annotation.pattern());
		}
		ComboBox Comboannotation = beanProperty.getAnnotation(ComboBox.class);
		if (Comboannotation != null) {
			this.setTitle(Comboannotation.title());
			Set<String> enums = new HashSet<>(Arrays.asList(Comboannotation.values()));
			this.setEnums(enums);
		}
		CheckBox checkBoxannotation=beanProperty.getAnnotation(CheckBox.class);
		if(checkBoxannotation != null)
		{
			this.setTitle(checkBoxannotation.title());
                        this.setDefault(checkBoxannotation.defaultvalue());
		}
		TextArea textArea = beanProperty.getAnnotation(TextArea.class);
		if (textArea != null) {
			this.setTitle(textArea.title());
		}
		Password password = beanProperty.getAnnotation(Password.class);
		if (password != null) {
			this.setTitle(password.title());
		}
		Number number = beanProperty.getAnnotation(Number.class);
		if (number != null) {
			this.setTitle(number.title());
		}
	}

	String getLayout() {
		return layout;
	}

	void setLayout(String layout) {
		this.layout = layout;
	}
}
