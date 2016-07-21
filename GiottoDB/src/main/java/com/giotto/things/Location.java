package com.giotto.things;

import java.util.ArrayList;
import java.util.HashMap;
import org.codehaus.jackson.map.ObjectMapper;

public class Location implements Thing {
	String name;
	private final String[] keyList = {"name","neighbors", "containment"};
	private HashMap<String, Object> pairs;

	@SuppressWarnings("unchecked")
	public Location(String jsonString) throws Exception{
		this(new ObjectMapper().readValue(jsonString, HashMap.class));
	}

	@SuppressWarnings({ "unchecked"})
	public Location(HashMap<String, Object> hashMap) throws Exception {
		pairs = new HashMap<String, Object>();
		pairs.put("_id", hashMap.get("_id"));
		pairs.put("name", hashMap.get("name"));
		name = (String) hashMap.get("name");
		pairs.put("neighbors", (ArrayList<Location>) hashMap.get("neighbors"));
		pairs.put("containment", (ArrayList<Location>) hashMap.get("containment"));
	}

	public String getName() {
		return name;
	}
	
	
	@Override
	public String toString(){
		return "{_id : " + pairs.get("_id") + ", name :" + name + ", neighbors :" + pairs.get("neighbors") + ", containment : " + pairs.get("containment")+  "}";
	}

	public String[] getRequiredKeys() {
		return keyList;
	}

	public Object getValue(String key) {
		return pairs.get(key);
	}

}
