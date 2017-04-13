import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CreateUser } from '../modele';
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

  whiteId: number = 0;
  blackId: number = 0;

  constructor(private dataService: DataService) {
  }

  createUser(name, passWord) {
    this.createUserOutput.emit({
      name,
      passWord
    });
    console.log(name);
    console.log(passWord);
    this.dataService.createUserDS({ name, passWord });
  }

  createGame(whitePassWord, blackPassWord) {
    this.dataService.createGameDS({ whiteId: this.whiteId, blackId: this.blackId, whitePassWord, blackPassWord });
    this.createNewGame.emit();
  }
}
