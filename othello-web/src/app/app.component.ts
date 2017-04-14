import { Component } from '@angular/core';
import { DataService } from './data.service';
import { Coordonnees, CreateToken } from './modele';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  state = "Menu"
  title = 'app works!';
  private dataService: DataService;

  constructor(dataservice: DataService) {
    this.dataService = dataservice;
  }

  //tokens: Coordonnees[] = [{ x: 1, y: 2 }];
  token: CreateToken;

  /*onSelectedBox(coordonnee: Coordonnees) {
    console.log(`yipiyeah ${coordonnee.x} ${coordonnee.y}`);
    this.tokens.push({ x: coordonnee.x, y: coordonnee.y });
  }*/

  loadGame(gameId) {
    console.log(`CHARGEMENT DE LA PARTIE ${gameId}`);
  }

  createToken(x, y, gameId) {
    this.dataService.createToken({ x, y, idGame });
  }
}
