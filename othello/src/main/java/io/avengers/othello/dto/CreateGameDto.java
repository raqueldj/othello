package io.avengers.othello.dto;

public class CreateGameDto {

	int idBlack;
	int idWhite;
	String passwordBlack;
	String passWordWhite;
	
	
	
	
	public CreateGameDto() {
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
	public String getPasswordBlack() {
		return passwordBlack;
	}
	public void setPasswordBlack(String passwordBlack) {
		this.passwordBlack = passwordBlack;
	}
	public String getPassWordWhite() {
		return passWordWhite;
	}
	public void setPassWordWhite(String passWordWhite) {
		this.passWordWhite = passWordWhite;
	}
	
	

	
}
