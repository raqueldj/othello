import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent {

  @Output() newGame: EventEmitter<null> = new EventEmitter();
  @Output() loadGame: EventEmitter<null> = new EventEmitter();

  onNewGame() {
    this.newGame.emit();
  }
  onLoadGame() {
    this.loadGame.emit();
  }
}
