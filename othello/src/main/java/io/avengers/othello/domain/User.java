package io.avengers.othello.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id = null;
	
	private String name;
	private String passWord;
	private int gameWin;
	private int gameLose;
	
	public User() {
	}
	
	public User(String name){
		this.name = name;
	}

	public User(String name, String passWord, int gameWin, int gameLose) {
		this.name = name;
		this.passWord = passWord;
		this.gameWin = gameWin;
		this.gameLose = gameLose;
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

}
