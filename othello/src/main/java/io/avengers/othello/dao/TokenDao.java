package io.avengers.othello.dao;

import java.util.List;

import javax.persistence.EntityManager;

import io.avengers.othello.domain.Game;
import io.avengers.othello.domain.Token;

public class TokenDao {

	EntityManager em;

	public TokenDao(EntityManager em) {
		super();
		this.em = em;
	}
	
	public Token create(Token token){
		em.persist(token);
		return token;
	}
	
	public Token update(Token token){
		return token;
	}
	
	public void updateTokenSwitch(Integer id){
		Token t=em.find(Token.class, id);
		t.setIsWhite(!t.getIsWhite());
	}
	
	public void delete(Token token){
		em.remove(token);
	}
	
	public Token findById(int id){
		Token token = em.find(Token.class, id);
		
		return token;
	}
	
	public List<Token> findByGame(int id){
		Game game=em.find(Game.class, id);
		String query="SELECT t from Token t WHERE t.game=:game";
		return em.createQuery(query,Token.class)
				.setParameter("game", game)
				.getResultList();
				
	}
	
	public Token findByXY(int id, int x, int y){
		Game game=em.find(Game.class, id);
		String query = "SELECT t from Token t WHERE t.x = :x AND t.y = :y AND t.game = :game";
		
		return em.createQuery(query, Token.class)
				.setParameter("x", x)
				.setParameter("y", y)
				.setParameter("game", game)
				.getSingleResult();
	}
}
