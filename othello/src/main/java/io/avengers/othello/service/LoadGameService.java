package io.avengers.othello.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.avengers.othello.dao.GameDao;
import io.avengers.othello.domain.Game;
import io.avengers.othello.dto.GameDto;
import io.avengers.othello.dto.UserDto;

@Stateless
@Named
public class LoadGameService {

	GameDao gameDao;

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	void after() {
		System.out.println("----------PostConstruct in FunkoPopService----------");
		this.gameDao = new GameDao(em);
	}

	public List<GameDto> findAllGamesRunning() {
		List<Game> games = gameDao.findRunning();
		List<GameDto> gamesDto = new ArrayList<>();
		

		for (Game game : games) {
			
			UserDto playerBlack = new UserDto(game.getId(), game.getBlackUser().getName());
			UserDto playerWhite = new UserDto(game.getId(), game.getWhiteUser().getName());
			
			gamesDto.add( new GameDto(game.getId(), playerBlack, playerWhite));
		}
		return gamesDto;
	}
	
}
