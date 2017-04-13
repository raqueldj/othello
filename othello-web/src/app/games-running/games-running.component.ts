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
    @Output() loadOldGame: EventEmitter<null> = new EventEmitter();

  onloadGame() {
    this.loadOldGame.emit();
  }
}
