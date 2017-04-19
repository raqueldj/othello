import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CreateToken, Coordonnees,GameState } from '../modele';
import { DataService } from '../data.service';


@Component({
  selector: 'app-set',
  templateUrl: './set.component.html',
  styleUrls: ['./set.component.css'],
  providers: [DataService]
})
export class SetComponent {

  @Output() selectBox: EventEmitter<CreateToken> = new EventEmitter();


  @Input() gameState: GameState;

  addToken(i, j) {
    this.selectBox.emit({ x: i + 1, y: 8 - j, gameId: this.gameState.id });
  }

  

 


}
