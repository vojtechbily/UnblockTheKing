package com.company.ui;

import com.company.Car;
import com.company.Field;
import com.company.GameDesc;
import com.company.Move;

import java.util.Collection;

import static com.company.Const.maxX;
import static com.company.Const.maxY;

public class UIConsole implements UI
{

	private final GameDesc gameDesc;
	private String exception = "";

	public UIConsole(GameDesc gameDesc)
	{
		this.gameDesc = gameDesc;
	}

	public void logException(Exception e)
	{
		exception = e.getMessage();
	}

	private void printException()
	{
		if (!exception.isEmpty())
		{
			System.out.println(exception);
			exception = "";
		}
	}

	public void printWholeDesc()
	{
		// todo emptyDesc + borders + exit  as constant
		char[][] output = new char[maxX][maxY];
		clearConsole();
		printEmptySpaces(output);
		printBorders(output);
		printExit(output);
		printCars(output, gameDesc.getCars());
		print(output);
		printException();
	}

	private void printEmptySpaces(char[][] desc)
	{
		for (int y = 0; y < desc.length; y++)
		{
			for (int x = 0; x < desc[y].length; x++)
			{
				desc[x][y] = ' ';
			}
		}
	}

	private void printCars(char[][] desc, Collection<Car> cars)
	{
		for (Car car : cars)
		{
			char carId = car.getId() < 10 ? (char) (car.getId() + '0') : (char) (car.getId() + 'A'-10);
			for (Field field : car.getOccupiedPositions())
			{

				desc[field.x][field.y] = carId;
			}
		}
	}

	private void printExit(char[][] desc)
	{
		desc[maxX - 1][4] = '=';
	}

	private void print(char[][] desc)
	{
		for (int y = maxY - 1; y >= 0; y--)
		{
			StringBuilder toPrint = new StringBuilder(7);
			for (int x = 0; x < maxX; x++)
			{
				toPrint.append(desc[x][y]);
			}
			System.out.println(toPrint.toString());
		}
	}

	private void printBorders(char[][] desc)
	{
		for (int i = 0; i < maxX; i++)
		{
			desc[i][0] = 'X';
			desc[0][i] = 'X';
			desc[i][maxY - 1] = 'X';
			desc[maxX - 1][i] = 'X';
		}
	}

	private void clearConsole()
	{
		for (int i = 0; i < 20; i++)
		{
			System.out.println("\n");
		}
	}

	public void printMoves()
	{
		System.out.println("Game ended in " + gameDesc.getMoves().size() + " moves");
		for (Move move : gameDesc.getMoves())
		{
			System.out.println(String.format("Car %d moved %s", move.getCar().getId(), move.getDirection()));
		}
	}
}
