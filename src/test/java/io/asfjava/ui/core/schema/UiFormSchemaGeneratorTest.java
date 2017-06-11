package io.asfjava.ui.core.schema;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

import java.io.Serializable;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.asfjava.ui.core.GeneratorFactoryInitializer;
import io.asfjava.ui.core.form.CheckBox;
import io.asfjava.ui.core.form.ComboBox;
import io.asfjava.ui.core.form.Number;
import io.asfjava.ui.core.form.Password;
import io.asfjava.ui.core.form.RadioBox;
import io.asfjava.ui.core.form.Tab;
import io.asfjava.ui.core.form.TextArea;
import io.asfjava.ui.core.form.TextField;
import io.asfjava.ui.dto.UiForm;

public class UiFormSchemaGeneratorTest {
	static GeneratorFactoryInitializer generatorFactoryInitializer;

	@BeforeClass
	public static void setUpBeforeClass() {
		generatorFactoryInitializer = new GeneratorFactoryInitializer();
		generatorFactoryInitializer.contextInitialized(null);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		generatorFactoryInitializer.contextDestroyed(null);
	}

	@Test
	public void testGenerate_textField() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(TextFieldForm.class);
		String json = new ObjectMapper().writeValueAsString(ui);

		Assert.assertThat(json, hasJsonPath("$.schema.properties.firstName.title", equalTo("First Name")));
		Assert.assertThat(json, hasJsonPath("$.schema.properties.firstName.pattern", equalTo("[a-z]")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='firstName')]", hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='firstName')].description",
				hasItem("This is a description for your first name field")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='firstName')].placeholder", hasItem("Your first name")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='firstName')].validationMessage", hasItem("this is a validation msg")));
		// Assert.assertThat(json,
		// hasJsonPath("$.form[?(@.key=='firstName')].type",hasItem("textField")));

	}

	@Test
	public void testGenerate_textField_WithFieldAddonRight() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(TextFieldFormRight.class);
		String json = new ObjectMapper().writeValueAsString(ui);

		Assert.assertThat(json, hasJsonPath("$.schema.properties.firstName.title", equalTo("First Name")));
		Assert.assertThat(json, hasJsonPath("$.schema.properties.firstName.pattern", equalTo("[a-z]")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='firstName')]", hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='firstName')].description",
				hasItem("This is a description for your first name field")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='firstName')].placeholder", hasItem("Your first name")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='firstName')].validationMessage", hasItem("this is a validation msg")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='firstName')].fieldAddonRight", hasItem("@")));

	}

	@Test
	public void testGenerate_textField_WithFieldAddonLeft() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(TextFieldFormLeft.class);
		String json = new ObjectMapper().writeValueAsString(ui);

		Assert.assertThat(json, hasJsonPath("$.schema.properties.firstName.title", equalTo("First Name")));
		Assert.assertThat(json, hasJsonPath("$.schema.properties.firstName.pattern", equalTo("[a-z]")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='firstName')]", hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='firstName')].description",
				hasItem("This is a description for your first name field")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='firstName')].placeholder", hasItem("Your first name")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='firstName')].validationMessage", hasItem("this is a validation msg")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='firstName')].fieldAddonLeft", hasItem("@")));

	}

	@Test
	public void testGenerate_Number_For_Integer() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(IntegerNumberForm.class);
		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.number.title", equalTo("Integer Number")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')]", hasSize(1)));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='number')].description", hasItem("This is an integer number")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='number')].placeholder", hasItem("Integer Number PlaceHolder")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].validationMessage",
				hasItem("this is a validation msg for an integer value")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].type", hasItem("number")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].notitle", hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].readonly", hasItem(true)));

	}

	@Test
	public void testGenerate_Number_For_Long() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(LongNumberForm.class);
		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.number.title", equalTo("Long Number")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')]", hasSize(1)));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='number')].description", hasItem("This is a long number")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='number')].placeholder", hasItem("Long Number PlaceHolder")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].validationMessage",
				hasItem("this is a validation msg for long value")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].type", hasItem("number")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].notitle", hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].readonly", hasItem(true)));

	}
	
	
	@Test
	public void testGenerate_Number_For_Double() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(DoubleNumberForm.class);
		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.number.title", equalTo("Double Number")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')]", hasSize(1)));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='number')].description", hasItem("This is a double number")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='number')].placeholder", hasItem("Double Number PlaceHolder")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].validationMessage",
				hasItem("this is a validation msg for double value")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].type", hasItem("number")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].notitle", hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].readonly", hasItem(true)));

	}
	
	@Test
	public void testGenerate_Number_WithRightAddon() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(NumberFormRight.class);
		String json = new ObjectMapper().writeValueAsString(ui);
		//Assert.assertThat(json, hasJsonPath("$.schema.properties.number.title",equalTo("Number")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')]",hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].description",hasItem("This is a number")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].placeholder",hasItem("Number of children")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].validationMessage",hasItem("this is a validation msg")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].type",hasItem("number")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].notitle",hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].readonly",hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].fieldAddonRight",hasItem("@")));

	}
	
	@Test
	public void testGenerate_Number_WithLeftAddon() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(NumberFormLeft.class);
		String json = new ObjectMapper().writeValueAsString(ui);
		//Assert.assertThat(json, hasJsonPath("$.schema.properties.number.title",equalTo("Number")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')]",hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].description",hasItem("This is a number")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].placeholder",hasItem("Number of children")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].validationMessage",hasItem("this is a validation msg")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].type",hasItem("number")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].notitle",hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].readonly",hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].fieldAddonLeft",hasItem("@")));

	}

	@Test
	public void testGenerate_Password() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(PasswordForm.class);

		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.password.title", equalTo("Password")));
		Assert.assertThat(json, hasJsonPath("$.schema.properties.password.pattern", equalTo("[a-z]")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')]", hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].description", hasItem("This is password")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='password')].placeholder", hasItem("Please set you password")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='password')].validationMessage", hasItem("this is a validation msg")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].type", hasItem("password")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].notitle", hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].readonly", hasItem(true)));
	}
	
	@Test
	public void testGenerate_Password_WithFieldAddonLeft() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(PasswordForm3.class);
		
		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.password.title",equalTo("Password")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')]",hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.schema.properties.password.pattern", equalTo("[a-z]")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].description",hasItem("This is password")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].placeholder",hasItem("Please set you password")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].validationMessage",hasItem("this is a validation msg")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].type",hasItem("password")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].notitle",hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].readonly",hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].fieldAddonLeft",hasItem("@")));

	}
	
	@Test
	public void testGenerate_Password_WithFieldAddonRight() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(PasswordForm2.class);
		
		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.password.title",equalTo("Password")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')]",hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.schema.properties.password.pattern", equalTo("[a-z]")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].description",hasItem("This is password")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].placeholder",hasItem("Please set you password")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].validationMessage",hasItem("this is a validation msg")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].type",hasItem("password")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].notitle",hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].readonly",hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].fieldAddonRight",hasItem("@")));

	}

	@Test
	public void testGenerate_TextArea() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(TextAreaForm.class);

		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.address.title", equalTo("Address")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='address')]", hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='address')].description", hasItem("This is textarea")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='address')].placeholder", hasItem("Fill your address please")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='address')].validationMessage", hasItem("this is a validation msg")));
		// Assert.assertThat(json,
		// hasJsonPath("$.form[?(@.key=='password')].type",hasItem("textArea")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='address')].notitle", hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='address')].readonly", hasItem(true)));

	}
	
	public void testGenerate_TextArea_WithFieldAddOnLeft() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(TextAreaForm2.class);

		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.address.title", equalTo("Address")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='address')]", hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.schema.properties.address.minLenght", equalTo(6)));
		Assert.assertThat(json, hasJsonPath("$.schema.properties.address.maxLenght", equalTo(10)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='address')].description", hasItem("This is textarea")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='address')].placeholder", hasItem("Fill your address please")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='address')].validationMessage", hasItem("this is a validation msg")));
		// Assert.assertThat(json,
		// hasJsonPath("$.form[?(@.key=='password')].type",hasItem("textArea")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='address')].notitle", hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='address')].readonly", hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='address')].fieldAddonLeft", hasItem("@")));


	}
	
	public void testGenerate_TextArea_WithFieldAddOnRight() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(TextAreaForm3.class);

		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.address.title", equalTo("Address")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='address')]", hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.schema.properties.address.minLenght", equalTo(6)));
		Assert.assertThat(json, hasJsonPath("$.schema.properties.address.maxLenght", equalTo(10)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='address')].description", hasItem("This is textarea")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='address')].placeholder", hasItem("Fill your address please")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='address')].validationMessage", hasItem("this is a validation msg")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='address')].notitle", hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='address')].readonly", hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='address')].fieldAddonRight", hasItem("@")));


	}

	@Test
	public void testGenerate_CheckBox() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(CheckBoxForm.class);
		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.color.title", equalTo("Color")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')]", hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')].multiple", hasItem(false)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')].required", hasItem(true)));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='color')].titleMap[?(@.name=='Red')].value", hasItem("red")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='color')].titleMap[?(@.name=='Blue')].value", hasItem("blue")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='color')].titleMap[?(@.name=='Green')].value", hasItem("green")));
	}

	@Test
	public void testGenerate_CheckBox_WithCustomValuesContainer() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(CheckBoxForm2.class);
		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.color.title", equalTo("Color")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')]", hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')].multiple", hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')].required", hasItem(false)));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='color')].titleMap[?(@.name=='Red')].value", hasItem("red")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='color')].titleMap[?(@.name=='Blue')].value", hasItem("blue")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='color')].titleMap[?(@.name=='Green')].value", hasItem("green")));
	}

	@Test
	public void testGenerate_RadioBox() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(RadioBoxForm.class);
		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.civilState.title", equalTo("Civil State")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='civilState')]", hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='civilState')].readOnly", hasItem(false)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='civilState')].titleMap[?(@.name=='Maried')].value",
				hasItem("COMMITTED")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='civilState')].titleMap[?(@.name=='Single')].value", hasItem("HAPPY")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='civilState')].titleMap[?(@.name=='Divorced')].value",
				hasItem("RELEASED")));

	}

	@Test
	public void testGenerate_ComboBox() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(ComboBoxForm.class);

		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.currency.title", equalTo("Currency")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='currency')]", hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='currency')].disabled", hasItem(false)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='currency')].multiple", hasItem(false)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='currency')].required", hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='currency')].autofocus", hasItem(false)));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='currency')].titleMap[?(@.name=='Euro')].value", hasItem("euro")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='currency')].titleMap[?(@.name=='Dollar')].value", hasItem("dollar")));

	}

	@Test
	public void testGenerate_ComboBox_WithCustomValuesContainer() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(ComboBoxForm2.class);

		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.gender.title", equalTo("Gender")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='gender')]", hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='gender')].disabled", hasItem(false)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='gender')].multiple", hasItem(false)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='gender')].required", hasItem(false)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='gender')].autofocus", hasItem(false)));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='gender')].titleMap[?(@.name=='Male')].value", hasItem("male")));
		Assert.assertThat(json,
				hasJsonPath("$.form[?(@.key=='gender')].titleMap[?(@.name=='Female')].value", hasItem("female")));

	}
	
	@Test
	public void testGenerate_TabbedFormed() throws JsonProcessingException{
		UiForm ui = UiFormSchemaGenerator.get().generate(TabbedForm.class);

		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.form[?(@.tabs)]"));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.tabs)].tabs[*]", hasSize(2)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.tabs)].tabs[0].title",hasItem("Info")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.tabs)].tabs[1].title",hasItem("Contact")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.tabs)].tabs[?(@.title=='Info')].items[*]",hasSize(2)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.tabs)].tabs[?(@.title=='Contact')].items[*]",hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='webSite')]"));
	}
}
	

class TextFieldForm implements Serializable {

	@TextField(title = "First Name", placeHolder = "Your first name", pattern = "[a-z]",minLenght=6,maxLenght=10, noTitle = true, validationMessage = "this is a validation msg", description = "This is a description for your first name field", readOnly = true)
	private String firstName;

	public String getFirstName() {
		return firstName;
	}

}

class TextFieldFormRight implements Serializable {

	@TextField(title = "First Name", placeHolder = "Your first name", fieldAddonRight = "@", pattern = "[a-z]",minLenght=6,maxLenght=10, noTitle = true, validationMessage = "this is a validation msg", description = "This is a description for your first name field")
	private String firstName;

	public String getFirstName() {
		return firstName;
	}

}

class TextFieldFormLeft implements Serializable {

	@TextField(title = "First Name", placeHolder = "Your first name", fieldAddonLeft = "@", pattern = "[a-z]", noTitle = true, validationMessage = "this is a validation msg", description = "This is a description for your first name field")
	private String firstName;

	public String getFirstName() {
		return firstName;
	}

}

class IntegerNumberForm implements Serializable {

	@Number(title = "Integer Number", placeHolder = "Integer Number PlaceHolder", description = "This is an integer number", noTitle = true, validationMessage = "this is a validation msg for an integer value", readOnly = true)
	private Integer number;

	public Integer getNumber() {
		return number;
	}
}

class NumberFormRight implements Serializable {

	@Number(title = "Number of children", placeHolder = "Number of children", fieldAddonRight = "@", description = "This is a number", noTitle = true, validationMessage = "this is a validation msg", readOnly = true)
	private Integer number;

	public Integer getNumber() {
    return number;
  }
}

class LongNumberForm implements Serializable {

	@Number(title = "Long Number", placeHolder = "Long Number PlaceHolder", description = "This is a long number", noTitle = true, validationMessage = "this is a validation msg for long value", readOnly = true)
	private Long number;

	public Long getNumber() {
		return number;
	}
}

class NumberFormLeft implements Serializable {

	@Number(title = "Number of children", placeHolder = "Number of children", fieldAddonLeft = "@", description = "This is a number", noTitle = true, validationMessage = "this is a validation msg", readOnly = true)
	private Integer number;

	public Integer getNumber() {
  		return number;
	}
}
class DoubleNumberForm implements Serializable {

	@Number(title = "Double Number", placeHolder = "Double Number PlaceHolder", description = "This is a double number", noTitle = true, validationMessage = "this is a validation msg for double value", readOnly = true)
	private Double number;

	public Double getNumber() {
		return number;
	}
}

class PasswordForm implements Serializable {

	@Password(title = "Password", placeHolder = "Please set you password", pattern = "[a-z]", description = "This is password", noTitle = true, validationMessage = "this is a validation msg", readOnly = true)
	private String password;

	public String getPassword() {
		return password;
	}
}

class PasswordForm2 implements Serializable {

	@Password(title = "Password", placeHolder = "Please set you password", pattern = "[a-z]",minLenght=6,maxLenght=10, fieldAddonRight = "@", description = "This is password", noTitle = true, validationMessage = "this is a validation msg", readOnly = true)
	private String password;

	public String getPassword() {
		return password;
	}
}

class PasswordForm3 implements Serializable {

	@Password(title = "Password", placeHolder = "Please set you password", pattern = "[a-z]",minLenght=6,maxLenght=10, fieldAddonLeft = "@", description = "This is password", noTitle = true, validationMessage = "this is a validation msg", readOnly = true)
	private String password;

	public String getPassword() {
		return password;
	}
}

class TextAreaForm2 implements Serializable {

	@TextArea(title = "Address", placeHolder = "Fill your address please", fieldAddonLeft = "@",minLenght=6,maxLenght=10, description = "This is textarea", noTitle = true, validationMessage = "this is a validation msg", readOnly = true)
	private String address;

	public String getAddress() {
		return address;
	}
}

class TextAreaForm3 implements Serializable {

	@TextArea(title = "Address", placeHolder = "Fill your address please",fieldAddonRight = "@",minLenght=6,maxLenght=10, description = "This is textarea", noTitle = true, validationMessage = "this is a validation msg", readOnly = true)
	private String address;

	public String getAddress() {
		return address;
	}
}

class TextAreaForm implements Serializable {

	@TextArea(title = "Address", placeHolder = "Fill your address please", description = "This is textarea",minLenght=6,maxLenght=10, noTitle = true, validationMessage = "this is a validation msg", readOnly = true)
	private String address;

	public String getAddress() {
		return address;
	}
}

class CheckBoxForm implements Serializable {

	@CheckBox(title = "Color", values = { "red", "blue", "green" }, defaultvalue = "red", required = true)
	private String color;

	public String getColor() {
		return color;
	}
}

class CheckBoxForm2 implements Serializable {

	@CheckBox(title = "Color", titleMap = MyCheckBoxValues.class, defaultvalue = "red", multiple = true)
	private String color;

	public String getColor() {
		return color;
	}
}

class RadioBoxForm implements Serializable {

	@RadioBox(title = "Civil State", titleMap = CivilStateValues.class)
	private String civilState;

	public String getCivilState() {
		return civilState;
	}
}

class ComboBoxForm implements Serializable {

	@ComboBox(title = "Currency", values = { "euro", "dollar" }, required = true)
	private String currency;

	public String getCurrency() {
		return currency;
	}
}

class ComboBoxForm2 implements Serializable {

	@ComboBox(title = "Gender", titleMap = GenderTitleMap.class)
	private String gender;

	public String getGender() {
		return gender;
	}
}

class TabbedForm implements Serializable{
	
	@Tab(title = "Info", index = 1)
	@TextField(title = "First Name", placeHolder = "Your first name", description = "This is a description for your first name field")
	private String firstName;
	
	@Tab(title = "Info", index = 1)
	@TextField(title = "Last Name", placeHolder = "Your last name")
	private String lastName;
	
	@Tab(title = "Contact", index = 2)
	@TextField(title = "eMail", placeHolder = "Your email", pattern = "^\\S+@\\S+$", validationMessage = "Your mail must be in this format jhondoe@example.com", description = "This is Text Field with pattern and validation message")
	private String email;
	
	@TextField(title = "Pesonal Website",fieldAddonLeft="http://", description = "This is TextField with fieldAddonLeft")
	private String webSite;
}
