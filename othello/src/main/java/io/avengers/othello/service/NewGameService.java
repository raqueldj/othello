package io.avengers.othello.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;

import io.avengers.othello.dao.GameDao;
import io.avengers.othello.dao.UserDao;
import io.avengers.othello.domain.Game;
import io.avengers.othello.domain.User;
import io.avengers.othello.dto.CreateGameDto;
import io.avengers.othello.dto.CreateUserDto;
import io.avengers.othello.dto.UserDto;

@Stateless
@Named
public class NewGameService {

	UserDao userDao;
	GameDao gameDao;
	
    @PersistenceContext
    EntityManager em;
    
    @PostConstruct
    void after(){
    	System.out.println("Post construct in universeService");
    	this.userDao = new UserDao(em);
    }
    
    public List<UserDto> findAllUser(){
    	
    	List<User> users = new ArrayList<>();
    	users = userDao.findAll();
    	System.out.println("====================================================" + users.iterator().next().getName());
    	List<UserDto> usersDto = new ArrayList<>();
    	UserDto userDto = new UserDto();
    	
    	for(User user: users){
    		userDto.setId(user.getId());
    		userDto.setName(user.getName());
    		userDto.setPassWord(user.getPassWord());
    		
    		usersDto.add(userDto);
    	}
    	System.out.println("=============================================" + userDto.getName());
    	return usersDto;
    }
    
    public void createUser(CreateUserDto userDto){
    	
    	User user = new User();
    	user.setName(userDto.getName());
    	user.setPassWord(userDto.getPassWord());
    	System.out.println("=======================================================" + userDto.getName());
    	userDao.create(user);
    }
    
    public void createGame(CreateGameDto gameDto){
    	
    	User userBlack = userDao.findById(gameDto.getIdBlack()).orElseThrow(NotFoundException::new);
    	User userWhite = userDao.findById(gameDto.getIdWhite()).orElseThrow(NotFoundException::new);
    	
    	if(gameDto.getPasswordBlack().equals(userBlack.getPassWord()) && gameDto.getPassWordWhite().equals(userWhite.getPassWord())){
        	Game game = new Game(userBlack, userWhite);
        	gameDao.create(game);
    	}else{
    		
    	}
    	
    }
}
