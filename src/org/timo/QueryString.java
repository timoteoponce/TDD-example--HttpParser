package org.timo;

import java.util.HashMap;
import java.util.Map;

public class QueryString {

	private String query;

	private Map<String, String> params = new HashMap<String, String>();

	public QueryString(String query) {
		this.query = query;
		if(query == null){
			throw new NullPointerException("Invalid query");
		}
	}

	public int size() {
		int size = 0;
		init();
		if (!query.isEmpty()) {
			size = params.size();
		}
		return size;
	}

	private void init() {
		if (params.isEmpty() && !query.isEmpty()) {
			// querySTrings have the format param1+value1&param2=value2
			String[] pairs = query.split("&");
			for (String pair : pairs) {
				parsePair(pair);
			}
		}
	}

	private void parsePair(String pair) {
		String[] array = pair.split("=");
		String key = array[0];
		String value = null;
		if (array.length > 1) {
			value = array[1];
		}
		if (!params.containsKey(key)) {
			params.put(key, value);
		}
	}

	public String getValue(String param) {
		init();// just in case
		return params.get(param);
	}

}
