package io.avengers.othello.service;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.avengers.othello.dao.UserDao;
import io.avengers.othello.domain.User;

@Stateless
@Named
public class UserService {
	
	UserDao dao;
	
    @PersistenceContext
    EntityManager em;

    @PostConstruct
    void after(){
    	System.out.println("Post construct in universeService");
    	this.dao = new UserDao(em);
    }
    
    public User createUser(User user){
    	return dao.create(user);
    }
    
    public void deleteUser(Integer id){
    	dao.delete(id);
    }
    
    public User updateUser(Integer id, User user){
    	User u = dao.update(id, user);
		return u;
    }
}
