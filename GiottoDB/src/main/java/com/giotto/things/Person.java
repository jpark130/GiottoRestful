package com.giotto.things;


import java.util.HashMap;
import com.giotto.db.DBManager;
import org.codehaus.jackson.map.ObjectMapper;


public class Person implements Thing {
	public String name;
	private HashMap<String, Object> pairs;
	private final String[] keyList = {"name", "location", "other"};
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Person(String map) throws Exception {
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
		  pairs.put("other", result.get("other"));
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString(){
		return "{_id : " + pairs.get("_id") + ", name :" + name + ", location :" + pairs.get("location") + ", other : " + pairs.get("other") +  "}";
	}

	public String[] getRequiredKeys() {
		return keyList;
	}

	public Object getValue(String key) {
		return pairs.get(key);
	}

}
