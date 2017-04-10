package io.avengers.othello.dao;

import javax.persistence.EntityManager;

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
	
	public void delete(Token token){
		em.remove(token);
	}
	
	public Token findById(int id){
		Token token = em.find(Token.class, id);
		
		return token;
	}
}
