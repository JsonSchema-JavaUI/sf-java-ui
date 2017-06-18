package io.asfjava.ui.demo.screen;

import java.io.Serializable;

import io.asfjava.ui.core.form.Action;
import io.asfjava.ui.core.form.ActionsGroup;
import io.asfjava.ui.core.form.TextField;

@ActionsGroup({ @Action(title = "Next", type = "button", onClick = "goToNext()"),
		@Action(title = "Previous", type = "button", onClick = "goToPrevious()") })
@Action(title = "Previous", type = "button", onClick = "goToPrevious()")
@Action(title = "Next", type = "button", onClick = "goToNext()")
@Action(title = "Send", type = "submit")
public class DemoForm implements Serializable {

	@TextField(title = "mail", pattern="^\\S+@\\S+$")
	private String mail;

	public String getMail() {
		return mail;
	}

	private static final long serialVersionUID = -5073515619469444978L;
}
