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
import io.avengers.othello.dto.GameStateDto;
import io.avengers.othello.dto.UserDto;
import io.avengers.othello.service.GameService;
import io.avengers.othello.service.NewGameService;

@Path("new-game")
@Produces(MediaType.APPLICATION_JSON)
@Consumes("application/json")
public class NewGameWs {

	@EJB
	NewGameService service;
	@EJB
	GameService gameService;
	
	@GET
	public List<UserDto> findAllUser(){
		return service.findAllUser();
	}
	
	@POST
	@Path("user")
	@Consumes(MediaType.APPLICATION_JSON)
	public int createUser(CreateUserDto userDto){
		return service.createUser(userDto);
	}

	@POST
	@Path("game")
	@Consumes(MediaType.APPLICATION_JSON)
	public GameStateDto createGame(CreateGameDto gameDto){
		
		int id = service.createGame(gameDto);
		return gameService.getState(id);
	}
}
