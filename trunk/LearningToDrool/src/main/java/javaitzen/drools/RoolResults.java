package javaitzen.drools;

import java.util.HashMap;
import java.util.Map;

public class RoolResults {

	private Map<String, String> results = new HashMap<String, String>();

	public final Map<String, String> getResults() {
		return results;
	}

	public final void setResults(Map<String, String> results) {
		this.results = results;
	}
	
	public void addResult(final String ruleName, final String result) {
		this.results.put(ruleName, result);
	}
}
