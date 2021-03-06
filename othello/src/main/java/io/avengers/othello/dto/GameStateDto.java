package io.avengers.othello.dto;

public class GameStateDto {
	int id;
	int set[][];
	UserDto playerWhite;
	UserDto playerBlack;
	int scorePW;
	int scorePB;
	boolean isRunning;
	boolean whitePlays;


	



	



	public GameStateDto() {
				
	}
	
	

	public GameStateDto(int id, int[][] set, UserDto playerWhite, UserDto playerBlack, int scorePW, int scorePB,
			boolean isRunning, boolean whitePlays) {
		super();
		this.id=id;
		this.set = set;
		this.playerWhite = playerWhite;
		this.playerBlack = playerBlack;
		this.scorePW = scorePW;
		this.scorePB = scorePB;
		this.isRunning = isRunning;
		this.whitePlays = whitePlays;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int[][] getSet() {
		return set;
	}

	public void setSet(int[][] set) {
		this.set = set;
	}

	public UserDto getPlayerWhite() {
		return playerWhite;
	}

	public void setPlayerWhite(UserDto playerWhite) {
		this.playerWhite = playerWhite;
	}

	public UserDto getPlayerBlack() {
		return playerBlack;
	}

	public void setPlayerBlack(UserDto playerBlack) {
		this.playerBlack = playerBlack;
	}

	public int getScorePW() {
		return scorePW;
	}

	public void setScorePW(int scorePW) {
		this.scorePW = scorePW;
	}

	public int getScorePB() {
		return scorePB;
	}

	public void setScorePB(int scorePB) {
		this.scorePB = scorePB;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public boolean isWhitePlays() {
		return whitePlays;
	}

	public void setWhitePlays(boolean whitePlays) {
		this.whitePlays = whitePlays;
	}
}
