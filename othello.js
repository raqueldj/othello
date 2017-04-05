function tablero() {
let table = document.createElement("table");
    let matrix = [];
    let tr;
    let td;
    for (let i = 1; i <= 8; i++) {
        tr = document.createElement('tr');
        matrix[i] = new Array();
        for (let j = 1; j <= 8; j++) {
            td = document.createElement('td');
            matrix[i][j] = 0;
        }
        tr.appendChild(td);
    }
  table.appendChild(tr);

    matrix[4][4] = 2;
    matrix[4][5] = 1;
    matrix[5][4] = 1;
    matrix[5][5] = 2;

    console.log(matrix);
    document.body.appendChild(table);
}
/*
var table = document.createElement("table");
for (var i = 1; i < 9; i++) {
    var tr = document.createElement('tr');
    for (var j = 1; j < 9; j++) {
        var td = document.createElement('td');
        if (i%2 == j%2) {
            td.className = "white";
            let x =2;
        } else {
            td.className = "black";
            let x =1;
        }
        tr.appendChild(td);
    }
    table.appendChild(tr);
}
document.body.appendChild(table);*/
//export default tablero;