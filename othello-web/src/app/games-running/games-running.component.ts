import { Component, Input, Output, EventEmitter } from '@angular/core';

import { Game } from './games'

@Component({
  selector: 'app-games-running',
  templateUrl: './games-running.component.html',
  styleUrls: ['./games-running.component.css']
})
export class GamesRunningComponent {
  @Input() games;

  @Output() createGame: EventEmitter<null> = new EventEmitter();

  gamesList: Game[] = [];

  Id = 1;
  addGame(player1, player2) {
    this.gamesList.push({
      id: this.Id++,
      player1,
      player2
    })
  }

  removeGame(gameIndex) {
    this.gamesList.splice(gameIndex, 1);
  }


  onNewGame() {
    this.createGame.emit();
  }
}
