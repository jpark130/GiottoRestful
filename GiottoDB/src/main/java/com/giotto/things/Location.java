package com.giotto.things;

import java.util.HashMap;
import java.util.HashSet;

import org.codehaus.jackson.map.ObjectMapper;

public class Location implements Thing {
	String name;
	HashSet<Location> neighbors;
	HashSet<Location> containment;
	
	public Location(String jsonString) throws Exception{
		HashMap result = new ObjectMapper().readValue(jsonString, HashMap.class);
		
		name = (String) result.get("name");
		neighbors = (HashSet<Location>) result.get("neighbors");
		if (neighbors == null || name == null) throw new Exception("Not a proper input");;
		containment = (HashSet<Location>) result.get("containment");
	}

	public String getName() {
		return name;
	}
	
	public HashSet<Location> getNeighbors(){
		return neighbors;
	}

	public HashSet<Location> getContainment() {
		return containment;
	}

}
