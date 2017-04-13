package io.avengers.othello.service;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import io.avengers.othello.domain.Game;
import io.avengers.othello.domain.User;
import io.avengers.othello.jpa.EmFactory;

public class GameServiceTest {
	
	EntityManager em;
	GameService gameService;
	User bob = new User("Bob");
	User jack = new User("Jack");
	Game game = new Game(bob, jack);
	
	@Before
	public void setUp() {
		em = EmFactory.createEntityManager();
		
	}
	@After
	public void tearDown() {
		if (em.isOpen()) {
			em.close();
		}
	}
	@AfterClass
	public static void close() {
		EmFactory.getInstance().close();
	}
	
	@Test
	public void playable(){
		
	}
}
