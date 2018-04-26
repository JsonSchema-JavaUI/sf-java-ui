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
import io.asfjava.ui.core.form.Password;
import io.asfjava.ui.dto.UiForm;

public class PasswordFormTest {
	
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

	private class PasswordForm implements Serializable {

		@Password(title = "Password", placeHolder = "Please set you password", pattern = "[a-z]", description = "This is password", noTitle = true, validationMessage = "this is a validation msg", readOnly = true)
		private String password;

		public String getPassword() {
			return password;
		}
	}

	private class PasswordForm2 implements Serializable {

		@Password(title = "Password", placeHolder = "Please set you password", pattern = "[a-z]",minLenght=6,maxLenght=10, fieldAddonRight = "@", description = "This is password", noTitle = true, validationMessage = "this is a validation msg", readOnly = true)
		private String password;

		public String getPassword() {
			return password;
		}
	}

	private class PasswordForm3 implements Serializable {

		@Password(title = "Password", placeHolder = "Please set you password", pattern = "[a-z]",minLenght=6,maxLenght=10, fieldAddonLeft = "@", description = "This is password", noTitle = true, validationMessage = "this is a validation msg", readOnly = true)
		private String password;

		public String getPassword() {
			return password;
		}
	}
}

