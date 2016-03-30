package com.company;

import com.company.exception.InvalidCarSelected;
import com.company.gamecontrol.GameControl;
import com.company.gamecontrol.GameControlConsole;
import com.company.ui.UI;
import com.company.ui.UIConsole;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		GameDesc gameDesc = new GameDesc();
		gameDesc.loadNewGame();

		GameControl gc = new GameControlConsole(gameDesc);
		UI ui = new UIConsole(gameDesc);

		while (!gc.shouldExit())
		{
			ui.printWholeDesc();
			gc.readInput();
			try
			{
				gc.performAction();
			}
			catch (InvalidCarSelected e)
			{
				ui.logException(e);
			}
			if (gameDesc.gameWon())
			{
				System.out.println("GAME WON.");
				ui.printMoves();
				return;
			}
		}
		System.out.println("GAME exited.");
		ui.printMoves();
	}
}
