package io.asfjava.ui.core.schema;

import java.io.Serializable;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.asfjava.ui.core.GeneratorFactoryInitializer;
import io.asfjava.ui.core.form.CheckBox;
import io.asfjava.ui.core.form.ComboBox;
import io.asfjava.ui.core.form.Number;
import io.asfjava.ui.core.form.Password;
import io.asfjava.ui.core.form.RadioBox;
import io.asfjava.ui.core.form.TextArea;
import io.asfjava.ui.core.form.TextField;
import io.asfjava.ui.dto.UiForm;
import io.asfjava.ui.test.JsonNodeAssert;

public class UiFormSchemaGeneratorTest {

	@BeforeClass
	public static void init() {
		new GeneratorFactoryInitializer().contextInitialized(null);
	}

	@Test
	public void testGenerate_textField() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(TextFieldForm.class);
		JsonNode json = new ObjectMapper().valueToTree(ui);
		JsonNodeAssert asserter = new JsonNodeAssert(json);
		asserter.assertEquals("schema.properties.firstName.title", "First Name");
		asserter.assertEquals("schema.properties.firstName.pattern", "[a-z]");
		asserter.assertEquals("form.0.key", "firstName");
		asserter.assertTrue("form.0.notitle");
		asserter.assertEquals("form.0.validationMessage", "this is a validation msg");
		asserter.assertEquals("form.0.description", "This is a description for your first name field");
		asserter.assertEquals("form.0.placeholder", "Your first name");
	}

	@Test
	public void testGenerate_Number() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(NumberForm.class);
		JsonNode json = new ObjectMapper().valueToTree(ui);
		JsonNodeAssert asserter = new JsonNodeAssert(json);
		asserter.assertEquals("schema.properties.number.title", "Number of children");
		asserter.assertEquals("form.0.key", "number");
		asserter.assertTrue("form.0.notitle");
		asserter.assertTrue("form.0.readonly");
		asserter.assertEquals("form.0.validationMessage", "this is a validation msg");
		asserter.assertEquals("form.0.description", "This is a number");
		asserter.assertEquals("form.0.placeholder", "Number of children");
	}

	@Test
	public void testGenerate_Password() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(PasswordForm.class);
		JsonNode json = new ObjectMapper().valueToTree(ui);
		JsonNodeAssert asserter = new JsonNodeAssert(json);
		asserter.assertEquals("schema.properties.password.title", "Password");
		asserter.assertEquals("form.0.key", "password");
		asserter.assertTrue("form.0.notitle");
		asserter.assertTrue("form.0.readonly");
		asserter.assertEquals("form.0.validationMessage", "this is a validation msg");
		asserter.assertEquals("form.0.description", "This is password");
		asserter.assertEquals("form.0.placeholder", "Please set you password");
	}
	
	@Test
	public void testGenerate_TextArea() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(TextAreaForm.class);
		JsonNode json = new ObjectMapper().valueToTree(ui);
		JsonNodeAssert asserter = new JsonNodeAssert(json);
		asserter.assertEquals("schema.properties.address.title", "Address");
		asserter.assertEquals("form.0.key", "address");
		asserter.assertTrue("form.0.notitle");
		asserter.assertTrue("form.0.readonly");
		asserter.assertEquals("form.0.validationMessage", "this is a validation msg");
		asserter.assertEquals("form.0.description", "This is textarea");
		asserter.assertEquals("form.0.placeholder", "Fill your address please");
	}
	
	@Test
	public void testGenerate_CheckBox() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(CheckBoxForm.class);
		JsonNode json = new ObjectMapper().valueToTree(ui);
		JsonNodeAssert asserter = new JsonNodeAssert(json);
		asserter.assertEquals("schema.properties.color.title", "Color");
		asserter.assertEquals("form.0.key", "color");
		asserter.assertFalse("form.0.multiple");
		asserter.assertTrue("form.0.required");
		asserter.assertEquals("form.0.titleMap.0.name", "Red");
		asserter.assertEquals("form.0.titleMap.0.value", "red");
		asserter.assertEquals("form.0.titleMap.1.name", "Blue");
		asserter.assertEquals("form.0.titleMap.1.value", "blue");
		asserter.assertEquals("form.0.titleMap.2.name", "Green");
		asserter.assertEquals("form.0.titleMap.2.value", "green");
	}
	
	@Test
	public void testGenerate_CheckBox_WithCustomValuesContainer() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(CheckBoxForm2.class);
		JsonNode json = new ObjectMapper().valueToTree(ui);
		JsonNodeAssert asserter = new JsonNodeAssert(json);
		asserter.assertEquals("schema.properties.color.title", "Color");
		asserter.assertEquals("form.0.key", "color");
		asserter.assertTrue("form.0.multiple");
		asserter.assertFalse("form.0.required");
		asserter.assertEquals("form.0.titleMap.0.name", "Red");
		asserter.assertEquals("form.0.titleMap.0.value", "red");
		asserter.assertEquals("form.0.titleMap.1.name", "Blue");
		asserter.assertEquals("form.0.titleMap.1.value", "blue");
		asserter.assertEquals("form.0.titleMap.2.name", "Green");
		asserter.assertEquals("form.0.titleMap.2.value", "green");
	}
	
	@Test
	public void testGenerate_RadioBox() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(RadioBoxForm.class);
		JsonNode json = new ObjectMapper().valueToTree(ui);
		JsonNodeAssert asserter = new JsonNodeAssert(json);
		asserter.assertEquals("schema.properties.civilState.title", "Civil State");
		asserter.assertEquals("form.0.key", "civilState");
		asserter.assertFalse("form.0.readonly");
		asserter.assertEquals("form.0.titleMap.0.name", "Maried");
		asserter.assertEquals("form.0.titleMap.0.value", "COMMITTED");
		asserter.assertEquals("form.0.titleMap.1.name", "Single");
		asserter.assertEquals("form.0.titleMap.1.value", "HAPPY");
		asserter.assertEquals("form.0.titleMap.2.name", "Divorced");
		asserter.assertEquals("form.0.titleMap.2.value", "RELEASED");
	}
	
	@Test
	public void testGenerate_ComboBox() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(ComboBoxForm.class);
		JsonNode json = new ObjectMapper().valueToTree(ui);
		JsonNodeAssert asserter = new JsonNodeAssert(json);
		asserter.assertEquals("schema.properties.currency.title", "Currency");
		asserter.assertEquals("form.0.key", "currency");
		asserter.assertFalse("form.0.autofocus");
		asserter.assertFalse("form.0.disabled");
		asserter.assertFalse("form.0.multiple");
		asserter.assertTrue("form.0.required");
		asserter.assertEquals("form.0.titleMap.0.name", "Euro");
		asserter.assertEquals("form.0.titleMap.0.value", "euro");
		asserter.assertEquals("form.0.titleMap.1.name", "Dollar");
		asserter.assertEquals("form.0.titleMap.1.value", "dollar");
	}
	
	@Test
	public void testGenerate_ComboBox_WithCustomValuesContainer() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(ComboBoxForm2.class);
		JsonNode json = new ObjectMapper().valueToTree(ui);
		JsonNodeAssert asserter = new JsonNodeAssert(json);
		asserter.assertEquals("schema.properties.gender.title", "Gender");
		asserter.assertEquals("form.0.key", "currency");
		asserter.assertFalse("form.0.autofocus");
		asserter.assertFalse("form.0.disabled");
		asserter.assertFalse("form.0.multiple");
		asserter.assertFalse("form.0.required");
		asserter.assertEquals("form.0.titleMap.0.name", "Male");
		asserter.assertEquals("form.0.titleMap.0.value", "male");
		asserter.assertEquals("form.0.titleMap.1.name", "Female");
		asserter.assertEquals("form.0.titleMap.1.value", "female");
	}
	
	

}

class TextFieldForm implements Serializable {

	@TextField(title = "First Name", placeHolder = "Your first name", pattern = "[a-z]", noTitle = true, validationMessage = "this is a validation msg", description = "This is a description for your first name field")
	private String firstName;

	public String getFirstName() {
		return firstName;
	}

}

class NumberForm implements Serializable {

	@Number(title = "Number of children", placeHolder = "Number of children", description = "This is a number", noTitle = true, validationMessage = "this is a validation msg", readOnly = true)
	private Integer number;

	public Integer getNumber() {
		return number;
	}
}

class PasswordForm implements Serializable {

	@Password(title = "Password", placeHolder = "Please set you password", description = "This is password", noTitle = true, validationMessage = "this is a validation msg", readOnly = true)
	private String password;

	public String getPassword() {
		return password;
	}
}

class TextAreaForm implements Serializable {

	@TextArea(title = "Address", placeHolder = "Fill your address please", description = "This is textarea", noTitle = true, validationMessage = "this is a validation msg", readOnly = true)
	private String address;

	public String getAddress() {
		return address;
	}
}

class CheckBoxForm implements Serializable{
	
	@CheckBox(title = "Color", values = { "red", "blue", "green" }, defaultvalue = "red",required=true)
	private String color;
	
	public String getColor(){
		return color;
	}
}

class CheckBoxForm2 implements Serializable{
	
	@CheckBox(title = "Color", titleMap=MyCheckBoxValues.class, defaultvalue = "red",multiple=true)
	private String color;
	
	public String getColor(){
		return color;
	}
}

class RadioBoxForm implements Serializable{
	
	@RadioBox(title = "Civil State", titleMap = CivilStateValues.class)
	private String civilState;
	
	public String getCivilState(){
		return civilState;
	}
}

class ComboBoxForm implements Serializable{
	
	@ComboBox(title = "Currency", values = { "euro", "dollar" }, required=true)
	private String currency;
	
	public String getCurrency(){
		return currency;
	}
}

class ComboBoxForm2 implements Serializable{
	
	@ComboBox(title = "Gender", titleMap = GenderTitleMap.class)
	private String gender;
	
	public String getGender(){
		return gender;
	}
}

