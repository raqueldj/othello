package io.avengers.othello.ws;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.avengers.othello.domain.Game;
import io.avengers.othello.service.GameService;

@Path("game")
@Produces(MediaType.APPLICATION_JSON)
@Consumes("application/json")
public class GameWs {
	
	@EJB
	GameService service;
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public Game create(Game game){
		return service.create(game);
	}
	
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") int id){
		service.deleteGameById(id);
	}

	

}
