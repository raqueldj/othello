import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { SetComponent } from './set/set.component';
import { TokenComponent } from './token/token.component';
import { DataComponent } from './data/data.component';
import { MenuComponent } from './menu/menu.component';
import { UsersComponent } from './users/users.component';
import { GamesRunningComponent } from './games-running/games-running.component';
import { UsersListComponent } from './users-list/users-list.component';

@NgModule({
  declarations: [
    AppComponent,
    SetComponent,
    TokenComponent,
    DataComponent,
    MenuComponent,
    UsersComponent,
    GamesRunningComponent,
    UsersListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
