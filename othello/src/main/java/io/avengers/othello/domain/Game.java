package io.avengers.othello.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

@Entity
public class Game {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id = null;
	
	@OneToOne
	private User whiteUser;
	
	@OneToOne
	private User blackUser;
	
	private boolean isRunning=true;
	
	public Game(){
		
	}
	
	public Game(User whiteUser,User blackUser){
		this.whiteUser = whiteUser;
		this.blackUser=blackUser;
	}

	public Game(Integer id, User whiteUser, User blackUser, boolean isRunning) {
		this.id = id;
		this.whiteUser = whiteUser;
		this.blackUser=blackUser;
		this.isRunning = isRunning;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public User getWhiteUser() {
		return whiteUser;
	}

	public void setWhiteUser(User whiteUser) {
		this.whiteUser = whiteUser;
	}

	public User getBlackUser() {
		return blackUser;
	}

	public void setBlackUser(User blackUser) {
		this.blackUser = blackUser;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blackUser == null) ? 0 : blackUser.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isRunning ? 1231 : 1237);
		result = prime * result + ((whiteUser == null) ? 0 : whiteUser.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (blackUser == null) {
			if (other.blackUser != null)
				return false;
		} else if (!blackUser.equals(other.blackUser))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isRunning != other.isRunning)
			return false;
		if (whiteUser == null) {
			if (other.whiteUser != null)
				return false;
		} else if (!whiteUser.equals(other.whiteUser))
			return false;
		return true;
	}

	
	
	
}
