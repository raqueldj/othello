import { Component } from '@angular/core';
import { DataService } from './data.service';

import { ScreenState } from './modele';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app works!';
  private dataService: DataService;

 constructor(dataservice: DataService) {
   this.dataService = dataservice;

 }

 state: ScreenState = {
   Menu : "Menu",
   Users: "Users",
   GamesRunning: "GamesRunning",
   Game: "Game"
  }
}
