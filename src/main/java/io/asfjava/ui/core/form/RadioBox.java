package io.asfjava.ui.core.form;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface RadioBox {

	/*the title of the RadioBox*/
	String title();
	
	boolean readOnly() default false ;

	Class<? extends ValuesContainer> titleMap();


}
