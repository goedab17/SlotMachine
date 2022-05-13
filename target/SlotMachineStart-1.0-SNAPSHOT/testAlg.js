
let roll = [1, 2, 2, 3, 3, 3, 4, 4, 4, 4,
            5, 5, 5, 5, 5, 6, 6, 6, 6, 6,
            6, 7, 7, 7, 7, 7, 7, 7, 8, 8,
            8, 8, 8, 8, 8, 8, 9, 9, 9, 9,
            9, 9, 9, 9, 9];
let display;
let a=0, b=0, c=0;
let win = [1000, 500, 300, 200, 200, 200, 100, 100, 100];
let einsatz = 10;
let balance;

function genReelNumbers() {
    balance = document.getElementById("balance").innerHTML;
    balance -= einsatz;
    display = [ [0, 0, 0], [0, 0, 0], [0, 0, 0] ];
    a = roll[Math.floor(Math.random() * roll.length)];
    b = roll[Math.floor(Math.random() * roll.length)];
    c = roll[Math.floor(Math.random() * roll.length)];
    display[0][Math.floor(Math.random() * 3)] = a;
    display[1][Math.floor(Math.random() * 3)] = b;
    display[2][Math.floor(Math.random() * 3)] = c;
    console.log("***** genReelNumbers: ");
    console.log(display[0][0] + " " + display[1][0] + " " + display[2][0]);
    console.log(display[0][1] + " " + display[1][1] + " " + display[2][1]);
    console.log(display[0][2] + " " + display[1][2] + " " + display[2][2]);
}

function checkMatch() {
    let rightAligned = false;

    if((display[0][0] == a && display[1][1] == b && display[2][2] == c) ||
        (display[0][0] == a && display[1][1] == b)) {
        rightAligned = true;
    }

    if((display[0][1] == a && display[1][1] == b && display[2][1] == c) ||
        (display[0][1] == a && display[1][1] == b)) {
        rightAligned = true;
    }

    if((display[0][2] == a && display[1][1] == b && display[2][0] == c) ||
        (display[0][2] == a && display[1][1] == b)) {
        rightAligned = true;
    }

    if(rightAligned) {
        if(a == b && a == c) winMoney(true, b);
        if(a == b && a != c) { winMoney(false, b);}
    }
}

function winMoney(bigWin, winIndex) {
    let moneyWon = 0;
    if(bigWin) {
        console.log("***** BIG WIN!!!")
        moneyWon += win[winIndex-1]*einsatz;
    } else {
        console.log("***** WIN!!!")
        moneyWon += win[winIndex-1]*einsatz/10;
    }
    balance += moneyWon;
}

function spin() {
    genReelNumbers();
    checkMatch();
    document.getElementById("balance").innerHTML = balance;
}