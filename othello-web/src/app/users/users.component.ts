import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CreateUser, User } from '../modele';
import { UsersListComponent } from '../users-list/users-list.component';
import { DataService } from '../data.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css'],
  providers: [DataService]
})
export class UsersComponent {
  @Output() createUserOutput: EventEmitter<CreateUser> = new EventEmitter();
  @Output() createNewGame: EventEmitter<null> = new EventEmitter();

  idWhite: number = 0;
  idBlack: number = 0;

  constructor(private dataService: DataService) {
        this.dataService.getUsers().then(users => this.users = users);

  }
  users: User[];

  createUser(name, passWord) {
    this.createUserOutput.emit({
      name,
      passWord
    });
    console.log(name);
    console.log(passWord);
    this.dataService.createUserDS({ name, passWord });
  }

  createGame(passWordWhite, passWordBlack) {
    this.dataService.createGameDS({ idWhite: this.idWhite, idBlack: this.idBlack, passWordWhite, passWordBlack });
    this.createNewGame.emit();
    this.dataService.getUsers().then(users => this.users = users);
  }
  
}
