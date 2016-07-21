package com.giotto.things;

public interface Thing {
	
	public String getName();
	
	public String[] getRequiredKeys();
	
	public Object getValue(String key);
}
