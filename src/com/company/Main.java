package com.company;

import com.company.Exception.InvalidCarSelected;

public class Main {
    public static void main(String[] args) throws Exception {


        GameDesc gameDesc = new GameDesc();
        gameDesc.loadNewGame();

        GameControl gc = new GameControl(gameDesc);
        UI ui = new UI(gameDesc);

        while (!gc.shouldExit()) {
            ui.printWholeDesc();
            gc.readInput();
            try {
                gc.moveCar();
            } catch (InvalidCarSelected e) {
                ui.logException(e);
            }
            if (gameDesc.gameWon()) {
                System.out.println("GAME WON.");
                return;
            }
        }
        System.out.println("GAME exited.");
    }
}
