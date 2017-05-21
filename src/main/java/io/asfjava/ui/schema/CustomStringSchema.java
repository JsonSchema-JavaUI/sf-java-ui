package io.asfjava.ui.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.types.StringSchema;

import io.asfjava.ui.form.TextField;

class CustomStringSchema extends StringSchema {
	@JsonProperty
	private String layout;

	@Override
	public void enrichWithBeanProperty(BeanProperty beanProperty) {
		super.enrichWithBeanProperty(beanProperty);
		TextField annotation = beanProperty.getAnnotation(TextField.class);
		if (annotation != null) {
//			this.setLayout(annotation.value().toString());
			this.setTitle(annotation.title());
		}
	}

	String getLayout() {
		return layout;
	}

	void setLayout(String layout) {
		this.layout = layout;
	}
}
