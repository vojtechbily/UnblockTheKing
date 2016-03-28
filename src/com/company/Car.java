package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Enter a paragraph that summarizes what the class does and why someone might want to utilize it <p> © 2016
 * NetSuite Inc.
 *
 * @author vbily
 * @since 2016-03-24
 */
class Car
{
	private Field startPos;
	final private int length;
	final private boolean isRed;
	final private boolean isHorizontal;
	final private char id;

	Car(Field startPos, int length, boolean isRed, boolean isHorizontal, char id)
	{
		this.startPos = startPos;
		this.length = length;
		this.isRed = isRed;
		this.isHorizontal = isHorizontal;
		this.id = id;
	}

	public char getId()
	{
		return id;
	}

	boolean isOnField(Field testedField)
	{
		return getOccupiedPositions().contains(testedField);
	}

	public void move(Direction direction)
	{
		if (!isMoveValid(direction))
			return;
		switch (direction)
		{
			case DOWN:
				startPos.move(0, -1);
				break;
			case UP:
				startPos.move(0, 1);
				break;
			case LEFT:
				startPos.move(-1, 0);
				break;
			case RIGHT:
				startPos.move(1, 0);
				break;
		}
	}

	private boolean isMoveValid(Direction direction)
	{
		return true;
	}

	public List<Field> getOccupiedPositions()
	{
		List<Field> fields = new ArrayList<>(length);
		for (int i = 0; i < length; i++)
		{
			Field field = new Field(startPos.getX(), startPos.getY());
			if (isHorizontal)
				field.move(i, 0);
			else
				field.move(0, i);
			fields.add(field);
		}
		return fields;
	}
}
