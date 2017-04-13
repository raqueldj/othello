import { Component, Input, Output, EventEmitter } from '@angular/core';

import { Game, User } from '../modele';
import { DataService } from '../data.service';

@Component({
  selector: 'app-games-running',
  templateUrl: './games-running.component.html',
  styleUrls: ['./games-running.component.css'],
  providers: [DataService]
})
export class GamesRunningComponent {
  @Output() gamesRuningOutput: EventEmitter<Game> = new EventEmitter();
  @Output() loadOldGame: EventEmitter<null> = new EventEmitter();

  constructor(private dataService: DataService) {
    this.dataService.getGamesRunning().then(games => this.games = games);
  }

  /*getGames(id, whiteUser, blackUser, running) {
    this.gamesRuningOutput.emit({
      id,
      whiteUser,
      blackUser,
      running
    });
    this.dataService.getRunningGames().then(games => this.games = games);
  }*/

  games: Game[]

  onloadGame() {
    this.loadOldGame.emit();
  }
}
