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
  @Output() loadOldGame: EventEmitter<number> = new EventEmitter();



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
