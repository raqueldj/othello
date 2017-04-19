package io.avengers.othello.ws;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.avengers.othello.dto.CreateGameDto;
import io.avengers.othello.dto.CreateTokenDto;
import io.avengers.othello.dto.GameStateDto;
import io.avengers.othello.dto.ReplayDto;
import io.avengers.othello.dto.TokenCreatedDto;
import io.avengers.othello.service.GameService;

@Path("game")
@Produces(MediaType.APPLICATION_JSON)
@Consumes("application/json")
public class GameWs {

	@EJB
	GameService service;

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public GameStateDto getState(@PathParam("id") int id) {
		return service.getState(id);
		
	
	}
	
	

	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public GameStateDto createToken(CreateTokenDto createTokenDto){
		
		return service.createToken(createTokenDto);
	}
	
	@POST
	@Path("replay")
	@Consumes(MediaType.APPLICATION_JSON)
	public GameStateDto replay(ReplayDto replay){
		
		return service.replay(replay);
	}
	
	
	
	
	
	
	
	
	/*
	 * @GET public List<Game> findAll(){ return service.findAll(); }
	 * 
	 * @GET
	 * 
	 * @Path("{id}") public Game findById(@PathParam("id") int id){ return
	 * service.findById(id).orElseThrow(NotFoundException::new); }
	 * 
	 * @GET
	 * 
	 * @Path("running") public List<Game> findRunning(){ return
	 * service.findRunning(); }
	 * 
	 * @POST
	 * 
	 * @Consumes({MediaType.APPLICATION_JSON}) public Game create(Game game){
	 * return service.create(game); }
	 * 
	 * @DELETE
	 * 
	 * @Path("{id}") public void delete(@PathParam("id") int id){
	 * service.deleteGameById(id); }
	 * 
	 * @PUT
	 * 
	 * @Path("/{id}/run") public void updateGameRunning(@PathParam("id") int
	 * id){ service.updateGameRunning(id); }
	 * 
	 * @PUT
	 * 
	 * @Path("/{id}/stop") public void updateGameNotRunning(@PathParam("id") int
	 * id){ service.updateGameNotRunning(id); }
	 */

}
