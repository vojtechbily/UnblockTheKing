package com.company;

import com.company.exception.FieldOccupyException;

import java.awt.Point;

import static com.company.Const.*;

public class Field extends Point {
    private boolean isOccupied = false;

    public Field(int x, int y) {
        super(x, y);
    }

    boolean isOccupied() {
        return isOccupied;
    }

    boolean isExit() {
        return getLocation().equals(new Point(maxX - 1, exitY));
    }

	/**
	 * Exit is not a border field!!!
     * @return
     */
    boolean isBorder() {
        return !isExit() && (
                getX() == 0 ||
                        getX() == maxX - 1 ||
                        getY() == 0 ||
                        getY() == maxY - 1
        );
    }

    public void occupy() throws FieldOccupyException {
        if (isOccupied)
            throw new FieldOccupyException(String.format("Field [%d % d] is already occupied.", x, y));
        if(isBorder())
            throw new FieldOccupyException(String.format("Field [%d % d] is border.", x, y));
        isOccupied = true;
    }

    public void leave() throws FieldOccupyException {
        if (!isOccupied)
            throw new FieldOccupyException(String.format("Field [%d % d] cannot be leaved becaused it was not occupied.", x, y));
        isOccupied = false;
    }
}
