package com.giotto.things;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.util.JSON;

public class Person implements Thing {
	private String _id;
	public String location;
	public String name;
	public JSON other;
	
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Location getLocation(){
		return null;
	}
	
	@Override
	public String toString(){
		HashMap<String,Object> result;
		System.out.println(other);
		try {
			result = new ObjectMapper().readValue(other.toString(), HashMap.class);

			System.out.println(result);
			return result.toString();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
