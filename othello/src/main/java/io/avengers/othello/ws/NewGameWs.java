package io.avengers.othello.ws;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.avengers.othello.service.NewGameService;

@Path("NewGame")
@Produces(MediaType.APPLICATION_JSON)
@Consumes("application/json")
public class NewGameWs {

	@EJB
	NewGameService service;
	
	
}
