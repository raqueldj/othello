import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-data',
  templateUrl: './data.component.html',
  styleUrls: ['./data.component.css']
})
export class DataComponent {
  @Input() player1;
  @Input() player2;

  @Output() replay: EventEmitter<null> = new EventEmitter();
  onReplay() {
    this.replay.emit();
  }
}
