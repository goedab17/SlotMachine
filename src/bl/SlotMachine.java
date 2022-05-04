package bl;

import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class SlotMachine {
    private int reel1, reel2, reel3;
    private int[][] display;
    private int[] roll = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4,
                          5, 5, 5, 5, 5, 6, 6, 6, 6, 6,
                          6, 7, 7, 7, 7, 7, 7, 7, 8, 8,
                          8, 8, 8, 8, 8, 8, 9, 9, 9, 9,
                          9, 9, 9, 9, 9};
    private int[] win = {1000, 500, 300, 200, 200, 200, 100, 100, 100};

    private static int balance = 1000;
    private static int einsatz = 10;

    public static void main(String[] args) {
        SlotMachine slot = new SlotMachine();
        Scanner scan = new Scanner(System.in);
        int trys = 0;

        while(balance >= 0 && balance <=100000) {
            System.out.println("Current Balance: " + balance + "$");
            System.out.print("Einsatz (10$; 20$; 30$): ");
            //einsatz = scan.nextInt();

            balance -= einsatz;
            slot.buttonClicked();
            trys++;
        }
        System.out.printf("Versuche: " + trys);
    }

    public void buttonClicked() {
        genReelNumbers();
        checkMatch();
    }

    public void checkMatch() {
        // Test Check
        /*if(display[1][1] == display[0][1] &&
                display[1][1] == display[2][1] &&
                display[0][1] != 0 &&
                display[1][1] != 0 &&
                display[2][1] != 0){
            System.out.println("EPIC WIN");
            win++;
        } else if(display[1][1] == display[0][0] &&
                display[1][1] == display[2][2] &&
                display[0][0] != 0 &&
                display[1][1] != 0 &&
                display[2][2] != 0) {
            System.out.println("EPIC WIN");
            win++;
        } else if(display[1][1] == display[0][2] &&
                display[1][1] == display[2][0] &&
                display[0][2] != 0 &&
                display[1][1] != 0 &&
                display[2][0] != 0) {
            System.out.println("EPIC WIN");
            win++;
        }*/
        boolean rightAligned = false;
        int a = 0, b = 0, c = 0;
        if((display[0][0] != 0 && display[1][1] != 0 && display[2][2] != 0) ||
                (display[0][0] != 0 && display[1][1] != 0)) {
            rightAligned = true; a = display[0][0]; b = display[1][1]; c = display[2][2];
        }

        if((display[0][1] != 0 && display[1][1] != 0 && display[2][1] != 0) ||
                (display[0][1] != 0 && display[1][1] != 0)) {
            rightAligned = true; a = display[0][1]; b = display[1][1]; c = display[2][1];
        }

        if((display[0][2] != 0 && display[1][1] != 0 && display[2][0] != 0) ||
                (display[0][2] != 0 && display[1][1] != 0)) {
            rightAligned = true; a = display[0][2]; b = display[1][1]; c = display[2][0];
        }

        if(rightAligned) {
            if(a == b && a == c) winMoney(true, b);
            if(a == b && a != c) { winMoney(false, b);}
        }
    }

    private void winMoney(boolean bigWin, int winIndex) {
        int moneyWon = 0;
        if(bigWin) {
            System.out.println("BIG WIN!!! ");
            moneyWon += win[winIndex-1]*einsatz;
        } else {
            System.out.println("WIN!!! ");
            moneyWon += win[winIndex-1]*einsatz/10;
        }
        balance += moneyWon;
        System.out.println(moneyWon + "$");
        System.out.println("------------------------------------------");
    }

    public void genReelNumbers() {
        // todo
        Random rand = new Random();
        reel1 = roll[rand.nextInt(roll.length)];
        reel2 = roll[rand.nextInt(roll.length)];
        reel3 = roll[rand.nextInt(roll.length)];
        display = new int[3][3];
        display[0][rand.nextInt(3)] = reel1;
        display[1][rand.nextInt(3)] = reel2;
        display[2][rand.nextInt(3)] = reel3;

        System.out.println();
        for (int i = 0; i < 3; i++) {

            if(i == 1) { System.out.print("-> ");
            } else { System.out.print("   "); }

            for (int j = 0; j < 3; j++) {
                System.out.print((display[j][i] + " ").replace("0", " "));
            }

            if(i == 1) { System.out.println("<-");
            } else {
                System.out.println("  ");
            }
        }
    }
}
