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
import io.asfjava.ui.core.form.RadioBox;
import io.asfjava.ui.dto.UiForm;

public class RadioBoxFormTest {
	
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

	private class RadioBoxForm implements Serializable {

		@RadioBox(title = "Civil State", titleMap = CivilStateValues.class)
		private String civilState;

		public String getCivilState() {
			return civilState;
		}
	}
}

