package io.asfjava.ui.core.schema;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
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
import io.asfjava.ui.core.form.Tab;
import io.asfjava.ui.core.form.TextField;
import io.asfjava.ui.dto.UiForm;

public class TabbedFormTest {
	
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

	private class TabbedForm implements Serializable{

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
}
