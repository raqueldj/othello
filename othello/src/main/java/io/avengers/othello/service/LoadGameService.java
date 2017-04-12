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
		GameDto gameDto = new GameDto();

		for (Game game : games) {

			gameDto.setId(game.getId());
			gameDto.getPlayerBlack().setId(game.getBlackUser().getId());
			gameDto.getPlayerBlack().setName(game.getBlackUser().getName());
			gameDto.getPlayerWhite().setId(game.getWhiteUser().getId());
			gameDto.getPlayerWhite().setName(game.getWhiteUser().getName());

			gamesDto.add(gameDto);
		}
		return gamesDto;
	}
}
