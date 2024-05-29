package net.sdp1.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//class for the gamescreen, this is where the game will be displayed.
public class GameScreen {
    
    JFrame screen;
    Container con;
    JPanel titleNamePanel, newGameButtonPanel, loadGameButtonPanel, exitGameButtonPanel;
    JLabel titleNameLabel;
    //title settings
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
    // button for starting the game
    JButton newGameButton, loadGameButton, exitGameButton;
    
    public GameScreen() {
        // makes sure of the GUI creation on the event dispatch thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gameGUI();
            }
        });
    }
    
    private void gameGUI() {
        
        screen = new JFrame();
        //window size
        screen.setSize(800, 600);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //game background color
        screen.getContentPane().setBackground(Color.BLACK);
        //custom window
        screen.setLayout(null);

        con = screen.getContentPane();
        
        //title border, where the font is placed
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 65);
        titleNamePanel.setBackground(Color.BLACK);
        
        //title font, for editing the font color and text
        titleNameLabel = new JLabel("RPG MURIM SIMULATOR");
        titleNameLabel.setForeground(Color.WHITE);
        titleNameLabel.setFont(titleFont);
        
        //start button design
        newGameButtonPanel = new JPanel();
        newGameButtonPanel.setBounds(300, 400, 200, 31);
        newGameButtonPanel.setBackground(Color.BLACK);
        
        //start button
        newGameButton = new JButton("NEW GAME");
        newGameButton.setBackground(Color.BLACK);
        newGameButton.setForeground(Color.WHITE);
        
        //load button design
        loadGameButtonPanel = new JPanel();
        loadGameButtonPanel.setBounds(300, 428, 200, 31);
        loadGameButtonPanel.setBackground(Color.BLACK);
        
        //load button
        loadGameButton = new JButton("LOAD GAME");
        loadGameButton.setBackground(Color.BLACK);
        loadGameButton.setForeground(Color.WHITE);
        
        //exit button design
        exitGameButtonPanel = new JPanel();
        exitGameButtonPanel.setBounds(300, 455, 200, 31);
        exitGameButtonPanel.setBackground(Color.BLACK);
        
        //exit button
        exitGameButton = new JButton("EXIT GAME");
        exitGameButton.setBackground(Color.BLACK);
        exitGameButton.setForeground(Color.WHITE);
        
        titleNamePanel.add(titleNameLabel);
        newGameButtonPanel.add(newGameButton);
        loadGameButtonPanel.add(loadGameButton);
        exitGameButtonPanel.add(exitGameButton);
        
        con.add(titleNamePanel);
        con.add(newGameButtonPanel);
        con.add(loadGameButtonPanel);
        con.add(exitGameButtonPanel);
        
        // sets the application to be visible
        screen.setVisible(true);
    }
    
}
