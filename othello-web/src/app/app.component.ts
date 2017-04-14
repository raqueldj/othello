import { Component } from '@angular/core';
import { DataService } from './data.service';


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

  loadGame(gameId) {
    console.log(`CHARGEMENT DE LA PARTIE ${gameId}`);
  }
}
