export interface User {
    name : string;
    passWord : string;
    gameWin : number;
    gameLose : number;
}

export interface Game{
    id: number;
    player1: string;
    player2: string;
}