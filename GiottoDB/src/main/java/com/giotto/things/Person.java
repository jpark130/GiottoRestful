package com.giotto.things;


import java.util.HashMap;

import org.codehaus.jackson.map.ObjectMapper;


public class Person implements Thing {
	public String location;
	public String name;
	public HashMap<String, String> other;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Person(String map) throws Exception {
		  HashMap result = new ObjectMapper().readValue(map, HashMap.class);
		  location = (String) result.get("location");
		  name = (String) result.get("name");
		  if (location == null || name == null) throw new Exception("Not a proper input");;
		  other = (HashMap<String, String>) result.get("other");
	}
	
	public String getName() {
		return name;
	}
	
	public Location getLocation(){
		return null;
	}
	
	@Override
	public String toString(){
		return location + name + other.toString();
	}

}
