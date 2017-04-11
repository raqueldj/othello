import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GamesRunningComponent } from './games-running.component';

describe('GamesRunningComponent', () => {
  let component: GamesRunningComponent;
  let fixture: ComponentFixture<GamesRunningComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GamesRunningComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GamesRunningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
