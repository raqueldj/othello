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

  
/*
  hasWhiteToken(i, j) {
    //this.tokens.some(token => token.x == i && token.y == j);
    for (i=1;i<=8;i++) {
      for(j=1;j<=8;j++){
      if (this.gameState.set[i+1][8-j] == 2)
        return 2;
      }
    }
    return false;
  }
*/
 


}
