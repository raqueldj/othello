package io.avengers.othello.dao;

import java.util.List;
import java.util.Optional;

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
	
	public List<Game> findAll(){
		String query="SELECT g from Game g ORDER BY g.id";
		return em.createQuery(query,Game.class)
				.getResultList();
	}
	
	public Optional<Game> findById(Integer id){
		Game g=em.find(Game.class, id);
		return Optional.ofNullable(g);
	}
	
	public List<Game> findRunning(){
		String query2="SELECT g from Game g WHERE g.isRunning =true ORDER BY g.id";
		return em.createQuery(query2,Game.class)
				.getResultList();
	}
	
	public void delete(Game game) {
		em.remove(game);

	}
	
	public void deleteGameById(Integer id) {
		Game g = em.find(Game.class, id);
		em.remove(g);

	}
	
	public void updateGameRunning(Integer gameId){
		Game g=em.find(Game.class, gameId);
		g.setRunning(true);
	}

	public void updateGameNotRunning(Integer gameId){
		Game g=em.find(Game.class, gameId);
		g.setRunning(false);
	}
	
	public void missedTurn(Integer gameId){
		Game g=em.find(Game.class, gameId);
		g.setMissedTurn(g.getMissedTurn()+1);
	}
}
