package com.giotto.web;

import java.net.UnknownHostException;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.giotto.db.DBManager;
import com.giotto.things.Person;
import com.mongodb.util.JSON;



/**
 * 
 * APIs for querying people
 * */
@Path("/people")
public class Resource {
	
  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public long count() throws UnknownHostException {
	  return DBManager.count("People");	  
  }
  
  @SuppressWarnings("unchecked")
@POST
  @Path("/find")
  @Consumes({"application/json"})
  public String getPerson(String jsonString){
	  try {
		  HashMap<String, Object> map = new ObjectMapper().readValue(jsonString, HashMap.class);
	      return JSON.serialize(DBManager.search("People", map));
	  } catch (Exception e) {
		  System.out.println(e);
	  }
	  return "";
  }
  
  @POST
  @Path("/post")
  @Consumes({"application/json"})
  public boolean addPerson(String person){
	  try {
		  Person p = new Person(person);
		  return DBManager.insert("People", p);
	  } catch (Exception e) {
	      System.out.println(e);
	  }
	  return false;
  }
  
  @DELETE
  @Path("/delete")
  @Consumes({"application/json"})
  public boolean removeLocation(String jsonString){
	  try{
		  Person l = new Person(jsonString);
		  return DBManager.delete("People", l);
	  } catch (Exception e){
		  System.out.println(e);
	  }
	  return false;
  }
}
