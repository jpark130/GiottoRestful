package com.giotto.web;

import java.net.UnknownHostException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.giotto.db.DBManager;
import com.giotto.things.Person;

@Path("home")
public class Resource {
	
  @GET
  @Path("hello")
  @Produces(MediaType.TEXT_PLAIN)
  public long helloWorld() throws UnknownHostException {
	return DBManager.count("People");	  
  }
}
