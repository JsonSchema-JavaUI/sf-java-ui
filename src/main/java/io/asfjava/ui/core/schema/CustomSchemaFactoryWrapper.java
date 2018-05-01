package io.asfjava.ui.core.schema;

import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import com.fasterxml.jackson.module.jsonSchema.factories.VisitorContext;
import com.fasterxml.jackson.module.jsonSchema.factories.WrapperFactory;

class CustomSchemaFactoryWrapper extends SchemaFactoryWrapper {

	CustomSchemaFactoryWrapper(SchemaDecoratorHandler schemaDecoratorHandler) {
		super(new SFSchemaFactoryWrapperFactory(schemaDecoratorHandler));
		schemaProvider = new CustomJsonSchemaFactory(schemaDecoratorHandler);
	}

	private static class SFSchemaFactoryWrapperFactory extends WrapperFactory {

		private final SchemaDecoratorHandler schemaDecoratorHandler;

		private SFSchemaFactoryWrapperFactory(SchemaDecoratorHandler schemaDecoratorHandler) {
			this.schemaDecoratorHandler = schemaDecoratorHandler;
		}

		@Override
		public SchemaFactoryWrapper getWrapper(SerializerProvider p, VisitorContext rvc) {
			SchemaFactoryWrapper wrapper = new CustomSchemaFactoryWrapper(schemaDecoratorHandler);
			if (p != null) {
				wrapper.setProvider(p);
			}
			wrapper.setVisitorContext(rvc);
			return wrapper;
		}
	}
}