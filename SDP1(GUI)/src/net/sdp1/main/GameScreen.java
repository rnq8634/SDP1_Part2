package net.sdp1.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//class for the gamescreen, this is where the game will be displayed.
public class GameScreen {
    
    JFrame screen;
    Container con;
    JPanel titleNamePanel, newGameButtonPanel;
    JLabel titleNameLabel;
    //title settings
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
    // button for starting the game
    JButton newGameButton;
    
    public GameScreen() {
        
        screen = new JFrame();
        //window size
        screen.setSize(800, 600);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //game background color
        screen.getContentPane().setBackground(Color.BLACK);
        //custom window
        screen.setLayout(null);
        //makes the application visible
        screen.setVisible(true);
        con = screen.getContentPane();
        
        //title border, where the font is placed
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 65);
        titleNamePanel.setBackground(Color.WHITE);
        
        //title font, for editing the font color and text
        titleNameLabel = new JLabel("RPG MURIM SIMULATOR");
        titleNameLabel.setForeground(Color.BLACK);
        titleNameLabel.setFont(titleFont);
        
        //start button design
        newGameButtonPanel = new JPanel();
        newGameButtonPanel.setBounds(300, 400, 200, 100);
        newGameButtonPanel.setBackground(Color.BLUE);
        
        //start button
        newGameButton = new JButton("New Game");
        newGameButton.setBackground(Color.BLACK);
        newGameButton.setForeground(Color.WHITE);
        
        titleNamePanel.add(titleNameLabel);
        newGameButtonPanel.add(newGameButton);
        
        con.add(titleNamePanel);
        con.add(newGameButtonPanel);
        
    }
    
}
