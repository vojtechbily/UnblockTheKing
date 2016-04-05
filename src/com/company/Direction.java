package com.company;

import com.sun.javaws.exceptions.InvalidArgumentException;

public enum Direction {
    LEFT(),
    RIGHT(),
    UP(),
    DOWN();

    public static Direction fromString(String s) throws InvalidArgumentException
    {
        for(Direction dir : Direction.values())
        {
            if(dir.name().equals(s))
            {
                return dir;
            }
        }
        throw  new InvalidArgumentException(new String[]{});
    }
}
