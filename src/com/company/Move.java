package com.company;

public class Move
{
	public Move(Car car, Direction direction ){
		this.car = car;
		this.direction = direction;
	}
	private Car car;

	public Direction getDirection()
	{
		return direction;
	}

	public Car getCar()
	{
		return car;
	}

	private Direction direction;
}
