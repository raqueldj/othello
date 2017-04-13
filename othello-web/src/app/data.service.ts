import { User, Game, CreateUser, CreateToken } from './modele';

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

    //createGame(): Promise<Game>
    createGame(game: Game): Promise<Game> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        return this.http.post("http://localhost:8080/othello-0.0.1-SNAPSHOT/api/game", game , options)
            .toPromise()
            .then((response) => { return response.json() });
    }

    createUserDS(user: CreateUser): Promise<CreateUser> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        console.log(user);
        return this.http.post("http://localhost:8080/othello-0.0.1-SNAPSHOT/api/new-game/user", user , options)
            .toPromise()
            .then((response) => { return response.json() });
    }

    createToken(token: CreateToken): Promise<CreateToken> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        return this.http.post("http://localhost:8080/othello-0.0.1-SNAPSHOT/api/game", token, options)
            .toPromise()
            .then((response) => { return response.json() });
    }

}


