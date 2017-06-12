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
import io.asfjava.ui.core.form.Number;
import io.asfjava.ui.dto.UiForm;

public class NumberFormTest {
	
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
