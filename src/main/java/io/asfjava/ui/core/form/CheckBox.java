package io.asfjava.ui.core.form;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface CheckBox {

	String title();

	String[] values() default {};

	boolean multiple() default false;

	boolean required() default false;

	String defaultvalue() default "";

	Class<? extends ValuesContainer> titleMap() default ValuesContainer.class;
}
