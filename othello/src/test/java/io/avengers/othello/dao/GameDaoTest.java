package io.avengers.othello.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import io.avengers.othello.domain.User;

public class GameDaoTest {

	User bob = new User("Bob");
	User jack = new User("Jack");
	
	List<User> users = new ArrayList<>();
	//users=[bob,jack];

	
	
	EntityManager em;
	
	
}
