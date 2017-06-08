package io.asfjava.ui.core.schema;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.jsonSchema.types.StringSchema;

import io.asfjava.ui.core.SchemaDecoratorFactory;
import io.asfjava.ui.core.form.CheckBox;
import io.asfjava.ui.core.form.ComboBox;
import io.asfjava.ui.core.form.Password;
import io.asfjava.ui.core.form.RadioBox;
import io.asfjava.ui.core.form.TextArea;
import io.asfjava.ui.core.form.TextField;

class CustomStringSchema extends StringSchema {

	@Override
	public void enrichWithBeanProperty(BeanProperty beanProperty) {
		super.enrichWithBeanProperty(beanProperty);
		if (beanProperty.getAnnotation(TextField.class) != null) {
			SchemaDecoratorFactory.getInstance().getGenerator(TextField.class.getName()).customizeSchema(beanProperty,
					this);
		}
		if (beanProperty.getAnnotation(ComboBox.class) != null) {
			SchemaDecoratorFactory.getInstance().getGenerator(ComboBox.class.getName()).customizeSchema(beanProperty,
					this);
		}
		if (beanProperty.getAnnotation(CheckBox.class) != null) {
			SchemaDecoratorFactory.getInstance().getGenerator(CheckBox.class.getName()).customizeSchema(beanProperty,
					this);
		}
		if (beanProperty.getAnnotation(RadioBox.class) != null) {
			SchemaDecoratorFactory.getInstance().getGenerator(RadioBox.class.getName()).customizeSchema(beanProperty,
					this);
		}
		if (beanProperty.getAnnotation(TextArea.class) != null) {
			SchemaDecoratorFactory.getInstance().getGenerator(TextArea.class.getName()).customizeSchema(beanProperty,
					this);
		}
		if (beanProperty.getAnnotation(Password.class) != null) {
			SchemaDecoratorFactory.getInstance().getGenerator(TextField.class.getName()).customizeSchema(beanProperty,
					this);
		}
	}

}
