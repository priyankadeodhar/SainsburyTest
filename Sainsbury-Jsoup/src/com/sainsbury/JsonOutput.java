package com.sainsbury;

import java.util.List;

public class JsonOutput {
	
	private List<Product> results;
	private Total total;
	
	
	public List<Product> getResults() {
		return results;
	}
	public void setResults(List<Product> results) {
		this.results = results;
	}
	public Total getTotal() {
		return total;
	}
	public void setTotal(Total total) {
		this.total = total;
	}
	
}
