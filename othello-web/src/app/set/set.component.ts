import { Component } from '@angular/core';

@Component({
  selector: 'app-set',
  templateUrl: './set.component.html',
  styleUrls: ['./set.component.css']
})
export class SetComponent {


  getCoordonnee(i,j){

    console.log(j+1,8-i);
  }
}
