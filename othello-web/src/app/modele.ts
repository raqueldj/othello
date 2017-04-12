export interface User {
    name : string;
    passWord : string;
    gameWin : number;
    gameLose : number;
}

export interface Game {
    id : number;
    player1 : User;
    player2 : User;
}