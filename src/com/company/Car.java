package com.company;

import com.company.Exception.FieldOccupyException;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;


class Car {
    static private int id = 0;
    final private boolean isRed;
    final private boolean isHorizontal;
    private int carId;
    private Deque<Field> fields;

    private boolean isInFinish = false;

    Car(Deque<Field> fields, boolean isRed, boolean isHorizontal) throws FieldOccupyException {
        for (Field field : fields) {
            field.occupy();
        }
        this.fields = fields;
        this.isRed = isRed;
        this.isHorizontal = isHorizontal;
        carId = id++;
    }

    public boolean isInFinish() {
        return isInFinish;
    }

    public int getId() {
        return carId;
    }

    public void move(Direction direction, Field newField) throws FieldOccupyException {
        if (!isMoveValid(direction, newField))
            return;
        leaveField(direction);
        occupyField(direction, newField);

    }

    Collection<Field> getOccupiedPositions() {
        return Collections.unmodifiableCollection(fields);
    }

    private void appendTopField(Direction d, Field f) {
        if (d.equals(Direction.RIGHT) || d.equals(Direction.UP))
            fields.addLast(f);
        else
            fields.addFirst(f);
    }

    private Field getTailField(Direction d) {
        if (d.equals(Direction.RIGHT) || d.equals(Direction.UP))
            return fields.getFirst();
        else
            return fields.getLast();
    }

    private boolean isMoveValid(Direction direction, Field field) {
        if (field.isBorder()) return false;
        if (field.isOccupied()) return false;
        switch (direction) {
            case DOWN:
                if (isHorizontal) return false;
                break;
            case UP:
                if (isHorizontal) return false;
                break;
            case LEFT:
                if (!isHorizontal) return false;
                break;
            case RIGHT:
                if (!isHorizontal) return false;
                if (isRed && field.isExit()) {
                    isInFinish = true;
                    return true;
                }
                break;
        }

        return true;
    }

    private void occupyField(Direction direction, Field field) throws FieldOccupyException {
        field.occupy();
        appendTopField(direction, field);
    }

    private void leaveField(Direction d) throws FieldOccupyException {
        Field field = getTailField(d);
        field.leave();
        fields.remove(field);
    }


    Field getFieldCoordInDirection(Direction direction) {
        int x, y;
        if (isHorizontal) {
            y = fields.getFirst().y;
            if (direction.equals(Direction.LEFT)) {
                x = fields.getFirst().x - 1;
            } else {
                x = fields.getLast().x + 1;
            }
        } else {
            x = fields.getLast().x;
            if (direction.equals(Direction.DOWN)) {
                y = fields.getFirst().y - 1;
            } else {
                y = fields.getLast().y + 1;
            }
        }
        return new Field(x, y);
    }
}
