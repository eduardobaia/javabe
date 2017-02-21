package br.com.learning.javabe.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("hello")
public class HelloController {


	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getMessage(@QueryParam("user") String user){
		
		return "Bem vindo: " + user ;
		
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("users/{id}")
	public String getUser(@PathParam("id") long id){
		
		return "Recuperando usuario com ID: "+ id;
	}
	
}
