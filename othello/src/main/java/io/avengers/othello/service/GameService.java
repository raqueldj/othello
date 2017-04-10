package io.avengers.othello.service;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.avengers.othello.dao.GameDao;
import io.avengers.othello.domain.Game;

@Stateless
@Named
public class GameService {
	
	GameDao dao;
	
	 @PersistenceContext
	    EntityManager em;

	    @PostConstruct
	    void after(){
	        System.out.println("=====================@PostConstruct UniverseService=====================");
	        this.dao= new GameDao(em);
	    }
	    
	    public Game create(Game game){
	    	return dao.create(game);
	    	
	    }
	    
	    public void deleteGameById(Integer id){
	    	dao.deleteGameById(id);
	    }


	
	
}
