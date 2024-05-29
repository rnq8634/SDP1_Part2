package net.sdp1.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

//GUI related stuff
public class GameScreen {
    
    JFrame screen;
    //in game text panel
    JPanel gameTextPanel, userInputPanel;
    //title screen panel
    JPanel titleNamePanel, newGameButtonPanel, loadGameButtonPanel, exitGameButtonPanel;
    // action menu panel
    JPanel actionMenuPanel;
    
    JTextField nameField;
    
    JLabel titleNameLabel;
    //title settings
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
    Font gameFont = new Font("Times New Roman", Font.PLAIN, 20);
    // button for starting the game
    JButton newGameButton, loadGameButton, exitGameButton;
    //choices
    JButton action1, action2, action3, action4;
    
    JButton confirmButton;
    JTextArea gameTextArea;
    
    titleHandler tHandler;
    loadHandler lHandler;
    nameHandler nHandler;
    
    public GameScreen() {
        
        //initialize the handlers
        tHandler = new titleHandler();
        lHandler = new loadHandler();
        nHandler = new nameHandler();
        
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
    
    //action for selecting Load Game at title screen
    public class loadHandler implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            GameMechanic.loadGame();
        }
        
    }
    
    //action for selecting New Game at title screen
    public class titleHandler implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            startGame();
        }
    }
    
    public void startGame() {
        
        titleNamePanel.setVisible(false);
        newGameButtonPanel.setVisible(false);
        loadGameButtonPanel.setVisible(false);
        exitGameButtonPanel.setVisible(false);
        
        //panel for user input
        userInputPanel = new JPanel();
        userInputPanel.setBounds(250, 250, 300, 58);
        userInputPanel.setBackground(Color.BLACK);
        screen.add(userInputPanel);
        
        //text field for user input
        nameField = new JTextField(20);
        userInputPanel.add(nameField);
        
        //game text design
        gameTextPanel = new JPanel();
        gameTextPanel.setBounds(100, 100, 600, 250);
        gameTextPanel.setBackground(Color.BLACK);
        screen.add(gameTextPanel);
        
        //game text
        gameTextArea = new JTextArea("Warrior, what is your name?");
        gameTextArea.setFont(gameFont);
        gameTextArea.setBounds(100, 100, 600, 250);
        //background
        gameTextArea.setBackground(Color.BLACK);
        //sets the text color
        gameTextArea.setForeground(Color.WHITE);
        //prevents long lines
        gameTextArea.setLineWrap(true);
        gameTextPanel.add(gameTextArea);
        
        confirmButton = new JButton("Confirm");
        confirmButton.setBackground(Color.BLACK);
        confirmButton.setForeground(Color.WHITE);
        userInputPanel.add(confirmButton);
        
        confirmButton.addActionListener(nHandler);
        
    }
    
    //action for confirming player name
    public class nameHandler implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "You didn't input a name!");
            } else {
                JOptionPane.showMessageDialog(null, "Your name is " + name + ".");
                gameGUI();
            }
        }
        
    }
    
    //where the gameplay will be displayed
    public void gameGUI() {
        
        //makes the previous screen disappear
        gameTextPanel.setVisible(false);
        confirmButton.setVisible(false);
        gameTextArea.setVisible(false);
        userInputPanel.setVisible(false);
        
        
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
        
        //action menu area
        actionMenuPanel = new JPanel();
        actionMenuPanel.setBounds(250, 350, 300, 150);
        actionMenuPanel.setBackground(Color.BLACK);
        actionMenuPanel.setLayout(new GridLayout(4, 1));
        screen.add(actionMenuPanel);
        
        //actions of the player/choices
        action1 = new JButton("Action 1");
        action1.setBackground(Color.BLACK);
        action1.setForeground(Color.WHITE);
        actionMenuPanel.add(action1);
        
        action2 = new JButton("Action 2");
        action2.setBackground(Color.BLACK);
        action2.setForeground(Color.WHITE);
        actionMenuPanel.add(action2);
        
        action3 = new JButton("Action 3");
        action3.setBackground(Color.BLACK);
        action3.setForeground(Color.WHITE);
        actionMenuPanel.add(action3);
        
        action4 = new JButton("Action 4");
        action4.setBackground(Color.BLACK);
        action4.setForeground(Color.WHITE);
        actionMenuPanel.add(action4);
        
    }
    
}
