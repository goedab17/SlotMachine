
let roll = [1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4,
            5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7,
            7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9,
            9, 9, 9, 9, 9, 9, 9, 9, 9 ];

let imgRoll = [ "https://raw.githubusercontent.com/goedab17/SlotMachine/image/png/primogems.png",
                "https://raw.githubusercontent.com/goedab17/SlotMachine/image/png/fate_red.png",
                "https://raw.githubusercontent.com/goedab17/SlotMachine/image/png/fate_blue.png",
                "https://raw.githubusercontent.com/goedab17/SlotMachine/image/png/pyro_slime.png",
                "https://raw.githubusercontent.com/goedab17/SlotMachine/image/png/hydro_slime.png",
                "https://raw.githubusercontent.com/goedab17/SlotMachine/image/png/cryo_slime.png",
                "https://raw.githubusercontent.com/goedab17/SlotMachine/image/png/electro_slime.png",
                "https://raw.githubusercontent.com/goedab17/SlotMachine/image/png/dendro_slime.png",
                "https://raw.githubusercontent.com/goedab17/SlotMachine/image/png/geo_slime.png"];

let display, einsatz, balance;
let win = [10000, 2000, 1000, 600, 500, 400, 300, 200, 100];
let loop = false;

function genReelNumbers() {
    balance = document.getElementById("balance").value;
    einsatz = document.getElementById("bet").value;
    balance -= einsatz;
    display = [ [0, 0, 0], [0, 0, 0], [0, 0, 0] ];

    console.log("***** genReelNumbers: *****");
    for (let i = 0; i <= 2; i++) {
        display [i][0] = roll[Math.floor(Math.random() * roll.length)];
        do {
            display [i][1] = roll[Math.floor(Math.random() * roll.length)];
        } while (display[i][1] == display[i][0])

        do {
            display [i][2] = roll[Math.floor(Math.random() * roll.length)];
        } while(display[i][2] == display[i][0] || display[i][2] == display[i][0])
    }

    document.getElementById("slot").innerHTML =
        "<tr><th><img id='icon1' src='"+ imgRoll[display[0][0]-1] +"' class='rotate1' width='120' height='120'></th>" +
            "<th><img id='icon2' src='"+ imgRoll[display[0][1]-1] +"' class='rotate2' width='120' height='120'></th>" +
            "<th><img id='icon3' src='"+ imgRoll[display[0][2]-1] +"' class='rotate3' width='120' height='120'></th></tr>" +
        "<tr><th><img id='icon4' src='"+ imgRoll[display[1][0]-1] +"' class='rotate1' width='120' height='120'></th>" +
            "<th><img id='icon5' src='"+ imgRoll[display[1][1]-1] +"' class='rotate2' width='120' height='120'></th>" +
            "<th><img id='icon6' src='"+ imgRoll[display[1][2]-1] +"' class='rotate3' width='120' height='120'></th></tr>" +
        "<tr><th><img id='icon7' src='"+ imgRoll[display[2][0]-1] +"' class='rotate1' width='120' height='120'></th>" +
            "<th><img id='icon8' src='"+ imgRoll[display[2][1]-1] +"' class='rotate2' width='120' height='120'></th>" +
            "<th><img id='icon9' src='"+ imgRoll[display[2][2]-1] +"' class='rotate3' width='120' height='120'></th></tr>";
    animation();
}

function animation() {
    console.log("***** Animation: *****");
    document.getElementById("icon1").classList.add('rotate1');
    document.getElementById("icon2").classList.add('rotate2');
    document.getElementById("icon3").classList.add('rotate3');
    document.getElementById("icon4").classList.add('rotate1');
    document.getElementById("icon5").classList.add('rotate2');
    document.getElementById("icon6").classList.add('rotate3');
    document.getElementById("icon7").classList.add('rotate1');
    document.getElementById("icon8").classList.add('rotate2');
    document.getElementById("icon9").classList.add('rotate3');
}

function checkMatch() {
    console.log("***** checkMatch: *****")
    let rightAligned = false;
    let bigWin = false;
    let moneyWon = 0;
    // horizontal winCheck
    for (let i = 0; i < 3; i++) {
        if(display[i][0] == display[i][1]) {
            if(display[i][0] == display[i][2]) {
                moneyWon += winMoney(display[i][0]);
            }
            // else { moneyWon +=winMoney(false, (display[i][0]));}
        }
    }

    // diagonal winCheck (up - down)
    if(display[0][0] == display[1][1]) {
        if(display[0][0] == display[2][2]) {
            moneyWon += winMoney(display[0][0]);
        }
        // else { moneyWon += winMoney(false, (display[0][0]));}
    }

    // diagonal winCheck (down - up)
    if(display[2][0] == display[1][1]) {
        if(display[2][0] == display[0][2]) {
            moneyWon += winMoney(display[2][0]);
        }
        // else { moneyWon += winMoney(false, (display[2][0]));}
    }

    document.getElementById("cwin").value = moneyWon;
    balance += moneyWon;
}

function winMoney(winIndex) { return win[winIndex-1]*einsatz/10;}

function autoSpin() {
    console.log("*****AutoSpin")
    autoBtn = document.getElementById("auto");
    if(autoBtn.value == "off") {
        autoBtn.value = "on"; autoBtn.innerHTML =  "AUTO (ON)";
        loop = true;
    } else {
        autoBtn.value = "off"; autoBtn.innerHTML =  "AUTO (OFF)";
        loop = false;
    }

}

function spin() {
    document.getElementById("cwin").value = 0;
    document.getElementById("btspin").disabled = true;
    genReelNumbers();
    setTimeout(() => {
        checkMatch();
        document.getElementById("balance").value = balance;
        document.getElementById("btspin").disabled = false;
    }, 2500);

}