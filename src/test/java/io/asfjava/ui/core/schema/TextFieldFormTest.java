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
import io.asfjava.ui.core.form.TextField;
import io.asfjava.ui.dto.UiForm;

public class TextFieldFormTest {
	
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

}

class TextFieldForm implements Serializable {

	@TextField(title = "First Name", placeHolder = "Your first name", pattern = "[a-z]", noTitle = true, validationMessage = "this is a validation msg", description = "This is a description for your first name field", readOnly = true)
	private String firstName;

	public String getFirstName() {
		return firstName;
	}

}

class TextFieldFormRight implements Serializable {

	@TextField(title = "First Name", placeHolder = "Your first name", fieldAddonRight = "@", pattern = "[a-z]", noTitle = true, validationMessage = "this is a validation msg", description = "This is a description for your first name field")
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
