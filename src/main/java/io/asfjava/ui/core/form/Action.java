package io.asfjava.ui.core.form;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Actions.class)
public @interface Action {
	String type();

	String title();

	String onClick() default "";
}
