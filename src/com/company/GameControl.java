package com.company;

import com.company.Exception.InvalidCarSelected;

import java.util.Scanner;

public class GameControl {

    private final GameDesc gameDesc;
    int selectedCar = 0;
    private Scanner scanner = new Scanner(System.in);
    private String line = "";
    private Direction direction;

    GameControl(GameDesc gameDesc) {
        this.gameDesc = gameDesc;
    }

    private static Direction selectDir(String line) {
        if (line.length() != 1)
            return null;

        char key = line.charAt(0);

        switch (key) {
            case 'W':
            case 'w':
                return Direction.UP;
            case 'D':
            case 'd':
                return Direction.RIGHT;
            case 'S':
            case 's':
                return Direction.DOWN;
            case 'A':
            case 'a':
                return Direction.LEFT;
        }
        return null;
    }

    private int selectCar(String line) {
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
        }
        return selectedCar;
    }

    public void readInput() {
        line = scanner.nextLine();
        selectedCar = selectCar(line);
        direction = selectDir(line);
    }

    public boolean shouldExit() {
        return line.equals("e");
    }

    public void moveCar() throws Exception {

        Car car = chooseCar();
        gameDesc.moveCar(car, direction);

    }

    private Car chooseCar() throws InvalidCarSelected {
        for (Car car : gameDesc.getCars())
            if (car.getId() == selectedCar)
                return car;
        throw new InvalidCarSelected("Invalid car " + selectedCar + " selected");
    }
}
