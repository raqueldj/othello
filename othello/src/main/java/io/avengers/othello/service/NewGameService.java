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
import io.avengers.othello.dao.TokenDao;
import io.avengers.othello.dao.UserDao;
import io.avengers.othello.domain.Game;
import io.avengers.othello.domain.Token;
import io.avengers.othello.domain.User;
import io.avengers.othello.dto.CreateGameDto;
import io.avengers.othello.dto.CreateUserDto;
import io.avengers.othello.dto.UserDto;

@Stateless
@Named
public class NewGameService {

	UserDao userDao;
	GameDao gameDao;
	TokenDao tokenDao;
	
    @PersistenceContext
    EntityManager em;
    
    @PostConstruct
    void after(){
    	System.out.println("Post construct in universeService");
    	this.userDao = new UserDao(em);
    	this.gameDao = new GameDao(em);
    	this.tokenDao = new TokenDao(em);
    }
    
    public List<UserDto> findAllUser(){
    	
    	List<User> users = new ArrayList<>();
    	users = userDao.findAll();
    	List<UserDto> usersDto = new ArrayList<>();
    	
    	for(User user: users){
    		usersDto.add(new UserDto(user.getId(), user.getName(), user.getPassWord()));
    	}

    	return usersDto;
    }

    public int createUser(CreateUserDto userDto){
    	
    	User user = new User();
    	user.setName(userDto.getName());
    	user.setPassWord(userDto.getPassWord());
    	userDao.create(user);
    	System.out.println("==============================================" + user.getId());
    	return user.getId();
    }
    
    public void createGame(CreateGameDto gameDto){
    	
    	User userBlack = userDao.findById(gameDto.getIdBlack()).orElseThrow(NotFoundException::new);
    	User userWhite = userDao.findById(gameDto.getIdWhite()).orElseThrow(NotFoundException::new);
    	//if(gameDto.getPassWordBlack().equals(userBlack.getPassWord()) && gameDto.getPassWordWhite().equals(userWhite.getPassWord())){
        	Game game = new Game(userBlack, userWhite);
        	gameDao.create(game);
        	tokenDao.create(new Token(true, 4, 4, game));
        	tokenDao.create(new Token(true, 5, 5, game));
        	tokenDao.create(new Token(false, 4, 5, game));
        	tokenDao.create(new Token(false, 5, 4, game));
    	//}


    }
    
}
