package io.asfjava.ui.core.schema;

import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import com.fasterxml.jackson.module.jsonSchema.factories.VisitorContext;
import com.fasterxml.jackson.module.jsonSchema.factories.WrapperFactory;

class CustomSchemaFactoryWrapper extends SchemaFactoryWrapper {

	private static class ASFSchemaFactoryWrapperFactory extends WrapperFactory {
		@Override
		public SchemaFactoryWrapper getWrapper(SerializerProvider p) {
			SchemaFactoryWrapper wrapper = new CustomSchemaFactoryWrapper();
			if (p != null) {
				wrapper.setProvider(p);
			}
			return wrapper;
		};

		@Override
		public SchemaFactoryWrapper getWrapper(SerializerProvider p, VisitorContext rvc) {
			SchemaFactoryWrapper wrapper = new CustomSchemaFactoryWrapper();
			if (p != null) {
				wrapper.setProvider(p);
			}
			wrapper.setVisitorContext(rvc);
			return wrapper;
		}
	};

	CustomSchemaFactoryWrapper() {
		super(new ASFSchemaFactoryWrapperFactory());
		schemaProvider = new CustomJsonSchemaFactory();
	}
}