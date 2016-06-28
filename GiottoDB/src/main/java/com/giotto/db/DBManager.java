package com.giotto.db;

import java.net.UnknownHostException;

import com.giotto.things.*;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
public class DBManager {

	//Overloaded method for inserting people
	public static boolean insert(Person thing) throws UnknownHostException{
		MongoClient m = new MongoClient("52.0.136.240",27017);
//		MongoClient m = new MongoClient("0.0.0.0",27017);
		MongoDatabase db = m.getDatabase("Giotto");
		System.out.println(db.getCollection("People").count());
		m.close();
		return true;
	}
	
	//Overloaded method for inserting location 
	//to insert a location make json -> location 
	//then add it to the database
	public static boolean insert(Location thing) throws UnknownHostException{
		MongoClient client = new MongoClient();
		DB db = client.getDB("Giotto");
		DBCollection locations = db.getCollection("Location");
		BasicDBObject document = new BasicDBObject();
		document.append("name", thing.getName());
		document.append("neighbors", thing.getNeighbors());
		document.append("contains", thing.getContainment());
		locations.insert(document);
		return true;

	}
	
	/** 
	 * 
	 * Counts the number of elements in the given database
	 * 
	 * @param the name of the database
	 * */
	public static long count(String name){
		MongoClient m = new MongoClient("52.0.136.240",27017);
//		MongoClient m = new MongoClient("0.0.0.0",27017);
		MongoDatabase db = m.getDatabase("Giotto");
		long count = db.getCollection(name).count();
		m.close();
		return count;
	}
	
	public static void update(String name, String newLocation) throws UnknownHostException{
		MongoClient client = new MongoClient();
		DB db = client.getDB("Giotto");
		DBCollection people = db.getCollection("People");
		DBObject target;
		if ((target = people.find().getQuery()) != null){
			people.update(target, target);//TODO : change this to new
		};
		
	}

	public static WriteResult delete(Thing thing) throws UnknownHostException{
		MongoClient client = new MongoClient();
		DB db = client.getDB("Giotto");
		if (thing.getClass().getName().equals("Person")){
			DBCollection people = db.getCollection("People");
			BasicDBObject document = new BasicDBObject();
			document.append("name", thing.getName());
			document.append("location", ((Person)thing).getLocation());
			return people.remove(document);
		}
		else {
			BasicDBObject document = new BasicDBObject();
			document.append("name", thing.getName());
			document.append("neighbors", ((Location)thing).getNeighbors());
			document.append("contains", ((Location)thing).getContainment());
			return db.getCollection("Location").remove(document);
		}
	}
	
}
