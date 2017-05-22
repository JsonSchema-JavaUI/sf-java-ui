package io.asfjava.ui.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
class GeneratorFactoryInitializer implements ServletContextListener {

	@Override
	public final void contextInitialized(final ServletContextEvent sce) {
		GeneratorFactoryLoader.getInstance().load();
	}

	@Override
	public final void contextDestroyed(final ServletContextEvent sce) {

	}
}
