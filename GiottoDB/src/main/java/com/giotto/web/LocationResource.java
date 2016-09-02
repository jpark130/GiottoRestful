package com.giotto.web;

import java.net.UnknownHostException;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.DELETE;


import com.giotto.db.DBManager;
import com.giotto.things.Location;
import com.mongodb.util.JSON;




/**
 * 
 * APIs for querying location
 * */

@Path("/location")
public class LocationResource {
	
  @GET
  @Path("/count")
  @Produces(MediaType.TEXT_PLAIN)
  public long count() throws UnknownHostException {
		return DBManager.count("Location");	  
  }
  
  @SuppressWarnings("unchecked")
  @POST
  @Path("/find")
  @Consumes({"application/json"})
  public String getLocation(String jsonString){
	  try {
		  HashMap<String, Object> map = new ObjectMapper().readValue(jsonString, HashMap.class);
	      return JSON.serialize(DBManager.search("Location", map));
	  } catch (Exception e) {
		  System.out.println(e);
	  }
	  return "";
  }
  
  @POST
  @Path("/post")
  @Consumes({"application/json"})
  public boolean postLocation(String jsonString){
	  try {
		  Location l = new Location(jsonString);
		  return DBManager.insert("Location", l);
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
		  Location l = new Location(jsonString);
		  return DBManager.delete("Location", l);
	  } catch (Exception e){
		  System.out.println(e);
	  }
	  return false;
  }
}
