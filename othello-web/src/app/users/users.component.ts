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
export class UsersComponent{

}
