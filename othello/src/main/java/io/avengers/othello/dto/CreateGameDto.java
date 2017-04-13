package io.avengers.othello.dto;

public class CreateGameDto {

	int idBlack;
	int idWhite;
	String passWordBlack;
	String passWordWhite;
	
	public CreateGameDto() {
	}
	
	public CreateGameDto(int idBlack, int idWhite, String passWordBlack, String passWordWhite) {
		this.idBlack = idBlack;
		this.idWhite = idWhite;
		this.passWordBlack = passWordBlack;
		this.passWordWhite = passWordWhite;
	}

	public int getIdBlack() {
		return idBlack;
	}
	public void setIdBlack(int idBlack) {
		this.idBlack = idBlack;
	}
	public int getIdWhite() {
		return idWhite;
	}
	public void setIdWhite(int idWhite) {
		this.idWhite = idWhite;
	}
	public String getPassWordBlack() {
		return passWordBlack;
	}
	public void setPassWordBlack(String passwordBlack) {
		this.passWordBlack = passwordBlack;
	}
	public String getPassWordWhite() {
		return passWordWhite;
	}
	public void setPassWordWhite(String passWordWhite) {
		this.passWordWhite = passWordWhite;
	}
	
	

	
}
