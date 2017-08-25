package io.asfjava.ui.core;

public final class SFJavaUi {

	public static void initialize(){
		GeneratorFactoryLoader.getInstance().load();
		SchemaDecoratorLoader.getInstance().load();
	}
	
	private SFJavaUi() {
	}
}
