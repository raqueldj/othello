package io.avengers.othello.dto;

public class UserDto {

	int id;
	String name;
	String passWord;
	
	public UserDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	public UserDto(int id, String name, String passWord) {
		this.id = id;
		this.name = name;
		this.passWord = passWord;
	}

	public UserDto(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	
	
}
