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
	
	public User update(Integer id, User uUp){
		User u = em.find(User.class, id);
		
		if(uUp.getGameLose() != 0)
			u.setGameLose(uUp.getGameLose());
		if(uUp.getGameWin() != 0)
			u.setGameWin(uUp.getGameWin());
		if(!uUp.getName().isEmpty())
			u.setName(uUp.getName());
		if(uUp.getPassWord() != null)
			u.setPassWord(uUp.getPassWord());
		
		return u;
	}
	
}
