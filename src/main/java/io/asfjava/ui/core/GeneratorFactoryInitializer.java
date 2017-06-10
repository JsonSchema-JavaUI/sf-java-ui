package io.asfjava.ui.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class GeneratorFactoryInitializer implements ServletContextListener {

	@Override
	public final void contextInitialized(final ServletContextEvent sce) {
		GeneratorFactoryLoader.getInstance().load();
		SchemaDecoratorLoader.getInstance().load();
	}

	@Override
	public final void contextDestroyed(final ServletContextEvent sce) {
     //to implement it
	}
}
