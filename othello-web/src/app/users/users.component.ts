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
  idCreate: number;
  users: User[];

  constructor(private dataService: DataService) {
    this.dataService.getUsers().then(users => {
      this.idBlack = users[0].id;
      this.idWhite = users[0].id;
      this.users = users;
    });
  }

  createUser(name, passWord) {
    this.createUserOutput.emit({
      name,
      passWord
    });
    console.log(name);
    console.log(passWord);
    this.dataService.createUserDS({ name, passWord }).then(Response => { this.idCreate = Response; console.log(this.idCreate) });
    this.users.push({ id: this.idCreate, name: name, passWord: passWord });

  }

  createGame(passWordWhite, passWordBlack) {
    this.dataService.createGameDS({ idWhite: this.idWhite, idBlack: this.idBlack, passWordWhite, passWordBlack });
    this.createNewGame.emit();
  }

}
