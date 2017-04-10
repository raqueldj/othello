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
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id = null;
	
	private String name;
	private String passWord;
	int gameWin;
	int gameLose;
	
	/*@ManyToMany
	@JoinTable(name = "USER_GAME", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "id") })
	private List<Game> game;*/
	
	public User() {
	}

	public User(Integer id, String name, String passWord, int gameWin, int gameLose/*, List<Game> game*/) {
		super();
		this.id = id;
		this.name = name;
		this.passWord = passWord;
		this.gameWin = gameWin;
		this.gameLose = gameLose;
		//this.game = game;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassWord() {
		return passWord;
	}


	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public int getGameWin() {
		return this.gameWin;
	}

	public void setGameWin(int gameWin) {
		this.gameWin = gameWin;
	}

	public int getGameLose() {
		return this.gameLose;
	}

	public void setGameLose(int gameLose) {
		this.gameLose = gameLose;
	}

	/*public List<Game> getGame() {
		return game;
	}

	public void setGame(List<Game> game) {
		this.game = game;
	}*/
}
