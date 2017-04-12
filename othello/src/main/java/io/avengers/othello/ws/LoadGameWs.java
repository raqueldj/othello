package io.avengers.othello.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.avengers.othello.dto.GameDto;
import io.avengers.othello.service.LoadGameService;

@Path("load-game")
@Produces(MediaType.APPLICATION_JSON)
@Consumes("application/json")
public class LoadGameWs {

	@EJB
	LoadGameService loadGameService;
	
	@GET
	public List<GameDto> findAllGamesRunning(){
		return loadGameService.findAllGamesRunning();
	}
}
