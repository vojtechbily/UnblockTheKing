package com.company;

public class Main {

    public static void main(String[] args)  throws Exception
    {

        char key =' ' ;
        Desc desc = new Desc();
        desc.loadNewGame();
        while(key != 'e')
        {
            key = (char) System.in.read();
            desc.printWholeDesc();
        }

    }
}
