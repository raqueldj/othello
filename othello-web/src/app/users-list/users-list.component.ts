import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { User } from '../modele';
import { DataService } from '../data.service';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css'],
  providers: [DataService]
})
export class UsersListComponent {
  @Input() users: User[] = [];
  @Output() selected = new EventEmitter<number>();

  onSelect(id) {
    this.selected.emit(id * 1);
  }
}
