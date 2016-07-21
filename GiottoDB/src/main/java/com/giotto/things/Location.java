package com.giotto.things;

import java.util.ArrayList;
import java.util.HashMap;
import org.codehaus.jackson.map.ObjectMapper;

public class Location implements Thing {
	Object _id;
	String name;
	ArrayList<Location> neighbors;
	ArrayList<Location> containment;
	
	@SuppressWarnings("unchecked")
	public Location(String jsonString) throws Exception{
		this(new ObjectMapper().readValue(jsonString, HashMap.class));
	}

	@SuppressWarnings({ "unchecked"})
	public Location(HashMap<String, Object> hashMap) throws Exception {
		_id = hashMap.get("_id");
		name = (String) hashMap.get("name");
		neighbors = (ArrayList<Location>) hashMap.get("neighbors");
		if (neighbors == null || name == null) throw new Exception("Not a proper input");;
		containment = (ArrayList<Location>) hashMap.get("containment");	
	}

	public String getName() {
		return name;
	}
	
	public ArrayList<Location> getNeighbors(){
		return neighbors;
	}

	public ArrayList<Location> getContainment() {
		return containment;
	}
	
	@Override
	public String toString(){
		return "{_id : " + _id + ", name :" + name + ", neighbors :" + neighbors + ", containment : " + containment +  "}";
	}

	public Object getLocationID() {
		return _id;
	}

}
