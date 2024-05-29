package net.sdp1.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

//GUI related stuff
public class GameScreen {
    
    JFrame screen;
    //in game text panel
    JPanel gameTextPanel;
    //title screen panel
    JPanel titleNamePanel, newGameButtonPanel, loadGameButtonPanel, exitGameButtonPanel;
    JLabel titleNameLabel;
    //title settings
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
    // button for starting the game
    JButton newGameButton, loadGameButton, exitGameButton;
    JTextArea gameTextArea;
    
    titleHandler tHandler;
    loadHandler lHandler;
    
    public GameScreen() {
        
        //initialize the handlers
        tHandler = new titleHandler();
        lHandler = new loadHandler();
        
        // makes sure of the GUI creation on the event dispatch thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                titleScreen();
            }
        });
    }
    
    //where the title screen is displayed
    private void titleScreen() {
        
        screen = new JFrame();
        //window size
        screen.setTitle("Game");
        screen.setSize(800, 600);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //game background color
        screen.getContentPane().setBackground(Color.BLACK);
        //custom window
        screen.setLayout(null);
        
        //title border, where the font is placed
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 65);
        titleNamePanel.setBackground(Color.BLACK);
        
        //title font, for editing the font color and text
        titleNameLabel = new JLabel("RPG MURIM SIMULATOR");
        titleNameLabel.setForeground(Color.WHITE);
        titleNameLabel.setFont(titleFont);
        titleNamePanel.add(titleNameLabel);
        
        //new game button design
        newGameButtonPanel = new JPanel();
        newGameButtonPanel.setBounds(300, 400, 200, 31);
        newGameButtonPanel.setBackground(Color.BLACK);
        
        //new game button
        newGameButton = new JButton("NEW GAME");
        newGameButton.setBackground(Color.BLACK);
        newGameButton.setForeground(Color.WHITE);
        newGameButtonPanel.add(newGameButton);
        
        //function for new game
        newGameButton.addActionListener(tHandler);
        
        //load button design
        loadGameButtonPanel = new JPanel();
        loadGameButtonPanel.setBounds(300, 428, 200, 31);
        loadGameButtonPanel.setBackground(Color.BLACK);
        
        //load button
        loadGameButton = new JButton("LOAD GAME");
        loadGameButton.setBackground(Color.BLACK);
        loadGameButton.setForeground(Color.WHITE);
        loadGameButtonPanel.add(loadGameButton);
        
        //function for load game
        loadGameButton.addActionListener(lHandler);
        
        //exit button design
        exitGameButtonPanel = new JPanel();
        exitGameButtonPanel.setBounds(300, 455, 200, 31);
        exitGameButtonPanel.setBackground(Color.BLACK);
        
        //exit button
        exitGameButton = new JButton("EXIT GAME");
        exitGameButton.setBackground(Color.BLACK);
        exitGameButton.setForeground(Color.WHITE);
        exitGameButtonPanel.add(exitGameButton);
        
        //function for the exit button to close the game
        exitGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        //adds it to the screen/display(so you can see it)
        screen.add(titleNamePanel);
        screen.add(newGameButtonPanel);
        screen.add(loadGameButtonPanel);
        screen.add(exitGameButtonPanel);
        // sets the application to be visible
        screen.setVisible(true);
    }
    
    //where the gameplay will be displayed
    public void gameGUI() {
        
        //screen.getContentPane().removeAll();
        //screen.repaint();
        
        //game text design
        gameTextPanel = new JPanel();
        gameTextPanel.setBounds(100, 100, 600, 250);
        gameTextPanel.setBackground(Color.BLUE);
        screen.add(gameTextPanel);
        
        //game text
        gameTextArea = new JTextArea();
        gameTextArea.setBounds(100, 100, 600, 250);
        //background
        gameTextArea.setBackground(Color.BLACK);
        //sets the text color
        gameTextArea.setForeground(Color.WHITE);
        //prevents long lines
        gameTextArea.setLineWrap(true);
        gameTextPanel.add(gameTextArea);
        
        //screen.revalidate();
        //screen.repaint();
        
    }
    
    public class loadHandler implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            GameMechanic.loadGame();
        }
        
    }
    
    public class titleHandler implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            gameGUI();
        }
    }
    
}
