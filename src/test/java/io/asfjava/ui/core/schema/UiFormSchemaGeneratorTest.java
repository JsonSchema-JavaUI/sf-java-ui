package io.asfjava.ui.core.schema;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

import java.io.Serializable;

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
import io.asfjava.ui.core.form.TextArea;
import io.asfjava.ui.core.form.TextField;
import io.asfjava.ui.dto.UiForm;

public class UiFormSchemaGeneratorTest {

	@BeforeClass
	public static void init() {
		new GeneratorFactoryInitializer().contextInitialized(null);
	}

	@Test
	public void testGenerate_textField() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(TextFieldForm.class);
		String json = new ObjectMapper().writeValueAsString(ui);
		

		Assert.assertThat(json, hasJsonPath("$.schema.properties.firstName.title",equalTo("First Name")));
		Assert.assertThat(json, hasJsonPath("$.schema.properties.firstName.pattern",equalTo("[a-z]")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='firstName')].key",hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='firstName')].description",hasItem("This is a description for your first name field")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='firstName')].placeholder",hasItem("Your first name")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='firstName')].validationMessage",hasItem("this is a validation msg")));
		//Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='firstName')].type",hasItem("textField")));
		
	}

	@Test
	public void testGenerate_Number() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(NumberForm.class);
		String json = new ObjectMapper().writeValueAsString(ui);
		//Assert.assertThat(json, hasJsonPath("$.schema.properties.number.title",equalTo("Number")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].key",hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].description",hasItem("This is a number")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].placeholder",hasItem("Number of children")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].validationMessage",hasItem("this is a validation msg")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].type",hasItem("number")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].notitle",hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='number')].readonly",hasItem(true)));

	}

	@Test
	public void testGenerate_Password() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(PasswordForm.class);
		
		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.password.title",equalTo("Password")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].key",hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].description",hasItem("This is password")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].placeholder",hasItem("Please set you password")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].validationMessage",hasItem("this is a validation msg")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].type",hasItem("password")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].notitle",hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].readonly",hasItem(true)));
	}

	@Test
	public void testGenerate_TextArea() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(TextAreaForm.class);
		
		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.address.title",equalTo("Address")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='address')].key",hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='address')].description",hasItem("This is textarea")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='address')].placeholder",hasItem("Fill your address please")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='address')].validationMessage",hasItem("this is a validation msg")));
		//Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='password')].type",hasItem("textArea")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='address')].notitle",hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='address')].readonly",hasItem(true)));

	}

	@Test
	public void testGenerate_CheckBox() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(CheckBoxForm.class);
		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.color.title",equalTo("Color")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')].key",hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')].multiple",hasItem(false)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')].required",hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')].titleMap[0].name",hasItem("Red")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')].titleMap[0].value",hasItem("red")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')].titleMap[1].name",hasItem("Blue")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')].titleMap[1].value",hasItem("blue")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')].titleMap[2].name",hasItem("Green")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')].titleMap[2].value",hasItem("green")));
	}

	@Test
	public void testGenerate_CheckBox_WithCustomValuesContainer() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(CheckBoxForm2.class);
		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.color.title",equalTo("Color")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')].key",hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')].multiple",hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')].required",hasItem(false)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')].titleMap[?(@.name=='Red')].value",hasItem("red")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')].titleMap[?(@.name=='Blue')].value",hasItem("blue")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='color')].titleMap[?(@.name=='Green')].value",hasItem("green")));
	}

	@Test
	public void testGenerate_RadioBox() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(RadioBoxForm.class);
		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.civilState.title",equalTo("Civil State")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='civilState')].key",hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='civilState')].readOnly",hasItem(false)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='civilState')].titleMap[?(@.name=='Maried')].value",hasItem("COMMITTED")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='civilState')].titleMap[?(@.name=='Single')].value",hasItem("HAPPY")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='civilState')].titleMap[?(@.name=='Divorced')].value",hasItem("RELEASED")));

	}

	@Test
	public void testGenerate_ComboBox() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(ComboBoxForm.class);
		
		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.currency.title",equalTo("Currency")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='currency')].key",hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='currency')].disabled",hasItem(false)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='currency')].multiple",hasItem(false)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='currency')].required",hasItem(true)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='currency')].autofocus",hasItem(false)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='currency')].titleMap[?(@.name=='Euro')].value",hasItem("euro")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='currency')].titleMap[?(@.name=='Dollar')].value",hasItem("dollar")));

	}

	@Test
	public void testGenerate_ComboBox_WithCustomValuesContainer() throws JsonProcessingException {
		UiForm ui = UiFormSchemaGenerator.get().generate(ComboBoxForm2.class);
		
		String json = new ObjectMapper().writeValueAsString(ui);
		Assert.assertThat(json, hasJsonPath("$.schema.properties.gender.title",equalTo("Gender")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='gender')].key",hasSize(1)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='gender')].disabled",hasItem(false)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='gender')].multiple",hasItem(false)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='gender')].required",hasItem(false)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='gender')].autofocus",hasItem(false)));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='gender')].titleMap[?(@.name=='Male')].value",hasItem("male")));
		Assert.assertThat(json, hasJsonPath("$.form[?(@.key=='gender')].titleMap[?(@.name=='Female')].value",hasItem("female")));

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
