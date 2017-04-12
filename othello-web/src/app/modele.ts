export interface User {
    name : string;
    passWord : string;
    gameWin : number;
    gameLose : number;
}

export interface Game {
    id : number;
    whiteUser : User;
    blackUser : User;
    running : boolean;
}

export interface CreateGame {
    whiteId : number;
    blackId : number;
    whitePassWord : String;
    blackPassWord : String;
}

export interface CreateUser {
    name : String;
    passWord : String;
}