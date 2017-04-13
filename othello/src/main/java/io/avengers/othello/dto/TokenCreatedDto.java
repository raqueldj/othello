package io.avengers.othello.dto;

public class TokenCreatedDto {
	
	int tokenId;
	boolean hasBeenCreated;
	int gameId;
	
	public int getTokenId() {
		return tokenId;
	}
	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}
	public boolean isHasBeenCreated() {
		return hasBeenCreated;
	}
	public void setHasBeenCreated(boolean hasBeenCreated) {
		this.hasBeenCreated = hasBeenCreated;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	

}
