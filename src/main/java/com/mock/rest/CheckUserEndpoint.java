package com.mock.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mysql.entities.User;


@Path("/checkusers")
public class CheckUserEndpoint {

	@GET
	@Path("/json/{name}")
	@Produces("application/json")
	public Response findByFirstNameJSON(@PathParam("name") final String firstname) {
		//CheckUser checkuser = null;
		
		User savedUser = CheckUser.getUser(firstname);
		if (savedUser == null) {
			return Response.status(Status.NOT_FOUND).entity("User Not Found!!!").build();
		}
		return Response.ok(savedUser).build();
	}

	/*
	@GET
	@Path("/xml/{name}")
	@Produces("application/xml")
	public Response findByFirstNameXML(@PathParam("name") final String firstname) {
		//TODO: retrieve the checkuser 
		//CheckUser checkuser = null;
		
		String fullname = CheckUser.getFullname(firstname);
		if (fullname == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(fullname).build();
	}
	*/
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateUserLogin(User user) {
 
		if(null==user || null==user.getUsername()){
			return Response.status(Status.BAD_REQUEST).entity("Wrong user Info : Username is null" + user.getUsername()).build();
		}
		
		User savedUser = CheckUser.validateForLoginMySQL(user);
		if (savedUser == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(201).entity(savedUser).build();
 
	}
	
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {
 
		if(null==user || null==user.getUsername()){
			return Response.status(Status.BAD_REQUEST).entity("Wrong user Reg Info : Username is null").build();
		}
		
		User savedUser = CheckUser.createUserMySQL(user);
		if (savedUser == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.status(201).entity(savedUser).build();
 
	}
	
	
}
