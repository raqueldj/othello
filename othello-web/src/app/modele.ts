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