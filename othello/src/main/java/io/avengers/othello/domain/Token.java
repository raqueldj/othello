package io.avengers.othello.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Token {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id = null;
	
	private Boolean isWhite;
	private int x;
	private int y;
	private Game game;
	
	public Token(){
		
	}
	
	public Token(Boolean isWhite, int x, int y, Game game) {
		super();
		this.isWhite = isWhite;
		this.x = x;
		this.y = y;
		this.game = game;
	}

	public Boolean getIsWhite() {
		return isWhite;
	}

	public void setIsWhite(Boolean isWhite) {
		this.isWhite = isWhite;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getId() {
		return id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	
	
}
