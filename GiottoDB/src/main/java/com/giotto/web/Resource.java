package com.giotto.web;

import java.net.UnknownHostException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.giotto.db.DBManager;
import com.giotto.things.Person;
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
  public String getPerson(String name){
	  try {
		  Person p = new Person(name);
	      return p.toString();
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
		  return DBManager.insert(p);
	  } catch (Exception e) {
	      System.out.println(e);
	  }
	  return false;
  }
}
