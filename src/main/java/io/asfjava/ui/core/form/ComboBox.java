package io.asfjava.ui.core.form;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * {@link ComboBox}This interface represent the ComboBox HTML Tag Title:
 * {@link #title()} represent the title of the comboBox. {@link #values()} an
 * array which contain the possible values of the comboBox. {@link #autofocus()}
 * by default false,specifies that the drop-down list should automatically get
 * focus when the page loads. {@link #disabled()} by default false,specifies
 * that a drop-down list should be disabled. {@link #multiple()} by default
 * false,specifies that multiple options can be selected at once.
 * {@link #required()} by default false,specifies that the user is required to
 * select a value before submitting the form. {@link #size()} by default
 * 1,defines the number of visible options in a drop-down list.
 * 
 * JsonSchema-JavaUI Team
 */

@Retention(RUNTIME)
@Target(FIELD)
public @interface ComboBox {

	String title();

	String[] values() default {};

	boolean autofocus() default false;

	boolean disabled() default false;

	boolean multiple() default false;

	boolean required() default false;

	int size() default 1;

	String refURL() default "";

	Class<? extends ValuesContainer> titleMap() default ValuesContainer.class;

}
