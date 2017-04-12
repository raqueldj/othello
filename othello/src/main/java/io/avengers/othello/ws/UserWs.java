package io.avengers.othello.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.avengers.othello.domain.User;
import io.avengers.othello.dto.CreateUserDto;
import io.avengers.othello.service.UserService;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes("application/json")
public class UserWs {
	
	@EJB
	UserService service;
	
	@GET
	public List<User> findAll(){
		return service.findAll();
	}
	
	@GET
	@Path("{id}")
	public User findById(@PathParam("id") int id){
		return service.findById(id).orElseThrow(NotFoundException::new);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public User createUser(CreateUserDto userDto){
		return service.createUser(userDto);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteUser(@PathParam("id") Integer id){
		service.deleteUser(id);
	}
	
	@PUT
	public User update(Integer id, User user){
		return service.updateUser(id, user);
	}
	
	@PUT
	@Path("/win/{id}")
	public void updateUserWin(@PathParam("id") int id){
		service.updateUserWin(id);
	}
	
	@PUT
	@Path("/lose/{id}")
	public void updateUserLose(@PathParam("id") int id){
		service.updateUserLose(id);
	}
}
