import { Component, EventEmitter, Output, Input } from '@angular/core';
import { SetComponent } from '../set/set.component';
import { TokenComponent } from '../token/token.component';
import { DataComponent } from '../data/data.component';
import { Coordonnees,GameState, CreateToken } from '../modele';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent {
  @Output() selectBox: EventEmitter<CreateToken> = new EventEmitter();

  @Input() gameState: GameState;

  addToken(createToken: CreateToken) {
    console.log(createToken.x,createToken.y)
    this.selectBox.emit(createToken);
  }
}
