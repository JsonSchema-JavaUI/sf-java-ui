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
import io.asfjava.ui.core.form.ComboBox;
import io.asfjava.ui.dto.UiForm;

public class ComboBoxFormTest {
	
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

	private class ComboBoxForm implements Serializable {

		@ComboBox(title = "Currency", values = { "euro", "dollar" }, required = true)
		private String currency;

		public String getCurrency() {
			return currency;
		}
	}

	private class ComboBoxForm2 implements Serializable {

		@ComboBox(title = "Gender", titleMap = GenderTitleMap.class)
		private String gender;

		public String getGender() {
			return gender;
		}
	}
}



