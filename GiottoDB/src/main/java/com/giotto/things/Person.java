package com.giotto.things;

public class Person implements Thing {
	private String _id;
	public String location;
	public String name;
	
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Location getLocation(){
		return null;
	}
	
	@Override
	public String toString(){
		return "Name :" + name + ", location : " + location;
	}

}
