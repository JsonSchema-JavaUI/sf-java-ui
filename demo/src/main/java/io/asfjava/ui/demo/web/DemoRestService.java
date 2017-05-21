package io.asfjava.ui.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonMappingException;

import io.asfjava.ui.demo.renderer.UiDemoRenderer;
import io.asfjava.ui.schema.dto.UiForm;

/**
 * REST controller for managing Ui.
 */
@RestController
@RequestMapping("/api")
public class DemoRestService {

	@Autowired
	private final UiDemoRenderer renderer;

	public DemoRestService(UiDemoRenderer renderer) {
		this.renderer = renderer;
	}

	@RequestMapping("/ui")
	public UiForm renderUI() throws JsonMappingException {
		return renderer.renderForm();
	}
}
