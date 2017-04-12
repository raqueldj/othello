import { Component, Input, Output, EventEmitter } from '@angular/core';


import { CreateToken } from '../modele';
import { DataService } from '../data.service';

@Component({
  selector: 'app-token',
  templateUrl: './token.component.html',
  styleUrls: ['./token.component.css'],
  providers: [DataService]
})
export class TokenComponent {
  @Input() token;
  @Output() position: EventEmitter<null> = new EventEmitter();

  constructor(private dataService: DataService) {
  }

tokenPosition: CreateToken[]; //= this.dataService.tokenPosition;
}
