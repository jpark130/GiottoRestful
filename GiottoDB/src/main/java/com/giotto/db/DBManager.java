package com.giotto.db;

import java.net.UnknownHostException;
import java.util.logging.Level;

import org.bson.Document;

import com.giotto.things.*;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.util.JSON;
public class DBManager {

//	private final static String DB_ADDRESS = "52.0.136.240";
	private final static String DB_ADDRESS = "0.0.0.0";
	private final static int PORT = 27017;
	
	/** 
	 * 
	 * DB Method for insertion
	 * 
	 * @param new thing data
	 * */	
	public static boolean insert(String dbTitle, Thing thing) throws UnknownHostException{
		java.util.logging.Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
		MongoClient m = new MongoClient(DB_ADDRESS,PORT); //AWS
		MongoDatabase db = m.getDatabase("Giotto");		
		MongoCollection<Document> people = db.getCollection(dbTitle);
		Document document = makeDoc(thing);
		people.insertOne(document);
		m.close();
		System.out.println("Successfully inserted " + thing.getName() + "!");
		return true;
	}
	

	/** 
	 * 
	 * Counts the number of elements in the given database
	 * 
	 * @param the name of the database
	 * */
	public static long count(String name){
		java.util.logging.Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
		MongoClient m = new MongoClient(DB_ADDRESS,PORT);
		MongoDatabase db = m.getDatabase("Giotto");
		long count = db.getCollection(name).count();
		m.close();
		return count;
	}
	
	/** 
	 * 
	 * Overloaded method for querying location
	 * 
	 * @param new location data
	 * @throws UnknownHostException 
	 * */
	public static String query(String name, Thing thing) throws UnknownHostException{
		java.util.logging.Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
		MongoClient m = new MongoClient(DB_ADDRESS,PORT); //AWS
		System.out.println("Querying for " + name + "...");
		MongoDatabase db = m.getDatabase("Giotto");
		Document queryDoc = makeDoc(thing);
		Document result = db.getCollection(name).find(queryDoc).first();
		if (result == null) {
			m.close();
			return null;
		}
		String output = JSON.serialize(result); 
		m.close();
		System.out.println("Found " + thing.getName() + "!");
		return output;
	}
	
	public static void update(String name, String newLocation) throws UnknownHostException{
		java.util.logging.Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
		MongoClient client = new MongoClient();
		DB db = client.getDB("Giotto");
		DBCollection people = db.getCollection("People");
		DBObject target;
		if ((target = people.find().getQuery()) != null){
			people.update(target, target);//TODO : change this to new
		};
		
	}

	public static DeleteResult delete(String dbTitle, Thing thing) throws UnknownHostException{
		java.util.logging.Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
		
		MongoClient m = new MongoClient(DB_ADDRESS,PORT); //AWS
		System.out.println("Deleting " + thing.getName() + "...");
		MongoDatabase db = m.getDatabase("Giotto");
		Document queryDoc = makeDoc(thing);
		
		MongoCollection<Document> dbCollection = db.getCollection(dbTitle);
		m.close();
		return dbCollection.deleteMany(queryDoc);
	}
	
	private static Document makeDoc(Thing thing){
		Document queryDoc = new Document();
		if (thing instanceof Location){
			Location location = (Location) thing;
			queryDoc.append("name", location.getName());
			queryDoc.append("neighbors", location.getNeighbors());
			queryDoc.append("containment", location.getContainment());
		}
		else if (thing instanceof Person){
			Person person = (Person) thing;
			queryDoc.append("name", person.getName());
			queryDoc.append("location", person.getLocation().toString());
			queryDoc.append("other", person.other);
		}
		return queryDoc;
	}
	
}
