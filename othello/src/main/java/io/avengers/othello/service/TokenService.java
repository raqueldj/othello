package io.avengers.othello.service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.avengers.othello.dao.TokenDao;
import io.avengers.othello.domain.Token;

public class TokenService {

	TokenDao tokenDao;
	
	@PersistenceContext
	EntityManager em;
	
	@PostConstruct
	void after() {
		System.out.println("----------PostConstruct in FunkoPopService----------");
		this.tokenDao = new TokenDao(em);
	}
	
	public Token create(Token token){
		return tokenDao.create(token);
	}
	
	public Token update(Token token){
		Token t = tokenDao.findById(token.getId());
		t.setIsWhite(token.getIsWhite());
		t.setX(token.getX());
		t.setY(token.getY());
		
		return tokenDao.update(t);
	}
	
	public void delete(Token token){
		Token t = tokenDao.findById(token.getId());
				
		tokenDao.delete(t);
	}
	
	public Token findById(int id){
		return tokenDao.findById(id);
	}
	
}
