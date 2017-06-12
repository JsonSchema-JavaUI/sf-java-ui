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

import io.asfjava.ui.core.form.TextArea;
import io.asfjava.ui.dto.UiForm;

public class TextAreaFormTest {
	
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
