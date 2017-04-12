package io.avengers.othello.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.avengers.othello.dto.CreateGameDto;
import io.avengers.othello.dto.CreateUserDto;
import io.avengers.othello.dto.UserDto;
import io.avengers.othello.service.NewGameService;

@Path("new-game")
@Produces(MediaType.APPLICATION_JSON)
@Consumes("application/json")
public class NewGameWs {

	@EJB
	NewGameService service;
	
	@GET
	public List<UserDto> findAllUser(){
		return service.findAllUser();
	}
	
	@POST
	@Path("user")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createUser(CreateUserDto userDto){
		service.createUser(userDto);
	}

	@POST
	@Path("game")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createGame(CreateGameDto gameDto){
		service.createGame(gameDto);
	}
}
