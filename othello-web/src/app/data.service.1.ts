import { User , Game } from './modele';

import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {Injectable} from '@angular/core';

@Injectable()
export class DataService {

    constructor(private http:Http){}

    getUsers() : Promise<User[]> {
        return this.http.get("http://localhost:8080/othello-0.0.1-SNAPSHOT/api/new-game")
            .toPromise()
            .then ((response) => {return response.json()});
    }

    getRunningGames() : void{
        this.http.get("http://localhost:8080/othello-0.0.1-SNAPSHOT/api/game/running")
        .toPromise()
        .then((response)=>{(response.json() as Game[]).forEach(game=>this.runningGames.push(game))});


    }


    runningGames: Game[] = [];
}


