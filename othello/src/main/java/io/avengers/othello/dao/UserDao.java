package io.avengers.othello.dao;

import javax.persistence.EntityManager;

import io.avengers.othello.domain.User;

public class UserDao {

	EntityManager em;
	
	public UserDao(EntityManager em){
		this.em = em;
	}
	
	public User create(User user){
		em.persist(user);
		return user;
	}
	
	public void delete(Integer id){
		User u = em.find(User.class, id);
		em.remove(u);
	}
	
}
