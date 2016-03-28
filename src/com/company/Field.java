package com.company;

import java.awt.*;

import static com.company.Const.maxX;
import static com.company.Const.maxY;

/**
 * TODO: Enter a paragraph that summarizes what the class does and why someone might want to utilize it <p> © 2016
 * NetSuite Inc.
 *
 * @author vbily
 * @since 2016-03-24
 */
public class Field extends Point
{
	Field(int x, int y){
		super(x,y);
	}

	Field(double x, double y){
		super((int)x,(int)y);
	}

	boolean isExit(){
		return getLocation().equals(new Point(maxX,4));
	}

	boolean isBorder()
	{
		return getX() == 0 ||
	           getX() == maxX ||
	           getY() == 0 ||
	           getY() == maxY;
	}


}
