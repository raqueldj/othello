import { Component, OnInit } from '@angular/core';
import { User } from '../modele';
import { DataService } from '../data.service';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css'],
  providers: [DataService]
})
export class UsersListComponent {

  constructor(private dataService: DataService) {

    this.dataService.getUsers().then(users => this.users = users);


  }

  users: User[];
}
