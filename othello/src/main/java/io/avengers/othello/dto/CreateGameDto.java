package io.avengers.othello.dto;

public class CreateGameDto {

	int id1;
	int id2;
	String passWord1;
	String passWord2;
	
	
	public int getId1() {
		return id1;
	}
	public void setId1(int id1) {
		this.id1 = id1;
	}
	public int getId2() {
		return id2;
	}
	public void setId2(int id2) {
		this.id2 = id2;
	}
	public String getPassWord1() {
		return passWord1;
	}
	public void setPassWord1(String passWord1) {
		this.passWord1 = passWord1;
	}
	public String getPassWord2() {
		return passWord2;
	}
	public void setPassWord2(String passWord2) {
		this.passWord2 = passWord2;
	}
	
	
}
