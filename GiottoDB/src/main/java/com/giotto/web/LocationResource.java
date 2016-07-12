package com.giotto.web;

import java.net.UnknownHostException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.giotto.db.DBManager;

@Path("location")
public class LocationResource {
	
  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public long count() throws UnknownHostException {
		return DBManager.count("Location");	  
  }
}
