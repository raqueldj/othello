package io.avengers.othello.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.avengers.othello.domain.Game;
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
		
		em.persist(boby);
		em.persist(bob);
		em.persist(jack);
		em.persist(game);
		
	}
}
