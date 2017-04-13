package io.avengers.othello.service;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.avengers.othello.domain.Game;
import io.avengers.othello.domain.Token;
import io.avengers.othello.domain.User;

@Startup
@Singleton
public class StartUpService {

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	void after() {
		System.out.println("====================AFTER STARTUP=======================");
		createData();
	}

	void createData() {
		User bob = new User("Bob");
		User jack = new User("Jack");
		User boby = new User("Boby");
		
		

		
		Game game = new Game(bob,jack);
		Token token1 = new Token(false, 4,5,game);
		Token token2 = new Token(false, 5,4,game);
		Token token3 = new Token(true, 4,4,game);
		Token token4 = new Token(true, 5,5,game);
		
		
		em.persist(boby);
		em.persist(bob);
		em.persist(jack);
		em.persist(game);
		em.persist(token1);
		em.persist(token2);
		em.persist(token3);
		em.persist(token4);
		
	}
}
