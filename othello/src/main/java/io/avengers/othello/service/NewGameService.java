package io.avengers.othello.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.avengers.othello.dao.UserDao;
import io.avengers.othello.domain.User;
import io.avengers.othello.dto.UserDto;

@Stateless
@Named
public class NewGameService {

	UserDao userDao;
	
    @PersistenceContext
    EntityManager em;
    
    public List<UserDto> findAllUser(){
    	
    	List<User> users = userDao.findAll();
    	List<UserDto> usersDto = new ArrayList<>();
    	UserDto userDto = new UserDto();
    	
    	for(User user: users){
    		userDto.setId(user.getId());
    		userDto.setName(user.getName());
    		userDto.setPassWord(user.getPassWord());
    		
    		usersDto.add(userDto);
    	}
    	return usersDto;
    }
}
