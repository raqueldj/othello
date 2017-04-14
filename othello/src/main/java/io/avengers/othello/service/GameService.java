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

		UserDto playerBlack = new UserDto(game.getBlackUser().getId(), game.getBlackUser().getName(),game.getBlackUser().getPassWord());
		UserDto playerWhite = new UserDto(game.getWhiteUser().getId(), game.getWhiteUser().getName(),game.getWhiteUser().getPassWord());

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

		GameStateDto gameStateDto = new GameStateDto(set, playerWhite, playerBlack, ws, bs, game.isRunning(),
				game.isWhitePlays());

		boolean playable = false;

		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				if(isPlayable(i, j, gameStateDto)){
					playable=true;
				}
				
			}

		}
System.out.println("==================================== playable :"+playable);
		if (!playable) {
			gameStateDto.setWhitePlays(!gameStateDto.isWhitePlays());
			gdao.missedTurn(id);
			gdao.nextPlayer(id);
			game.setMissedTurn(game.getMissedTurn()+1);
		} else{
			gdao.playedTurn(id);
			game.setMissedTurn(0);
		}
		
		if(game.getMissedTurn()==2 || ws+bs==64){
			gdao.updateGameNotRunning(id);
			gameStateDto.setRunning(false);
		}
		
		return gameStateDto;
	}

	// ---2---
	// Verifie si le coup est jouable et le créé si possible (renvoi l'Id du
	// jeton et si il a été créé)
	public TokenCreatedDto createToken(CreateTokenDto createTokenDto) {
		GameStateDto gameStateDto = getState(createTokenDto.getGameId());

		
		Game game = gdao.findById(createTokenDto.getGameId()).orElseThrow(NotFoundException::new);
System.out.println("======================================Before test if is playable for token:"+createTokenDto.getX()+createTokenDto.getY());
		if (isPlayable(createTokenDto.getX(), createTokenDto.getY(), gameStateDto)) {

			Token newToken = new Token(gameStateDto.isWhitePlays(),createTokenDto.getX(),createTokenDto.getY(),game);
	System.out.println("========================= X:"+newToken.getX());
	System.out.println("========================= Y:"+newToken.getY());
			tdao.create(newToken);
			
			
			
			TokenCreatedDto createdToken = new TokenCreatedDto(newToken.getId(),true,createTokenDto.getGameId());
			System.out.println("========================= Id:"+createdToken.getTokenId());
			
			
			
			List<Token> tokensToSwitch = tokensToSwitch(createdToken, gameStateDto);
			System.out.println("============================ first token to switch X: "+tokensToSwitch.get(0).getX()+" Y: "+tokensToSwitch.get(0).getY());
			
			for (Token token:tokensToSwitch){
				System.out.println("===========================token to switch X: "+token.getX()+" Y: "+token.getY());
				tdao.updateTokenSwitch(token.getId());
			}
			
			
			
			gdao.nextPlayer(createTokenDto.getGameId());
			return createdToken;

		} else {
			TokenCreatedDto createdToken = new TokenCreatedDto(0,false,createTokenDto.getGameId());
			return createdToken;
		}

		

	}

	//---3---
	//Retourne les jetons et passe au tour suivant
	public void play(TokenCreatedDto tokenCreated) {
		GameStateDto gameStateDto = getState(tokenCreated.getGameId());
		Game game = gdao.findById(tokenCreated.getGameId()).orElseThrow(NotFoundException::new);

		List<Token> tokensToSwitch = new ArrayList<>();

		tokensToSwitch = tokensToSwitch(tokenCreated, gameStateDto);

		for (Token token : tokensToSwitch) {
			tdao.updateTokenSwitch(token.getId());
		}

		game.setWhitePlays(!game.isWhitePlays());

	}

	public boolean isPlayable(int x, int y, GameStateDto gameStateDto) {

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

	public List<Token> tokensToSwitch(TokenCreatedDto tokenCreated, GameStateDto gameStateDto) {
System.out.println("===================================toto1");
		Token newToken = tdao.findById(tokenCreated.getTokenId());
		int x = newToken.getX();
		int y = newToken.getY();
		System.out.println("===================================toto1");
		List<Token> tokensToSwitch = new ArrayList<>();

		System.out.println("===================================toto1");
		int playingColor = 1;
		int adverseColor = 2;
		int gameId = tokenCreated.getGameId();
		System.out.println("===================================toto1");
		if (gameStateDto.isWhitePlays()) {
			playingColor = 2;
			adverseColor = 1;
		}
		if (x + 2 < 8) {
			if (gameStateDto.getSet()[x + 1][y] == adverseColor) {
				for (int k = x + 2; k <= 8; k++) {
					if (gameStateDto.getSet()[k][y] == playingColor) {
						for (int l = x + 1; l < k; l++) {
							System.out.println("=============================findByXY  X: "+l+" Y: "+y);
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
					if (gameStateDto.getSet()[x + k][y - k] == playingColor) {
						for (int l = 1; l < k ; l++) {
							System.out.println("=============================findByXY  X: "+(x+l)+" Y: "+(y-l));
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
					if (gameStateDto.getSet()[x][k] == playingColor) {
						for (int l = y - 1; l > k; l--) {
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
					if (gameStateDto.getSet()[x - k][y - k] == playingColor) {
						for (int l = 1; l < k ; l++) {
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
					if (gameStateDto.getSet()[k][y] == playingColor) {
						for (int l = x - 1; l > k; l--) {
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
					if (gameStateDto.getSet()[x - k][y + k] == playingColor) {
						for (int l = 1; l < k ; l++) {
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
					if (gameStateDto.getSet()[x][k] == playingColor) {
						for (int l = y + 1; l < k; l++) {
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
				System.out.println("=========================== max :"+max);
				for (int k = 2; k <= max; k++) {
					System.out.println("=========================== k :"+k);
					if (gameStateDto.getSet()[x + k][y + k] == playingColor) {
						for (int l = 1; l < k ; l++) {
							System.out.println("=========================== l :"+l);
							System.out.println("================================ find by XY x : "+x+ " l: "+l +" y+: "+y+" k: "+k);
							tokensToSwitch.add(tdao.findByXY(gameId, x + l, y + l));
						}
						break;
					}
				}

			}
		}
		System.out.println("===================================toto1");
		return tokensToSwitch;

	}
}
