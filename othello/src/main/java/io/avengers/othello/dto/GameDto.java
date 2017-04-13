package io.avengers.othello.dto;

public class GameDto {
	int id;
	UserDto playerWhite;
	UserDto playerBlack;

	public GameDto() {
		super();
	}

	public GameDto(int id, UserDto playerWhite, UserDto playerBlack) {
		this.id = id;
		this.playerWhite = playerWhite;
		this.playerBlack = playerBlack;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
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

}
