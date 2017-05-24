package io.asfjava.ui.demo.screen;

import java.io.Serializable;
import java.util.Date;

import io.asfjava.ui.core.form.ComboBox;
import io.asfjava.ui.core.form.TextField;

public class DemoForm implements Serializable {

	@TextField(title = "First Name",placeHolder = "Your first name")
	private String firstName;

	@TextField(title = "Last Name",placeHolder = "Your last name")
	private String lastName;
	
	@ComboBox(title="Gender",values={"Male","Female"})
	private String gender;

	private Date birthDate;

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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getGender() {
		return gender;
	}

	private static final long serialVersionUID = -5073515619469444978L;
}
