package io.asfjava.ui.demo.screen;

import java.io.Serializable;
import io.asfjava.ui.core.form.Number;

public class NumberDemoForm  implements Serializable {


	@Number(title = "Full Name")
	private String fullName;

	private String mail;

	private String githubRepository;

	private String githubUserName;

	public String getMail() {
		return mail;
	}

	public String getFullName() {
		return fullName;
	}

	public String getGithubRepository() {
		return githubRepository;
	}

	public String getGithubUserName() {
		return githubUserName;
	}

	private static final long serialVersionUID = 8246871107045000185L;
}