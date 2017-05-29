package io.asfjava.ui.demo.screen;

import java.io.Serializable;

import io.asfjava.ui.core.form.ComboBox;
import io.asfjava.ui.core.form.TextArea;
import io.asfjava.ui.core.form.TextField;

public class DemoForm implements Serializable {

	@TextField(title = "First Name", placeHolder = "Your first name", description="This is a description for your first name field")
	private String firstName;

	@TextField(title = "Last Name", placeHolder = "Your last name")
	private String lastName;

	@ComboBox(title = "Gender", values = { "Male", "Female" })
	private String gender;

	@TextArea(title = "Address", placeHolder = "Fill your address please", description = "This is textarea")
	private String address;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private static final long serialVersionUID = -5073515619469444978L;
}
