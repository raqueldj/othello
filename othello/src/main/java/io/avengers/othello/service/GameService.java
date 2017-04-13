package io.avengers.othello.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;

import io.avengers.othello.dao.GameDao;
import io.avengers.othello.dao.TokenDao;
import io.avengers.othello.dao.UserDao;
import io.avengers.othello.domain.Game;
import io.avengers.othello.domain.Token;
import io.avengers.othello.dto.CreateTokenDto;
import io.avengers.othello.dto.GameStateDto;

@Stateless
@Named
public class GameService {

	GameDao gdao;
	TokenDao tdao;
	UserDao udao;

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	void after() {
		System.out.println("=====================@PostConstruct UniverseService=====================");
		this.gdao = new GameDao(em);
		this.tdao = new TokenDao(em);
		this.udao = new UserDao(em);
	}

	public GameStateDto getState(int id) {
		GameStateDto gameStateDto = new GameStateDto();
		Game game = gdao.findById(id).orElseThrow(NotFoundException::new);
		List<Token> tokens = new ArrayList<>();
		tokens = tdao.findByGame(id);

		int ws = 0;
		int bs = 0;

		for (Token token : tokens) {

			if (token.getIsWhite()) {
				gameStateDto.getSet()[token.getX()][token.getY()] = 2;
				ws = ws + 1;
			} else {
				gameStateDto.getSet()[token.getX()][token.getY()] = 1;
				bs = bs + 1;
			}

		}

		gameStateDto.getPlayerBlack().setId(game.getBlackUser().getId());
		gameStateDto.getPlayerBlack().setName(game.getBlackUser().getName());
		gameStateDto.getPlayerBlack().setPassWord(game.getBlackUser().getPassWord());

		gameStateDto.getPlayerWhite().setId(game.getWhiteUser().getId());
		gameStateDto.getPlayerWhite().setName(game.getWhiteUser().getName());
		gameStateDto.getPlayerWhite().setPassWord(game.getWhiteUser().getPassWord());

		gameStateDto.setScorePB(bs);
		gameStateDto.setScorePW(ws);

		gameStateDto.setRunning(game.isRunning());

		gameStateDto.setWhitePlays(game.isWhitePlays());
		
		boolean playable = false;
		int playingColor = 1;
		int adverseColor = 2;

		if (gameStateDto.isWhitePlays()) {
			playingColor = 2;
			adverseColor = 1;
		}
		
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				if (i + 2 < 8) {
					if (gameStateDto.getSet()[i + 1][j] == adverseColor) {
						for (int k = i + 2; k <= 8; k++) {
							if (gameStateDto.getSet()[k][j] == playingColor) {
								playable = true;
							}
						}
					}
				}
				if (gameStateDto.getSet()[i + 1][j - 1] == adverseColor) {
					if (i + 2 < 8 && j > 2) {
						int max = Math.min(j - 1, 8 - i);
						for (int k = 2; k <= max; k++) {
							if (gameStateDto.getSet()[i + k][j - k] == playingColor) {
								playable = true;
							}
						}
					}
				}
				if (gameStateDto.getSet()[i][j - 1] == adverseColor) {
					if (j > 2) {
						for (int k = j - 2; k >= 0; k--) {
							if (gameStateDto.getSet()[i][k] == playingColor) {
								playable = true;
							}
						}
					}
				}
				if (gameStateDto.getSet()[i - 1][j - 1] == adverseColor) {
					if (i > 2 && j > 2) {
						int max = Math.min(j - 1, i - 1);
						for (int k = 2; k <= max; k++) {
							if (gameStateDto.getSet()[i - k][j - k] == playingColor) {
								playable = true;
							}
						}
					}
				}
				if (gameStateDto.getSet()[i - 1][j] == adverseColor) {
					if (i > 2) {
						for (int k = i - 2; k >= 0; k--) {
							if (gameStateDto.getSet()[k][j] == playingColor) {
								playable = true;
							}
						}
					}
				}
				if (gameStateDto.getSet()[i - 1][j + 1] == adverseColor) {
					if (i > 2 && j + 2 < 8) {
						int max = Math.min(8 - j, i - 1);
						for (int k = 2; k <= max; k++) {
							if (gameStateDto.getSet()[i - k][j + k] == playingColor) {
								playable = true;
							}
						}
					}
				}
				if (gameStateDto.getSet()[i][j + 1] == adverseColor) {
					if (j + 2 < 8) {
						for (int k = j + 2; k <= 8; k++) {
							if (gameStateDto.getSet()[i][k] == playingColor) {
								playable = true;
							}
						}
					}
				}
				if (gameStateDto.getSet()[i + 1][j + 1] == adverseColor) {
					if (i + 2 < 8 && j + 2 < 8) {
						int max = Math.min(8 - j, 8 - i);
						for (int k = 2; k <= max; k++) {
							if (gameStateDto.getSet()[i + k][j + k] == playingColor) {
								playable = true;
							}
						}

					}
				}
			}

		}

		if (!playable){
			gameStateDto.setWhitePlays(!gameStateDto.isWhitePlays());
		}
		return gameStateDto;
	}

	

	public Token createToken(CreateTokenDto createTokenDto) {

		return null;
	}

	public void play(int id) {

	}

	/*
	 * 
	 * public Game create(Game game){
	 * 
	 * 
	 * return dao.create(game);
	 * 
	 * }
	 * 
	 * public List<Game> findAll(){ return dao.findAll(); }
	 * 
	 * public Optional<Game> findById(int id){ return dao.findById(id); }
	 * 
	 * public List<Game> findRunning(){ return dao.findRunning(); }
	 * 
	 * public void deleteGameById(Integer id){ dao.deleteGameById(id); }
	 * 
	 * public void updateGameRunning(Integer id){ dao.updateGameRunning(id); }
	 * 
	 * public void updateGameNotRunning(Integer id){
	 * dao.updateGameNotRunning(id);
	 * 
	 * 
	 * }
	 * 
	 * 
	 */
}
