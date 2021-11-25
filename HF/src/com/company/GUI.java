package com.company;

import javax.swing.*;
import java.awt.*;
public class GUI
{

    private JFrame frame;

    public GUI()
    {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(Main.WIDHT,Main.HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Minesweeper");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        ImageIcon logo = new ImageIcon("logo2.jpg");
        frame.setIconImage(logo.getImage());
        frame.setVisible(true);
        //




        JPanel board = new Board(new GridBagLayout(),Main.scorelabel);
        frame.add(board);


        frame.pack();
    }
}
