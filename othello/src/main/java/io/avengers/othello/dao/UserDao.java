package io.avengers.othello.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import io.avengers.othello.domain.User;

public class UserDao {

	EntityManager em;
	
	public UserDao(EntityManager em){
		this.em = em;
	}
	
	public List<User> findAll(){
		String query="SELECT u from User u ORDER by u.id";
				return em.createQuery(query,User.class)
						.getResultList();
	}
	
	public Optional<User> findById(Integer id){
		User u = em.find(User.class, id);
		return Optional.ofNullable(u);
	}
	
	public int findIdByName(String name){
		User u = em.find(User.class, name);
		return u.getId();
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
	
	public void updateUserWin(Integer id){
		User u = em.find(User.class, id);
		int w=u.getGameWin();
		u.setGameWin(w+1);
	}
	
	public void updateUserLose(Integer id){
		User u = em.find(User.class, id);
		int l=u.getGameLose();
		u.setGameLose(l+1);
	}
	
}
