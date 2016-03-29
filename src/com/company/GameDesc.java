package com.company;

import com.company.Exception.FieldOccupyException;

import java.util.*;

import static com.company.Const.*;

class GameDesc {

    private Field[][] fields = new Field[maxX][maxY];
    private Collection<Car> cars;
    private boolean gameWon = false;

    GameDesc() {
        initFields();
    }

    private void initFields() {
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxX; y++) {
                fields[x][y] = new Field(x, y);
            }
        }
    }

    void loadNewGame() throws FieldOccupyException {
        cars = new ArrayList<>(2);
        Deque<Field> car1Fields = new ArrayDeque<>(2);
        car1Fields.add(fields[1][exitY]);
        car1Fields.add(fields[2][exitY]);
        Car car1 = new Car(car1Fields, true, true);
        cars.add(car1);

        Deque<Field> car2Fields = new ArrayDeque<>(2);
        car2Fields.add(fields[4][3]);
        car2Fields.add(fields[4][4]);
        Car car2 = new Car(car2Fields, false, false);
        cars.add(car2);
    }


    void moveCar(Car car, Direction direction) throws FieldOccupyException {
        if (direction == null) return;
        Field tempField = car.getFieldCoordInDirection(direction);
        Field field = fields[tempField.x][tempField.y];
        car.move(direction, field);
        if (car.isInFinish())
            gameWon = true;
    }


    public boolean gameWon() {
        return gameWon;
    }

    public Collection<Car> getCars() {
        return Collections.unmodifiableCollection(cars);
    }

}
