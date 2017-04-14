export interface User {
    id : number;
    name : string;
    passWord : string;
}

export interface Game {
    id : number;
    whiteUser : User;
    blackUser : User;
}

export interface CreateGame {
    idWhite : number;
    idBlack : number;
    passWordWhite : String;
    passWordBlack : String;
}

export interface CreateUser {
    name : String;
    passWord : String;
}

export interface CreateToken {
    x : number;
    y : number;
    idGame : number; 
}

export interface GameState {
    id: number;
    set : number[][];
    WhiteUser : User;
    BlackUser : User;
    scorePW : number;
    scorePB : number;
    isRunning : boolean;
    whitePlays : boolean;
}

export interface LoadGame {
    idGame : number;
}

export interface Replay {
    id1 : number;
    id2 : number;
}
