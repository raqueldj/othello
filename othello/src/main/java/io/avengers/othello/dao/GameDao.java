package io.avengers.othello.dao;

import javax.persistence.EntityManager;

import io.avengers.othello.domain.Game;

public class GameDao {
	
	EntityManager em;
	
	public GameDao(EntityManager em) {
		this.em = em;
	}

	
	public Game create(Game game) {

		em.persist(game);
		return game;

	}
	
	
	public void delete(Game game) {
		em.remove(game);

	}
	
	public void deleteGameById(Integer id) {
		Game g = em.find(Game.class, id);
		em.remove(g);

	}

}
