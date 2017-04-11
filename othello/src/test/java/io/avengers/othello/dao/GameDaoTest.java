package io.avengers.othello.dao;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import io.avengers.othello.domain.Game;
import io.avengers.othello.domain.User;
import io.avengers.othello.jpa.EmFactory;

public class GameDaoTest {

	User bob = new User("Bob");
	User jack = new User("Jack");
	Game game = new Game(bob,jack);
	Game gameBis = new Game(jack,bob);
	Game gameTer = new Game (bob,jack);
	

	EntityManager em;
	GameDao gdao;
	UserDao udao;
	
	@Before
	public void setUp(){
		em = EmFactory.createEntityManager();
		
		
		gdao = new GameDao(em);
		udao = new UserDao(em);
		udao.create(bob);
		udao.create(jack);
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
	public void create(){
		em.getTransaction().begin();
		gdao.create(game);
		gdao.create(gameBis);
		gdao.create(gameTer);
		
		assertTrue(game.getId() > 0);
		assertTrue(gameBis.getId() > 0);
		assertTrue(gameTer.getId() > 0);
		
		em.getTransaction().commit();
	}
	
	
}
