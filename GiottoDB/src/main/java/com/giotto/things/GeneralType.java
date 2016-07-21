package com.giotto.things;

import java.util.HashMap;

import org.codehaus.jackson.map.ObjectMapper;

import com.giotto.db.DBManager;

public class GeneralType implements Thing{
	private String name;
	private Object _id;
	private String type;
	private Location location;
	private HashMap<String, String> other;
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public GeneralType(String map) throws Exception {
		HashMap result = new ObjectMapper().readValue(map, HashMap.class);
		_id = result.get("_id");
		name = (String) result.get("name");
		type = (String) result.get("neighbors");
		Location l = new Location((HashMap<String, Object>)result.get("location"));
		String locationQuery = DBManager.query("Location", l);
		if (locationQuery == null) {
			DBManager.insert("Location", l);
			locationQuery = DBManager.query("Location", l);
		}
		location = new Location(locationQuery);
		if (type == null || name == null || location == null) throw new Exception("Not a proper input");
		other = (HashMap<String, String>) result.get("other");

	}
	
	public Location getLocation(){
		return location;
	}
	
	public HashMap<String, String> getOther(){
		return other;
	}

	public String getName() {
		return name;
	}
	
	public String getType(){
		return type;
	}
	
	public Object getID(){
		return _id;
	}

}
