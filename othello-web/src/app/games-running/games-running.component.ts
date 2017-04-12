import { Component, Input, Output, EventEmitter } from '@angular/core';

import { Game,User } from '../modele';
import { DataService } from '../data.service.1';

@Component({
  selector: 'app-games-running',
  templateUrl: './games-running.component.html',
  styleUrls: ['./games-running.component.css'],
  providers: [DataService]
})
export class GamesRunningComponent {
  @Input() games;

  @Output() joinGame: EventEmitter<null> = new EventEmitter();

  constructor(private dataService: DataService) {
    }

  runningGames: Game[] = this.dataService.runningGames;

  removeGame(gameIndex) {
    this.runningGames.splice(gameIndex, 1);
  }


  onJoinGame() {
    this.joinGame.emit();
  }
}
