package io.asfjava.ui.core.schema;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

import java.io.Serializable;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.asfjava.ui.core.form.CheckBox;
import io.asfjava.ui.dto.UiForm;

public class CheckBoxFormTest {
	
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
