package com.giotto.things;


import java.util.HashMap;
import com.giotto.db.DBManager;
import org.codehaus.jackson.map.ObjectMapper;


public class Person implements Thing {
	public Location location;
	public String name;
	public HashMap<String, String> other;
	public Object _id;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Person(String map) throws Exception {
		  HashMap result = new ObjectMapper().readValue(map, HashMap.class);
		  _id = result.get("_id");
		  Location l = new Location((HashMap<String, Object>)result.get("location"));
		  String locationQuery = DBManager.query("Location", l);
		  if (locationQuery == null) {
			  DBManager.insert("Location", l);
			  locationQuery = DBManager.query("Location", l);
		  }
		  location = new Location(locationQuery);
		  name = (String) result.get("name");
		  if (location == null || name == null) throw new Exception("Not a proper input");
		  other = (HashMap<String, String>) result.get("other");
	}
	
	public String getName() {
		return name;
	}
	
	public Location getLocation(){
		return location;
	}
	
	@Override
	public String toString(){
		return "{_id : " + _id + ", name :" + name + ", location :" + location + ", other : " + other +  "}";
	}

}
