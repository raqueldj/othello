package io.avengers.othello.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Game {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id = null;
	
	@ManyToMany
	@JoinTable(name = "USER_GAME", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "id") })
	private List<User> users;
	
	private boolean isRunning=false;
	
}
