import { Component, Output, EventEmitter } from '@angular/core';
import { DataService } from './data.service';
import { Coordonnees, CreateToken, GameState, CreateUser, User, CreateGame, Replay } from './modele';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  state = "Menu"
  title = 'app works!';
  private dataService: DataService;
  gameId: number;

  constructor(dataservice: DataService) {
    this.dataService = dataservice;
    this.dataService.getUsers().then(users => {
      this.users = users;
    });
  }

  tokens: Coordonnees[] = [{ x: 1, y: 2 }];
  token: CreateToken;
  gameState: GameState;
  user: CreateUser;
  users: User[];
  newGame: CreateGame;

  addToken(createToken: CreateToken) {
    console.log(`yipiyeah ${createToken.x} ${createToken.y}`);
    // this.tokens.push({ x: coordonnee.x, y: coordonnee.y });
    this.dataService.createToken({ x: createToken.x, y: createToken.y, gameId: createToken.gameId })
      .then(response => (this.gameState = response));
  }

  loadGame(gameId) {
    this.gameId = gameId
    console.log(`CHARGEMENT DE LA PARTIE ${gameId}`);
    this.dataService.loadGame(gameId)
      .then(response => (this.gameState = response));
  }

  createUser(user) {
    this.user = user;
    console.log(`creation de : ${user}`);
    this.dataService.createUserDS(user)
      .then(Response => {
        this.users.push({ id: Response, name: user.name, passWord: user.passWord });
      });
  }


  createGame(newGame){
    this.dataService.createGameDS(newGame)
     .then(response => (this.gameState = response));
  }

  replay(Replay){
    this.dataService.replay(Replay)
    .then(response => (this.gameState = response));
  }
}
