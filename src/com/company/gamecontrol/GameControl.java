package com.company.gamecontrol;

public interface GameControl
{
	void readInput() throws Exception;
	public boolean shouldExit();
	public void performAction() throws Exception;
}
