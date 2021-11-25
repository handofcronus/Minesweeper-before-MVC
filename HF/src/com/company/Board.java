package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel
{
    private static int minecount;
    private static int flagcount;
    private GridBagLayout gbl;
    Time time = new Time();
    private JLabel timelabel= new JLabel("Time:"+time.getTime());
    private JLabel scorelabel;
    public static int getMinecount()
    {
        return minecount;
    }

    public static int getFlagcount()
    {
        return flagcount;
    }

    public static void setFlagcount(int flagcount)
    {
        Board.flagcount = flagcount;
    }

    public Board(GridBagLayout gridBagLayout, JLabel scorelabel)
    {

        super(gridBagLayout);
        gbl=gridBagLayout;

        this.scorelabel=scorelabel;

        createCells();
        minecount=0;
        addCells();
        calculatemines();


    }
    private void createCells()
    {
        for (int i=0;i<Main.BOARDSIZE;i++)
        {
            for (int j=0;j<Main.BOARDSIZE;j++)
            {
                if(Math.random()<0.2)
                {
                    if(Math.random()<0.3)
                    {
                        JButton tmpbutton = new JButton();
                        tmpbutton.setBackground(Color.blue);
                        Main.buttons[i][j]=tmpbutton;
                        Cell temp = new Cell(3,i,j,false,false);
                        minecount++;
                        Main.board[i][j] = temp;
                        Main.buttons[i][j].addMouseListener(new CellListener());
                    }
                    else
                    {
                        JButton tmpbutton = new JButton();
                        tmpbutton.setBackground(Color.black);
                        Main.buttons[i][j]=tmpbutton;
                        Cell temp = new Cell(1,i,j,false,false);
                        minecount++;
                        Main.board[i][j] = temp;
                        Main.buttons[i][j].addMouseListener(new CellListener());
                    }
                }
                else
                {
                    JButton tmpbutton = new JButton();
                    tmpbutton.setBackground(Color.gray);
                    Cell temp = new Cell(0,i,j,false,false);
                    Main.buttons[i][j]=tmpbutton;

                    Main.board[i][j] = temp;
                    Main.buttons[i][j].addMouseListener(new CellListener());
                }
            }

        }

    }
    public void refreshtimelabel()
    {
        time.incrementTime();
        timelabel.setText("Time:"+time.getTime());
    }
    private void addCells()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel infopanel = new JPanel();
        infopanel.setBackground(Color.yellow);
        infopanel.setLayout(new BorderLayout());
        infopanel.add(timelabel,BorderLayout.WEST);
        infopanel.add(scorelabel,BorderLayout.EAST);
        gbc.weightx=1;
        gbc.weighty=1;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridheight=2;
        gbc.gridwidth=15;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        gbl.setConstraints(infopanel,gbc);
        this.add(infopanel,gbc);
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.fill = GridBagConstraints.BOTH;
        for (int i=0;i<Main.BOARDSIZE;i++)
        {
            gbc.gridy=i+2;
            for (int j=0;j<Main.BOARDSIZE;j++)
            {
                gbc.gridx=j;
                gbl.setConstraints(Main.buttons[i][j],gbc);
                this.add(Main.buttons[i][j]);
            }
        }
    }

    private void calculatemines()
    {
        int minecount=0;
        int fakemines=0;
        for (int i=0;i<Main.BOARDSIZE;i++)
        {
            for (int j=0;j<Main.BOARDSIZE;j++)
            {
                if(Main.board[i][j].getType()==1||Main.board[i][j].getType()==3)
                {
                    //skipp
                }
                else
                {
                    ArrayList<Cell> neighbours = Main.board[i][j].getAdjacent();
                    for(int k=0;k<neighbours.size();k++)
                    {
                        if(neighbours.get(k).getType()==1)
                        {
                            minecount++;
                        }
                        else if(neighbours.get(k).getType()==3)
                        {
                            fakemines++;
                            minecount++;
                        }
                    }
                    if(minecount>0)
                    {
                        Main.board[i][j].setType(2);
                        Main.board[i][j].setAdjacentmines(minecount);
                        minecount=0;
                    }
                    if(fakemines>0)
                    {
                        Main.board[i][j].setAdjfakemine(true);
                        fakemines=0;
                    }
                }

            }
        }

    }

}
