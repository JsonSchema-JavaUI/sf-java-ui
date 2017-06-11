package io.asfjava.ui.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;

public class UiForm implements Serializable {

	private JsonSchema schema;
	private ArrayNode form;

	public UiForm(JsonSchema schema, ArrayNode form) {
		setSchema(schema);
		setForm(form);
	}

	public JsonSchema getSchema() {
		return schema;
	}

	public void setSchema(JsonSchema schema) {
		this.schema = schema;
	}

	public ArrayNode getForm() {
		return form;
	}

	public void setForm(ArrayNode form) {
		this.form = form;
	}

	private static final long serialVersionUID = -2534915468830780L;
}
