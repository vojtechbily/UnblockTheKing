package com.company;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import static com.company.Const.maxX;
import static com.company.Const.maxY;

/**
 * TODO: Enter a paragraph that summarizes what the class does and why someone might want to utilize it <p> © 2016
 * NetSuite Inc.
 *
 * @author vbily
 * @since 2016-03-24
 */
class Desc
{

	private Field[][] fields = new Field[maxX][maxY];
	private List<Car> cars;

	Desc()
	{
		for(int x = 0; x < maxX;x++){
			for(int y = 0; y < maxX;y++){
			  fields[x][y] = new Field(x,y);
			}
		}
	}

	void loadNewGame()
	{
		cars = new ArrayList<>(6);
	}

	void printWholeDesc()
	{
		char[][] desc = new char[maxX][maxY];
		clearConsole();
		printBorders(desc);
		printExit(desc);
		printCars(desc);
		print(desc);
	}

	private void printCars(char[][] desc)
	{
		for(Car car : cars)
		{
			for(Field field : car.getOccupiedPositions())
			{
				desc[field.x][field.y] = car.getId();
			}
		}
	}

	private void printExit(char[][] desc) {
		desc[maxX-1][4] = '=';
	}

	private void print(char[][] desc)
	{
		for (int y = maxY-1; y >= 0; y--)
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
			desc[i][maxY-1] = 'X';
			desc[maxX-1][i] = 'X';
		}
	}

	private void clearConsole()
	{
		for (int i = 0; i < 300; i++)
		{
			System.out.println("\n");
		}
	}



}
