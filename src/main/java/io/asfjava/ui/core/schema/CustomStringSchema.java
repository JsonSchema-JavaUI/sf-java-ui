package io.asfjava.ui.core.schema;

import java.awt.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.types.StringSchema;

import io.asfjava.ui.core.form.ComboBox;
import io.asfjava.ui.core.form.TextField;

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
		}
		ComboBox Comboannotation = beanProperty.getAnnotation(ComboBox.class);
		if (Comboannotation != null) {
			this.setTitle(Comboannotation.title());
			Set<String> enums = new HashSet<>(Arrays.asList(Comboannotation.values()));
			this.setEnums(enums);
		}
	}

	String getLayout() {
		return layout;
	}

	void setLayout(String layout) {
		this.layout = layout;
	}
}
