import { Component, Input, Output, EventEmitter } from '@angular/core';
import { User, GameState, Replay } from '../modele';

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.css']
})
export class DataComponent {
  @Input() gameState : GameState;

  @Output() restart: EventEmitter<Replay> = new EventEmitter();
  Replay() {
    this.restart.emit({id1:this.gameState.playerWhite.id,id2:this.gameState.playerBlack.id});
  }
}
