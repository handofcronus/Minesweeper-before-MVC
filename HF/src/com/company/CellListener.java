package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class CellListener implements MouseListener
{

    //int x=0 , y=0;
    //        for (int i=0;i<Main.BOARDSIZE;i++)
    //        {
    //            for(int j=0; j<Main.BOARDSIZE;j++)
    //            {
    //                if(Main.buttons[i][j]==e.getSource())
    //                {
    //                    x=i;
    //                    y=j;
    //                }
    //            }
    //        }

    public int getButtonx(MouseEvent e)
    {
        int x=0;
        for (int i=0;i<Main.BOARDSIZE;i++)
        {
            for(int j=0; j<Main.BOARDSIZE;j++)
            {
                if(Main.buttons[i][j]==e.getSource())
                {
                    x=i;
                    break;
                }
            }
        }
        return x;
    }
    public int getButtony(MouseEvent e)
    {
        int y=0;
        for (int i=0;i<Main.BOARDSIZE;i++)
        {
            for(int j=0; j<Main.BOARDSIZE;j++)
            {
                if(Main.buttons[i][j]==e.getSource())
                {
                    y=j;
                    break;
                }
            }
        }
        return y;
    }
    public void trigger_lose()
    {
        Main.lost=true;
        for (int i=0;i<Main.BOARDSIZE;i++)
        {
            for(int j=0;j<Main.BOARDSIZE;j++)
            {
                if(Main.board[i][j].getType()==1)
                {
                    Main.buttons[i][j].setText("Mine");
                    Main.buttons[i][j].setForeground(Color.red);
                }
            }
        }
    }
    @Override
    public void mouseClicked(MouseEvent e)
    {

        int x=getButtonx(e);
        int y=getButtony(e);
        if(SwingUtilities.isLeftMouseButton(e))
        {
            if(!Main.lost)
            {
                if(Main.board[x][y].getType()==1)
                {
                    trigger_lose();
                }
                else if(Main.board[x][y].getType()==0)
                {
                    Main.board[x][y].revealBlanks();
                }
                else if(Main.board[x][y].getType()==2)
                {
                    Main.board[x][y].reveal();
                }
                else if(Main.board[x][y].getType()==3)
                {
                    Main.buttons[x][y].setBackground(Color.white);
                    Main.buttons[x][y].setForeground(Color.blue);
                    Main.buttons[x][y].setText("X");

                }
            }
            else
            {
                System.out.println("Game Lost");
            }
        }
        else if (SwingUtilities.isRightMouseButton(e)&&!Main.board[x][y].isDiscovered())
        {
            if(Main.board[x][y].isFlagged())
            {
                Main.board[x][y].unflag();
            }
            else
            {
                Main.board[x][y].flag();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}
