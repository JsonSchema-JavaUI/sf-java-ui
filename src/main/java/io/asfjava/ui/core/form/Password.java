package io.asfjava.ui.core.form;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface Password {
	String title();

	String placeHolder() default "";

	String description() default "";

	boolean noTitle() default false;

	String validationMessage() default "";

	boolean readOnly() default false;
}