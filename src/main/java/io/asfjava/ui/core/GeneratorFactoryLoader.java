package io.asfjava.ui.core;

final class GeneratorFactoryLoader {

	void load() {
		System.out.println("I'm loader");
	}

	void unload() {
		System.out.println("I'm unloader");
	}

	static GeneratorFactoryLoader getInstance() {
		if (INSTANCE == null)
			INSTANCE = new GeneratorFactoryLoader();
		return INSTANCE;
	}

	private static GeneratorFactoryLoader INSTANCE;

	private GeneratorFactoryLoader() {
	}
}
