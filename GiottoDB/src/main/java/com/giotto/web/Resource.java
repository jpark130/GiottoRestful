package com.giotto.web;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

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
  public String getPerson(String name) throws JsonParseException, JsonMappingException, IOException {
	  System.out.println(name);
	  HashMap result = new ObjectMapper().readValue(name, HashMap.class);
	  HashMap other = (HashMap) (result.get("other"));
	  System.out.println(other.get("HI"));
	  
//	  return DBManager.query("People", "matthew");
	  return "";
  }
  
}
