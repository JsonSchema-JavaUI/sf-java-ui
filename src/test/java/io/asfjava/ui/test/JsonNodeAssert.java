package io.asfjava.ui.test;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.Assert;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonNodeAssert {
	
	JsonNode actual;
	
	public JsonNodeAssert(JsonNode node) {
		this.actual = node;
	}
	
	public void assertEquals(String path, String expected){
		Assert.assertEquals(expected, traverse(actual, path).asText());
	}
	
	public void assertTrue(String path){
		Assert.assertTrue(traverse(actual, path).asBoolean());
	}
	
	public void assertFalse(String path){
		Assert.assertFalse(traverse(actual, path).asBoolean());
	}
	
	private JsonNode traverse(JsonNode node, String path) {
		if (path == null || path.isEmpty()) {
			return node;
		}
		String[] tokens = path.split("\\.");
		Iterator<String> itr = Arrays.asList(tokens).iterator();
		while (itr.hasNext()) {
			String token = itr.next();
			if (isIndex(token)) {
				node = node.path(Integer.parseInt(token));
			} else {
				node = node.path(token);
			}
			if (node.isMissingNode()) {
				Assert.fail("path: " + path + " does not exist in the given json node");
			}
		}

		return node;
	}
	
	private boolean isIndex(String path){
		return path.matches("\\d+");
	}

}
