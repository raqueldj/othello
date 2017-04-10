package io.avengers.othello.ws;

import javax.ejb.EJB;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.avengers.othello.domain.User;
import io.avengers.othello.service.UserService;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes("application/json")
public class UserWs {
	
	@EJB
	UserService service;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public User createUser(User user){
		return service.createUser(user);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteUser(@PathParam("id") Integer id){
		
	}
	
}
