package io.avengers.othello.ws;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import io.avengers.othello.domain.Token;
import io.avengers.othello.service.TokenService;

public class TokenWs {

	@EJB
	TokenService tokenService;
	
	@GET
	@Path("{id}")
	public Token findById(@PathParam("id") int id){
		return tokenService.findById(id);
	}
	
	@POST
	public Token create(Token token){
		return tokenService.create(token);
	}
	
	@PUT
	public Token update(Token token){
		return tokenService.update(token);
	}
	
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") int id){
		Token t = tokenService.findById(id);
		tokenService.delete(t);
	}
}
