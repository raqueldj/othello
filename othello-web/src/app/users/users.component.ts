import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CreateUser, User, GameState,CreateGame } from '../modele';
import { UsersListComponent } from '../users-list/users-list.component';
import { DataService } from '../data.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
  providers: [DataService]
})
export class UsersComponent {
  @Input() gameState: GameState;
  @Input() newUsers: User[];

  @Output() createUser: EventEmitter<CreateUser> = new EventEmitter();
  @Output() createNewGame: EventEmitter<CreateGame> = new EventEmitter();

  idWhite: number = 0;
  idBlack: number = 0;
  idCreate: number;
  users: User[];

  

  addUser(name, passWord) {
    this.createUser.emit({
      name,
      passWord
    });
   
  }

  createGame(passWordWhite, passWordBlack) {
    this.createNewGame.emit({ idWhite: this.idWhite, idBlack: this.idBlack, passWordWhite, passWordBlack });
  }
}
