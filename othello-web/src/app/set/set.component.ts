import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CreateToken, Coordonnees } from '../modele';
import { DataService } from '../data.service';


@Component({
  selector: 'app-set',
  templateUrl: './set.component.html',
  styleUrls: ['./set.component.css'],
  providers: [DataService]
})
export class SetComponent {

  @Output() selectBox: EventEmitter<Coordonnees> = new EventEmitter();

  @Input() tokens: Coordonnees[];

  getCoordonnee(j, i) {
    this.selectBox.emit({ x: j + 1, y: 8 - i });
  }

  hasWhiteToken(j, i) {
    //this.tokens.some(token => token.x == i && token.y == j);
    for (let token of this.tokens) {
      if (token.x == j && token.y == i)
        return true;
    }
    return false;
  }

  /*@Input() token;
  @Input() idGame : number;
  @Output() position: EventEmitter<CreateToken> = new EventEmitter();

  constructor(private dataService: DataService) {
  }

  createToken(x, y, idGame) {
    this.position.emit({
      x,
      y,
      idGame
    });

    this.dataService.createToken({ x, y, idGame });
  }*/
}
