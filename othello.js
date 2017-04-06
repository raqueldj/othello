$(document).ready(function () {
    tablero();
});

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
            //matrix.textContent = matrix[i][j];
            td.append(matrix[i][j]);
            tr.append(td);
        }

        table.append(tr);
    }

    matrix[4][4] = 2;
    matrix[4][5] = 1;
    matrix[5][4] = 1;
    matrix[5][5] = 2;

    console.log(matrix);
    document.body.append(table);
}
