package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Cell
{
    // 0 blank 1 mine 2 number 3 fake mine
    private int type;
    private int x,y;
    private int adjacentmines = 0;
    private boolean discovered;
    private boolean adjfakemine;
    private boolean flagged;


    public Cell(int type, int x,int y, boolean discovered, boolean flagged)
    {
        this.type = type;
        this.x=x;
        this.y=y;
        this.discovered = discovered;
        this.flagged = flagged;

    }
    public void setAdjfakemine(boolean b){adjfakemine=b;}
    public boolean isAdjfakemine(){return adjfakemine;}
    public int getType() {return type;}
    public int getX() {return x;}
    public int getY() {return y;}
    public void setX(int x){this.x=x;}
    public void setY(int y){this.y=y;}
    public int getAdjacentmines(){return adjacentmines;}
    public boolean isDiscovered()
    {
        return discovered;
    }
    public boolean isFlagged()
    {
        return flagged;
    }
    public void setDiscovered(boolean discovered)
    {
        this.discovered = discovered;
    }
    public void setFlagged(boolean flagged)
    {
        this.flagged = flagged;
    }
    public void setType(int i) {this.type = i;}
    public void setAdjacentmines(int i)
    {
        this.adjacentmines = i;
    }



    //(!adjacents.get(i).isDiscovered()&&adjacents.get(i).getType()!=3&&this.getType()==0)

    public void flag()
    {
        Main.buttons[this.x][this.y].setText("F");
        Main.buttons[this.x][this.y].setForeground(Color.black);
        Main.board[this.x][this.y].setFlagged(true);
    }
    public void unflag()
    {
        Main.buttons[this.x][this.y].setText(" ");
        Main.buttons[this.x][this.y].setForeground(Color.black);
    }

    public void revealBlanks()
    {
        this.reveal();
        ArrayList<Cell> adjacents = this.getAdjacent();
        for (int i=0;i<adjacents.size();i++)
        {
            if((!adjacents.get(i).isDiscovered()&&adjacents.get(i).getType()!=1&&this.getType()==0)&&adjacents.get(i).getType()!=3)
            {
                adjacents.get(i).reveal();
                adjacents.get(i).revealBlanks();
            }
        }
    }

    public void reveal()
    {
        if(!this.discovered)
        {
            if(this.adjfakemine)
            {
                this.setDiscovered(true);
                Main.buttons[this.x][this.y].setText(String.valueOf(this.adjacentmines));
                Main.buttons[this.x][this.y].setBackground(Color.white);
                Main.buttons[this.x][this.y].setForeground(Color.red);
            }
            else
            {
                this.setDiscovered(true);
                Main.buttons[this.x][this.y].setText(String.valueOf(this.adjacentmines));
                Main.buttons[this.x][this.y].setBackground(Color.white);
            }

        }
    }


    public ArrayList<Cell> getAdjacent()
    {
        ArrayList<Cell> adjacents = new ArrayList<>();
        int [] pos = new int[]{
                -1,-1,
                -1,0,
                -1,1,
                0,-1,
                0,1,
                1,-1,
                1,0,
                1,1 };

        for (int i =0;i<pos.length;i++)
        {
            int adjx=this.getX()+ pos[i];
            int adjy=this.getY()+ pos[++i];
            if(adjx>-1&&adjx<Main.BOARDSIZE&&adjy>-1&&adjy< Main.BOARDSIZE)
            {
                adjacents.add(Main.board[adjx][adjy]);
            }
        }
        return adjacents;
    }

}
