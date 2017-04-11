package io.avengers.othello.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.avengers.othello.domain.Token;
import io.avengers.othello.service.TokenService;

@Path("token")
@Produces(MediaType.APPLICATION_JSON)
@Consumes("application/json")
public class TokenWs {

	@EJB
	TokenService tokenService;
	
	@GET
	@Path("{id}")
	public Token findById(@PathParam("id") int id){
		return tokenService.findById(id);
	}
	
	@GET
	@Path("/game/{id}")
	public List<Token> findByGame(@PathParam("id") int id){
		return tokenService.findByGame(id);
	}
	
	@POST
	public Token create(Token token){
		return tokenService.create(token);
	}
	
	@PUT
	public Token update(Token token){
		return tokenService.update(token);
	}
	
	@PUT
	@Path("/switch/{id}")
	public void updateTokenSwitch(@PathParam("id") int id){
		tokenService.updateTokenSwitch(id);
	}
	
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") int id){
		Token t = tokenService.findById(id);
		tokenService.delete(t);
	}
	
	
}
