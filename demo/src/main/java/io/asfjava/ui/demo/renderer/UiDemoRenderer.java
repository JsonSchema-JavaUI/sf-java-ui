package io.asfjava.ui.demo.renderer;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonMappingException;

import io.asfjava.ui.core.schema.UiFormSchemaGenerator;
import io.asfjava.ui.demo.screen.DemoForm;
import io.asfjava.ui.dto.UiForm;

@Service
public class UiDemoRenderer {
	public UiForm renderForm() throws JsonMappingException {
		return UiFormSchemaGenerator.get().generate(DemoForm.class);
	}
}
