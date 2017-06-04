package io.asfjava.ui.core.form;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface DatePicker {
	String title();

	String minDate() default "1995-09-01";

	String maxDate() default "";

	String format();

	boolean noTitle() default false;

	boolean readOnly() default false;
}