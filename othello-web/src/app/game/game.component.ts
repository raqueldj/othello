import { Component, EventEmitter, Output, Input } from '@angular/core';
import { SetComponent } from '../set/set.component';
import { TokenComponent } from '../token/token.component';
import { DataComponent } from '../data/data.component';
import { Coordonnees } from '../modele';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent {
  @Output() selectBox: EventEmitter<Coordonnees> = new EventEmitter();

  @Input() tokens: Coordonnees[];

  onSelectedBox(coordonnee: Coordonnees) {
    this.selectBox.emit(coordonnee);
  }
}
