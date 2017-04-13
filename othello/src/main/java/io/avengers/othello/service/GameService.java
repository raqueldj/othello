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
import io.avengers.othello.dto.UserDto;
import io.avengers.othello.dto.TokenCreatedDto;

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
		
		Game game = gdao.findById(id).orElseThrow(NotFoundException::new);
		List<Token> tokens = new ArrayList<>();
		tokens = tdao.findByGame(id);
		
		UserDto playerBlack = new UserDto(game.getId(), game.getBlackUser().getName());
		UserDto playerWhite = new UserDto(game.getId(), game.getWhiteUser().getName());

		int ws = 0;
		int bs = 0;
		int[][] set = new int[8][8];
		
		for (Token token : tokens) {

			if (token.getIsWhite()) {
				set[token.getX()][token.getY()] = 2;
				ws = ws + 1;
			} else {
				set[token.getX()][token.getY()] = 1;
				bs = bs + 1;
			}

		}

		GameStateDto gameStateDto= new GameStateDto(set, playerWhite, playerBlack, ws, bs, game.isRunning(), game.isWhitePlays());
		
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

		if (!playable) {
			gameStateDto.setWhitePlays(!gameStateDto.isWhitePlays());
		}
		return gameStateDto;
	}

	public TokenCreatedDto createToken(CreateTokenDto createTokenDto) {
		GameStateDto gameStateDto = getState(createTokenDto.getGameId());
		Token newToken = new Token();
		TokenCreatedDto createdToken = new TokenCreatedDto();
		Game game = gdao.findById(createTokenDto.getGameId()).orElseThrow(NotFoundException::new);
		newToken.setGame(game);
		newToken.setIsWhite(createTokenDto.isWhite());
		newToken.setX(createTokenDto.getX());
		newToken.setY(createTokenDto.getY());

		tdao.create(newToken);

		return null;

	}

	public void play(TokenCreatedDto tokenCreated) {

	}

	public boolean isPlayable(int x, int y, GameStateDto gameStateDto) {

		boolean playable;

		int playingColor = 1;
		int adverseColor = 2;

		if (gameStateDto.isWhitePlays()) {
			playingColor = 2;
			adverseColor = 1;

			if (x + 2 < 8) {
				if (gameStateDto.getSet()[x + 1][y] == adverseColor) {
					for (int k = x + 2; k <= 8; k++) {
						if (gameStateDto.getSet()[k][y] == playingColor) {
							playable = true;
						}
					}
				}
			}
			if (x + 2 < 8 && y > 2) {
				if (gameStateDto.getSet()[x + 1][y - 1] == adverseColor) {

					int max = Math.min(y - 1, 8 - x);
					for (int k = 2; k <= max; k++) {
						if (gameStateDto.getSet()[x + k][y - k] == playingColor) {
							playable = true;
						}
					}
				}
			}
			if (y > 2) {
				if (gameStateDto.getSet()[x][y - 1] == adverseColor) {

					for (int k = y - 2; k >= 0; k--) {
						if (gameStateDto.getSet()[x][k] == playingColor) {
							playable = true;
						}
					}
				}
			}
			if (x > 2 && y > 2) {
				if (gameStateDto.getSet()[x - 1][y - 1] == adverseColor) {

					int max = Math.min(y - 1, x - 1);
					for (int k = 2; k <= max; k++) {
						if (gameStateDto.getSet()[x - k][y - k] == playingColor) {
							playable = true;
						}
					}
				}
			}
			if (x > 2) {
				if (gameStateDto.getSet()[x - 1][y] == adverseColor) {

					for (int k = x - 2; k >= 0; k--) {
						if (gameStateDto.getSet()[k][y] == playingColor) {
							playable = true;
						}
					}
				}
			}
			if (x > 2 && y + 2 < 8) {
				if (gameStateDto.getSet()[x - 1][y + 1] == adverseColor) {

					int max = Math.min(8 - y, x - 1);
					for (int k = 2; k <= max; k++) {
						if (gameStateDto.getSet()[x - k][y + k] == playingColor) {
							playable = true;
						}
					}
				}
			}
			if (y + 2 < 8) {
				if (gameStateDto.getSet()[x][y + 1] == adverseColor) {

					for (int k = y + 2; k <= 8; k++) {
						if (gameStateDto.getSet()[x][k] == playingColor) {
							playable = true;
						}
					}
				}
			}
			if (x + 2 < 8 && y + 2 < 8) {
				if (gameStateDto.getSet()[x + 1][y + 1] == adverseColor) {

					int max = Math.min(8 - y, 8 - x);
					for (int k = 2; k <= max; k++) {
						if (gameStateDto.getSet()[x + k][y + k] == playingColor) {
							playable = true;
						}
					}

				}
			}
			return playable;

		}
	}
}
