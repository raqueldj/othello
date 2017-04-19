import { User, Game, CreateUser, CreateToken, CreateGame, GameState } from './modele';

import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';

import { environment } from '../environments/environment';

@Injectable()
export class DataService {
  private baseUrl = environment.webServiceUrl;

    constructor(private http: Http) { }

    getUsers(): Promise<User[]> {
        return this.http.get(this.baseUrl+"new-game")
            .toPromise()
            .then((response) => { return response.json() });
    }

    getGamesRunning(): Promise<Game[]> {
        return this.http.get(this.baseUrl+"load-game")
            .toPromise()
            .then((response) => { return response.json() });
    }

    loadGame(id: number): Promise<GameState> {
        return this.http.get(this.baseUrl+"load-game/"+id)
            .toPromise()
            .then((response) => { return response.json() as GameState});
    }

    createGameDS(game: CreateGame): Promise<GameState> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        console.log(game);

        return this.http.post(this.baseUrl+"new-game/game", game, options)
            .toPromise()
            .then((response) => { return response.json() as GameState});
    }

    createUserDS(user: CreateUser): Promise<number> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        console.log(user);
        return this.http.post(this.baseUrl+"new-game/user", user, options)
            .toPromise()
            .then((response) => { return response.json() });
    }

    createToken(token: CreateToken): Promise<GameState> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        return this.http.post(this.baseUrl+"game", token, options)
            .toPromise()
            .then((response) => { return response.json() as GameState});
    }
}


