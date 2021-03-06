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
import io.avengers.othello.domain.User;
import io.avengers.othello.dto.CreateTokenDto;
import io.avengers.othello.dto.GameStateDto;
import io.avengers.othello.dto.ReplayDto;
import io.avengers.othello.dto.TokenCreatedDto;
import io.avengers.othello.dto.UserDto;

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

	// ---1---
	// Etat de la partie(positions des jetons, scores, couleur du prochain coup
	// à jouer)
	public GameStateDto getState(int id) {

		Game game = gdao.findById(id).orElseThrow(NotFoundException::new);
		List<Token> tokens = new ArrayList<>();
		tokens = tdao.findByGame(id);

		UserDto playerBlack = new UserDto(game.getBlackUser().getId(), game.getBlackUser().getName(),
				game.getBlackUser().getPassWord());
		UserDto playerWhite = new UserDto(game.getWhiteUser().getId(), game.getWhiteUser().getName(),
				game.getWhiteUser().getPassWord());

		int ws = 0;
		int bs = 0;
		int[][] set = new int[9][9];

		for (Token token : tokens) {

			if (token.getIsWhite()) {
				set[token.getX()][token.getY()] = 2;
				ws = ws + 1;
			} else {
				set[token.getX()][token.getY()] = 1;
				bs = bs + 1;
			}

		}

		GameStateDto gameStateDto = new GameStateDto(id, set, playerWhite, playerBlack, ws, bs, game.isRunning(),
				game.isWhitePlays());

		
		
		return gameStateDto;
	}

	// ---2---
	// Verifie si le coup est jouable et le créé si possible (renvoi l'Id du
	// jeton et si il a été créé)
	public GameStateDto createToken(CreateTokenDto createTokenDto) {
		System.out.println("=============================================jeton a creer"+createTokenDto.getX()+createTokenDto.getY());
		GameStateDto endOfTurnState = new GameStateDto();
		GameStateDto gameStateDto = getState(createTokenDto.getGameId());

		Game game = gdao.findById(createTokenDto.getGameId()).orElseThrow(NotFoundException::new);
System.out.println("=================================================="+createTokenDto.getX()+createTokenDto.getY()+"is Playable"+isPlayableXY(createTokenDto.getX(), createTokenDto.getY(), gameStateDto));
		if (isPlayableXY(createTokenDto.getX(), createTokenDto.getY(), gameStateDto)) {

			Token newToken = new Token(gameStateDto.isWhitePlays(), createTokenDto.getX(), createTokenDto.getY(), game);
			tdao.create(newToken);

			List<Token> tokensToSwitch = tokensToSwitch(newToken.getId(), createTokenDto.getGameId(), gameStateDto);

			for (Token token : tokensToSwitch) {
				tdao.updateTokenSwitch(token.getId());
			}

			List<Token> tokens = new ArrayList<>();
			tokens = tdao.findByGame(createTokenDto.getGameId());
			
			

			gdao.nextPlayer(createTokenDto.getGameId());
			
			
			endOfTurnState = getState(createTokenDto.getGameId());
			
			
			// Condition fin de partie
			
			game = gdao.findById(createTokenDto.getGameId()).orElseThrow(NotFoundException::new);
			if (gameStateDto.getScorePB() + gameStateDto.getScorePW() == 64) {
				gdao.updateGameNotRunning(createTokenDto.getGameId());
				endOfTurnState = getState(createTokenDto.getGameId());

			}

			if (!isPlayable(createTokenDto.getGameId(), game.isWhitePlays())
					&& !isPlayable(createTokenDto.getGameId(), !game.isWhitePlays())) {

				gdao.updateGameNotRunning(createTokenDto.getGameId());
				endOfTurnState = getState(createTokenDto.getGameId());
			}

			if (!isPlayable(createTokenDto.getGameId(), game.isWhitePlays())) {
				gdao.nextPlayer(createTokenDto.getGameId());
				endOfTurnState = getState(createTokenDto.getGameId());

			}

		} else {
			endOfTurnState = getState(createTokenDto.getGameId());
		}
		return endOfTurnState;

	}

	public boolean isPlayableXY(int x, int y, GameStateDto gameStateDto) {

		
		List<Token> checkToken = new ArrayList<>();
		checkToken = tdao.findByGame(gameStateDto.getId());
		for (Token token:checkToken ){
			if(token.getX()==x && token.getY()==y){
				System.out.println("======================================jeton deja existant");
				return false;
			}
		}
		
		
		
		boolean playable = false;

		int playingColor = 1;
		int adverseColor = 2;

		if (gameStateDto.isWhitePlays()) {
			playingColor = 2;
			adverseColor = 1;
		}
		if (x + 2 < 8) {
			if (gameStateDto.getSet()[x + 1][y] == adverseColor) {
				for (int k = x + 2; k <= 8; k++) {
					if (gameStateDto.getSet()[k][y] == playingColor) {
						return true;
						
					}
					if (gameStateDto.getSet()[k][y] == 0) {
						playable = false;
						break;
					}
				}
			}
		}
		if (x + 2 < 8 && y > 2) {
			if (gameStateDto.getSet()[x + 1][y - 1] == adverseColor) {

				int max = Math.min(y - 1, 8 - x);
				for (int k = 2; k <= max; k++) {
					if (gameStateDto.getSet()[x + k][y - k] == playingColor) {
						return true;
						
					}
					if (gameStateDto.getSet()[x + k][y - k] == 0) {
						playable = false;
						break;
					}
				}
			}
		}
		if (y > 2) {
			if (gameStateDto.getSet()[x][y - 1] == adverseColor) {

				for (int k = y - 2; k >= 0; k--) {
					if (gameStateDto.getSet()[x][k] == playingColor) {
						return true;
						
					}
					if (gameStateDto.getSet()[x][k] == 0) {
						playable = false;
						break;
					}
				}
			}
		}
		if (x > 2 && y > 2) {
			if (gameStateDto.getSet()[x - 1][y - 1] == adverseColor) {

				int max = Math.min(y - 1, x - 1);
				for (int k = 2; k <= max; k++) {
					if (gameStateDto.getSet()[x - k][y - k] == playingColor) {
						return true;
						
					}
					if (gameStateDto.getSet()[x - k][y - k] == 0) {
						playable = false;
						break;
					}
				}
			}
		}
		if (x > 2) {
			if (gameStateDto.getSet()[x - 1][y] == adverseColor) {

				for (int k = x - 2; k >= 0; k--) {
					if (gameStateDto.getSet()[k][y] == playingColor) {
						return true;
						
					}
					if (gameStateDto.getSet()[k][y] == 0) {
						playable = false;
						break;
					}
				}
			}
		}
		if (x > 2 && y + 2 < 8) {
			if (gameStateDto.getSet()[x - 1][y + 1] == adverseColor) {

				int max = Math.min(8 - y, x - 1);
				for (int k = 2; k <= max; k++) {
					if (gameStateDto.getSet()[x - k][y + k] == playingColor) {
						return true;
						
					}
					if (gameStateDto.getSet()[x - k][y + k] == 0) {
						playable = false;
						break;
					}
				}
			}
		}
		if (y + 2 < 8) {
			if (gameStateDto.getSet()[x][y + 1] == adverseColor) {

				for (int k = y + 2; k <= 8; k++) {
					if (gameStateDto.getSet()[x][k] == playingColor) {
						return true;
						
					}
					if (gameStateDto.getSet()[x][k] == 0) {
						playable = false;
						break;
					}
				}
			}
		}
		if (x + 2 < 8 && y + 2 < 8) {
			if (gameStateDto.getSet()[x + 1][y + 1] == adverseColor) {

				int max = Math.min(8 - y, 8 - x);
				for (int k = 2; k <= max; k++) {
					if (gameStateDto.getSet()[x + k][y + k] == playingColor) {
						return true;
						
					}
					if (gameStateDto.getSet()[x + k][y + k] == 0) {
						playable = false;
						break;
					}
				}

			}
		}
		return playable;

	}

	public boolean isPlayable(int id, boolean isWhite) {
		GameStateDto gameStateDto = getState(id);
		gameStateDto.setWhitePlays(isWhite);
		boolean playable = false;
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				if (isPlayableXY(i, j, gameStateDto)) {
					playable = true;
				}

			}

		}

		return playable;
	}

	public List<Token> tokensToSwitch(int tokenId, int gameId, GameStateDto gameStateDto) {
		Token newToken = tdao.findById(tokenId);
		int x = newToken.getX();
		int y = newToken.getY();
		List<Token> tokensToSwitch = new ArrayList<>();

		int playingColor = 1;
		int adverseColor = 2;

		if (gameStateDto.isWhitePlays()) {
			playingColor = 2;
			adverseColor = 1;
		}
		if (x + 2 < 8) {
			if (gameStateDto.getSet()[x + 1][y] == adverseColor) {
				for (int k = x + 2; k <= 8; k++) {
					if (gameStateDto.getSet()[k][y] == 0) {
						break;
						
					}
				
					if (gameStateDto.getSet()[k][y] == playingColor) {
						for (int l = x + 1; l < k; l++) {
							System.out.println("=======================token to switch : "+l+y);
							tokensToSwitch.add(tdao.findByXY(gameId, l, y));
						}
						break;
					}
				}
			}
		}
		if (x + 2 < 8 && y > 2) {
			if (gameStateDto.getSet()[x + 1][y - 1] == adverseColor) {

				int max = Math.min(y - 1, 8 - x);
				for (int k = 2; k <= max; k++) {
					if (gameStateDto.getSet()[x+k][y-k] == 0) {
						break;
						
					}
					if (gameStateDto.getSet()[x + k][y - k] == playingColor) {
						for (int l = 1; l < k; l++) {
							System.out.println("=======================token to switch : "+(x+l)+(y-l));

							tokensToSwitch.add(tdao.findByXY(gameId, x + l, y - l));
						}
						break;
					}
				}
			}
		}
		if (y > 2) {
			if (gameStateDto.getSet()[x][y - 1] == adverseColor) {

				for (int k = y - 2; k >= 0; k--) {
					if (gameStateDto.getSet()[x][k] == 0) {
						break;
						
					}
					if (gameStateDto.getSet()[x][k] == playingColor) {
						for (int l = y - 1; l > k; l--) {
							System.out.println("=======================token to switch : "+x+l);

							tokensToSwitch.add(tdao.findByXY(gameId, x, l));
						}
						break;
					}
				}
			}
		}
		if (x > 2 && y > 2) {
			if (gameStateDto.getSet()[x - 1][y - 1] == adverseColor) {

				int max = Math.min(y - 1, x - 1);
				for (int k = 2; k <= max; k++) {
					if (gameStateDto.getSet()[x-k][y-k] == 0) {
						break;
						
					}
					if (gameStateDto.getSet()[x - k][y - k] == playingColor) {
						for (int l = 1; l < k; l++) {
							System.out.println("=======================token to switch : "+x+" - "+k+"/"+y+" - "+k);

							tokensToSwitch.add(tdao.findByXY(gameId, x - l, y - l));
						}
						break;
					}
				}
			}
		}
		if (x > 2) {
			if (gameStateDto.getSet()[x - 1][y] == adverseColor) {

				for (int k = x - 2; k >= 0; k--) {
					if (gameStateDto.getSet()[k][y] == 0) {
						break;
						
					}
					if (gameStateDto.getSet()[k][y] == playingColor) {
						for (int l = x - 1; l > k; l--) {
							System.out.println("=======================token to switch : "+l+y);

							tokensToSwitch.add(tdao.findByXY(gameId, l, y));
						}
						break;
					}
				}
			}
		}
		if (x > 2 && y + 2 < 8) {
			if (gameStateDto.getSet()[x - 1][y + 1] == adverseColor) {

				int max = Math.min(8 - y, x - 1);
				for (int k = 2; k <= max; k++) {
					if (gameStateDto.getSet()[x-k][y+k] == 0) {
						break;
						
					}
					if (gameStateDto.getSet()[x - k][y + k] == playingColor) {
						for (int l = 1; l < k; l++) {
							System.out.println("=======================token to switch : "+(x-l)+(y+l));

							tokensToSwitch.add(tdao.findByXY(gameId, x - l, y + l));
						}
						break;
					}
				}
			}
		}
		if (y + 2 < 8) {
			if (gameStateDto.getSet()[x][y + 1] == adverseColor) {

				for (int k = y + 2; k <= 8; k++) {
					if (gameStateDto.getSet()[x][k] == 0) {
						break;
						
					}
					if (gameStateDto.getSet()[x][k] == playingColor) {
						for (int l = y + 1; l < k; l++) {
							System.out.println("=======================token to switch : "+x+l);

							tokensToSwitch.add(tdao.findByXY(gameId, x, l));
						}
						break;
					}
				}
			}
		}
		if (x + 2 < 8 && y + 2 < 8) {
			if (gameStateDto.getSet()[x + 1][y + 1] == adverseColor) {

				int max = Math.min(8 - y, 8 - x);
				for (int k = 2; k <= max; k++) {
					if (gameStateDto.getSet()[x+k][y+k] == 0) {
						break;
						
					}
					if (gameStateDto.getSet()[x + k][y + k] == playingColor) {
						for (int l = 1; l < k; l++) {
							System.out.println("=======================token to switch : "+(x+l)+(y+l));

							tokensToSwitch.add(tdao.findByXY(gameId, x + l, y + l));
						}
						break;
					}
				}

			}
		}
		return tokensToSwitch;

	}
	
	public GameStateDto replay(ReplayDto replay){
		User userBlack = udao.findById(replay.getId1()).orElseThrow(NotFoundException::new);
		User userWhite = udao.findById(replay.getId2()).orElseThrow(NotFoundException::new);
		Game game = new Game(userBlack, userWhite);
		gdao.create(game);
		tdao.create(new Token(true, 4, 4, game));
		tdao.create(new Token(true, 5, 5, game));
		tdao.create(new Token(false, 4, 5, game));
		tdao.create(new Token(false, 5, 4, game));
		
		return getState(game.getId());
	}
}
