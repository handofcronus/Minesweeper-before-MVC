package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Main {

    public static final int WIDHT=1000, HEIGHT=1000;
    public static final int BOARDSIZE=15;
    public static int time=120;
    public static int score=0;

    public static JLabel scorelabel = new JLabel("Score:"+String.valueOf(Main.score));
    public static boolean lost=false;
    public static Cell[][] board = new Cell[BOARDSIZE][BOARDSIZE];
    public static JButton[][] buttons = new JButton[BOARDSIZE][BOARDSIZE];

    public static void main(String[] args)
    {
	    new GUI();

    }
}
