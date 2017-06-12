package io.asfjava.ui.core.schema;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  TextFieldFormTest.class,
  NumberFormTest.class,
  PasswordFormTest.class,
  TextAreaFormTest.class,
  RadioBoxFormTest.class,
  ComboBoxFormTest.class,
  CheckBoxFormTest.class,
  TabbedFormTest.class
})
public class UiFormSchemaGeneratorTest {	
}