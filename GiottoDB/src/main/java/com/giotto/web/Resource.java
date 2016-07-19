package com.giotto.web;

import java.net.UnknownHostException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.giotto.db.DBManager;
import com.giotto.things.Person;
import com.giotto.things.Thing;
import com.mongodb.util.JSON;

@Path("/people")
public class Resource {
	
  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public long count() throws UnknownHostException {
	  return DBManager.count("People");	  
  }
  
  @POST
  @Path("/find")
  @Consumes({"application/json"})
  public String getPerson(Person name) throws UnknownHostException {
	  System.out.println(name.location);
	  System.out.println(JSON.serialize(name));
	//		return DBManager.query("People", "matthew");	  
	  return JSON.serialize(name);
  }
  
}
