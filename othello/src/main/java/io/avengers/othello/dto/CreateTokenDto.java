package io.avengers.othello.dto;

public class CreateTokenDto {
	
	int x;
	int y;
	//boolean isWhite;
	int gameId;
	
	
	
	public CreateTokenDto() {
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
	/*
	public boolean isWhite() {
		return isWhite;
	}
	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}
	*/
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	
}