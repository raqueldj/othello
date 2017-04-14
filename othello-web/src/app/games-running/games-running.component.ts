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
  @Output() loadOldGame: EventEmitter<number> = new EventEmitter();

  constructor(private dataService: DataService) {
    this.dataService.getGamesRunning().then(games => this.games = games);
  }

  games: Game[]

  selectedGameId = 0;

  onSelectGame(id) {
    this.selectedGameId = id;
  }

  onloadGame() {
    if (this.selectedGameId > 0)
      this.loadOldGame.emit(this.selectedGameId);
  }
}
