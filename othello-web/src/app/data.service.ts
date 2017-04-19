import { User, Game, CreateUser, CreateToken, CreateGame, GameState, Replay } from './modele';

import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { Injectable } from '@angular/core';
import { Headers, RequestOptions } from '@angular/http';

@Injectable()
export class DataService {

    constructor(private http: Http) { }

    getUsers(): Promise<User[]> {
        return this.http.get("http://localhost:8080/othello-0.0.1-SNAPSHOT/api/new-game")
            .toPromise()
            .then((response) => { return response.json() });
    }

    getGamesRunning(): Promise<Game[]> {
        return this.http.get("http://localhost:8080/othello-0.0.1-SNAPSHOT/api/load-game")
            .toPromise()
            .then((response) => { return response.json() });
    }

    loadGame(id: number): Promise<GameState> {
        return this.http.get("http://localhost:8080/othello-0.0.1-SNAPSHOT/api/load-game/"+id)
            .toPromise()
            .then((response) => { return response.json() as GameState});
    }

    createGameDS(game: CreateGame): Promise<GameState> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        console.log(game);

        return this.http.post("http://localhost:8080/othello-0.0.1-SNAPSHOT/api/new-game/game", game, options)
            .toPromise()
            .then((response) => { return response.json() as GameState});
    }

    replay(replay:Replay):Promise<GameState>{
 let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
         return this.http.post("http://localhost:8080/othello-0.0.1-SNAPSHOT/api/game/replay", replay, options)
            .toPromise()
            .then((response) => { return response.json() as GameState});
    }

    createUserDS(user: CreateUser): Promise<number> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        console.log(user);
        return this.http.post("http://localhost:8080/othello-0.0.1-SNAPSHOT/api/new-game/user", user, options)
            .toPromise()
            .then((response) => { return response.json() });
    }

    createToken(token: CreateToken): Promise<GameState> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        return this.http.post("http://localhost:8080/othello-0.0.1-SNAPSHOT/api/game", token, options)
            .toPromise()
            .then((response) => { return response.json() as GameState});
    }




}


