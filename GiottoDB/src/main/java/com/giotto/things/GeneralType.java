package com.giotto.things;

import java.util.HashMap;

import org.codehaus.jackson.map.ObjectMapper;

import com.giotto.db.DBManager;

public class GeneralType implements Thing{
	private String name;
	private HashMap<String, Object> pairs;
	private final String[] keyList = {"name", "location", "type", "other"};
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public GeneralType(String map) throws Exception {
		  HashMap result = new ObjectMapper().readValue(map, HashMap.class);
		  pairs = new HashMap<String, Object>();
		  pairs.put("_id", result.get("_id"));
		  name = (String) result.get("name");
		  Location l = new Location((HashMap<String, Object>)result.get("location"));
		  String locationQuery = DBManager.query("Location", l);
		  if (locationQuery == null) {
			  DBManager.insert("Location", l);
			  locationQuery = DBManager.query("Location", l);
		  }
		  pairs.put("location", new Location(locationQuery));
		  pairs.put("name", result.get("name"));
		  pairs.put("type", result.get("type"));
		  pairs.put("other", result.get("other"));
	}
	

	public String[] getRequiredKeys() {
		return keyList;
	}

	public Object getValue(String key) {
		return pairs.get(key);
	}

	public String getName() {
		return name;
	}

}
