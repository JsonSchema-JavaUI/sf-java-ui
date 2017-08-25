package io.asfjava.ui.demo.screen;

import java.io.Serializable;

import io.asfjava.ui.core.form.TextArea;

public class DemoForm implements Serializable {

	@TextArea(title = "Comment", description = "Add your Comment here", placeHolder = "fill your comment please")
	private String comment;

	@TextArea(title = "Tweet", placeHolder = "This message will be tweeted", maxLenght = 140, validationMessage = Messages.TWEET_VALIDATION)
	private String tweet;

	@TextArea(title = "Fill a message", minLenght = 50)
	private String message;

	public String getComment() {
		return comment;
	}

	public String getTweet() {
		return tweet;
	}

	public String getMessage() {
		return message;
	}

	private static final long serialVersionUID = -5073515619469444978L;
}
