package io.asfjava.ui.demo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
class GeneratorFactoryInitializer implements ServletContextListener {

	@Override
	public final void contextInitialized(final ServletContextEvent sce) {
		System.out.println("Hellooooo ");
	}

	@Override
	public final void contextDestroyed(final ServletContextEvent sce) {

	}
}
