package io.asfjava.ui.core.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ASFUILogger {

	private final static Logger LOGGER = LoggerFactory.getLogger("ASFUILogger");

	public static Logger getLogger() {
		return LOGGER;
	}

	private ASFUILogger() {
	}

}
