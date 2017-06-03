package io.asfjava.ui.demo.screen;

import java.io.Serializable;

import io.asfjava.ui.core.form.ComboBox;
import io.asfjava.ui.core.form.RadioBox;
import io.asfjava.ui.core.form.TextArea;
import io.asfjava.ui.core.form.TextField;
import io.asfjava.ui.core.form.Password;
import io.asfjava.ui.core.form.Number;

public class DemoForm implements Serializable {

	@TextField(title = "First Name", placeHolder = "Your first name", description="This is a description for your first name field")
	private String firstName;

	@TextField(title = "Last Name", placeHolder = "Your last name")
	private String lastName;
	
	@Number(title = "Number of children", placeHolder = "Number of children . 0 if you haven't", description = "This is a number")
	private Integer number;
	
	@Password(title = "Password", placeHolder = "Please set you password", description = "This is password")
	private String password;
	
	@ComboBox(title = "Gender", values = { "Male", "Female" })
	private String gender;
	
	
	@RadioBox(title = "Civil State" , titleMap = CivilStateTitelsMap.class)
	private String civilState;

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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public String getCivilState() {
		return civilState;
	}

	public void setCivilState(String civilState) {
		this.civilState = civilState;
	}

	private static final long serialVersionUID = -5073515619469444978L;
}
