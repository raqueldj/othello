import { Component, Input, Output, EventEmitter } from '@angular/core';
import { User, GameState } from '../modele';

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.css']
})
export class DataComponent {
  @Input() gameState : GameState;

  @Output() replay: EventEmitter<null> = new EventEmitter();
  onReplay() {
    this.replay.emit();
  }
}
