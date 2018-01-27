package io.asfjava.ui.demo.screen.dto;

import java.io.Serializable;

import io.asfjava.ui.core.form.DisplayAllFields;
import io.asfjava.ui.core.form.Index;
import io.asfjava.ui.core.form.TextField;

@DisplayAllFields
public class Event implements Serializable {

	@Index(0)
	@TextField(title = "Title", placeHolder = "Event's title")
	private String title;

	@Index(2)
	@TextField(title = "Location", placeHolder = "Event's location")
	private String location;

	@Index(1)
	private Speaker speaker;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
}
