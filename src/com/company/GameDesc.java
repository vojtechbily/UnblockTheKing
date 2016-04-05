package com.company;

import com.company.exception.FieldOccupyException;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import static com.company.Const.*;

public class GameDesc
{

	private Field[][] fields = new Field[maxX][maxY];
	private Collection<Car> cars;
	private boolean gameWon = false;
	private String filename = "game.save";

	public Collection<Move> getMoves()
	{
		return Collections.unmodifiableCollection(moves);
	}

	private Queue<Move> moves = new LinkedList<>();

	public GameDesc()
	{
		initFields();
	}

	private void initFields()
	{
		for (int x = 0; x < maxX; x++)
		{
			for (int y = 0; y < maxX; y++)
			{
				fields[x][y] = new Field(x, y);
			}
		}
	}

	public void loadNewGame() throws Exception
	{
		cars = new ArrayList<>(10);

		// 1
		Deque<Field> carFields = new ArrayDeque<>(2);
		carFields.add(fields[1][6]);
		carFields.add(fields[2][6]);
		cars.add( new Car(carFields, false, true));

		// 2
		carFields = new ArrayDeque<>(3);
		carFields.add(fields[4][6]);
		carFields.add(fields[5][6]);
		carFields.add(fields[6][6]);
		cars.add( new Car(carFields, false, true));

		//3
		carFields = new ArrayDeque<>(2);
		carFields.add(fields[5][5]);
		carFields.add(fields[6][5]);
		cars.add( new Car(carFields, false, true));

		//4
		carFields = new ArrayDeque<>(2);
		carFields.add(fields[4][4]);
		carFields.add(fields[4][5]);
		cars.add( new Car(carFields, false, false));

		//5
		carFields = new ArrayDeque<>(2);
		carFields.add(fields[2][4]);
		carFields.add(fields[3][4]);
		cars.add( new Car(carFields, true, true));

		//6
		carFields = new ArrayDeque<>(2);
		carFields.add(fields[1][3]);
		carFields.add(fields[1][4]);
		cars.add( new Car(carFields, false, false));

		//7
		carFields = new ArrayDeque<>(2);
		carFields.add(fields[1][2]);
		carFields.add(fields[2][2]);
		cars.add( new Car(carFields, false, true));

		// 8
		carFields = new ArrayDeque<>(3);
		carFields.add(fields[3][1]);
		carFields.add(fields[3][2]);
		carFields.add(fields[3][3]);
		cars.add( new Car(carFields, false, false));

		// 9
		carFields = new ArrayDeque<>(2);
		carFields.add(fields[4][3]);
		carFields.add(fields[5][3]);
		cars.add( new Car(carFields, false, true));

		// 10
		carFields = new ArrayDeque<>(3);
		carFields.add(fields[6][2]);
		carFields.add(fields[6][3]);
		carFields.add(fields[6][4]);
		cars.add( new Car(carFields, false, false));

		// 11
		carFields = new ArrayDeque<>(3);
		carFields.add(fields[4][1]);
		carFields.add(fields[5][1]);
		carFields.add(fields[6][1]);
		cars.add( new Car(carFields, false, true));

	}

	public void moveCar(Car car, Direction direction) throws FieldOccupyException
	{
		if (direction == null) return;
		Field tempField = car.getFieldCoordInDirection(direction);
		Field field = fields[tempField.x][tempField.y];
		car.move(direction, field);
		moves.add(new Move(car, direction));
		if (car.isInFinish())
			gameWon = true;
	}

	public boolean gameWon()
	{
		return gameWon;
	}

	public Collection<Car> getCars()
	{
		return Collections.unmodifiableCollection(cars);
	}

	public void loadGame() throws Exception
	{
		FileDialog dialog = new java.awt.FileDialog((java.awt.Frame) null);
		dialog.setVisible(true);
		filename  = dialog.getFile();

		String fileContent  = new String(Files.readAllBytes(Paths.get(filename)));

		for(String line : fileContent.split("\n"))
		{
			int pos = line.indexOf(";");
			String carId = line.substring(0, pos);
			int carIdInt = Integer.parseInt(carId);
			String move = line.substring(pos+1);
			Direction dir = Direction.fromString(move);
			Car car = selectCar(carIdInt);
			moveCar(car,dir);
		}
	}

	public void saveGame() throws IOException
	{
		PrintWriter pw = new PrintWriter(filename);
		for(Move move :moves){
			pw.print(move.getCar().getId());
			pw.print(";");
			pw.print(move.getDirection());
			pw.print("\n");
		}
		pw.flush();
		pw.close();
	}

	public void resetGame() throws Exception
	{
		Car.resetId();
		initFields();
		loadNewGame();
		moves.clear();
	}

	public void stepBack() throws FieldOccupyException
	{
		if (moves.isEmpty())
			return;
		Move move = moves.poll();
		moveCar(move.getCar(), getOppositeDirection(move.getDirection()));
		moves.remove();
	}

	private Direction getOppositeDirection(Direction direction)
	{
		switch (direction)
		{
			case LEFT:
				return Direction.RIGHT;
			case RIGHT:
				return Direction.LEFT;
			case DOWN:
				return Direction.UP;
			case UP:
				return Direction.DOWN;
		}
		return null;
	}


	public Car selectCar(int carId)
	{
		for (Car car : cars)
		{
			if(car.getId() == carId)
				return car;
		}
		throw new IllegalArgumentException(""+carId);
	}
}
