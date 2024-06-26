package net.sdp1.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
public class UI 
{
    public JFrame screen;
    public JPanel gameTextPanel, userInputPanel, actionMenuPanel, playerPanel, saveButtonPanel, playerWindowPanel;
    public JPanel titleNamePanel, newGameButtonPanel, loadGameButtonPanel, exitGameButtonPanel, menuButtonPanel;
    public JLabel titleNameLabel, hpLabel, martialLabel, martialBodyLabel, playerNameLabel;
    public JButton confirmButton, menuButton, saveButton, playerWindowButton;
    public JButton action1, action2, action3, action4;
    public JTextArea gameTextArea;
    public JTextField nameField;
    
    //class reference
    public GameLocation gameLocation;
    public Player player;
    public PlayerWindow playerWindow;
    public GameMechanic gameMechanic;
    public Combat combat;
    public PlayerRecover playerRecover;
    public PlayerTrait playerTrait;
    public Random random;
    public PlayerDeath playerDeath;
    
    public final Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
    public final Font gameFont = new Font("Times New Roman", Font.PLAIN, 20);
    
    public UI() 
    {
        SwingUtilities.invokeLater(this::createAndShowUI);
        playerWindow = new PlayerWindow(this);
        gameMechanic = new GameMechanic(this);
        gameLocation = new GameLocation(this);
        combat = new Combat(this, playerDeath);
        playerRecover = new PlayerRecover(this);
        playerTrait = new PlayerTrait(this);
        random = new Random(this);
    }
    
    //creates the window
    public void createAndShowUI() 
    {
        screen = new JFrame("Game");
        screen.setSize(800, 600);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.getContentPane().setBackground(Color.BLACK);
        screen.setLayout(null);
        
        titleScreen();
        screen.setVisible(true);
    }
    
    //where the title screen is displayed
    public void titleScreen() 
    {
        clearScreen();
        
        // game title
        titleNamePanel = createPanel(100, 100, 600, 65, Color.BLACK);
        titleNameLabel = createLabel("RPG MURIM SIMULATOR", titleFont, Color.WHITE);
        titleNamePanel.add(titleNameLabel);
        
        //buttons
        newGameButtonPanel = createButtonPanel(300, 400, "[NEW GAME]", e -> startGame());
        loadGameButtonPanel = createButtonPanel(300, 428, "[LOAD GAME]", e -> gameMechanic.loadGame());
        exitGameButtonPanel = createButtonPanel(300, 455, "[EXIT GAME]", e -> System.exit(0));
        
        screen.add(titleNamePanel);
        screen.add(newGameButtonPanel);
        screen.add(loadGameButtonPanel);
        screen.add(exitGameButtonPanel);
        
        // screen refresh
        screen.revalidate();
        screen.repaint();
    }
    
    // start of game(where user inputs name)
    public void startGame() 
    {
        clearScreen();
        
        userInputPanel = createPanel(250, 250, 300, 58, Color.BLACK);
        nameField = new JTextField(20);
        userInputPanel.add(nameField);
        
        // main text area 
        gameTextPanel = createPanel(100, 100, 600, 250, Color.BLACK);
        gameTextArea = createTextArea("Warrrior, what is your name?", gameFont, Color.WHITE, false);
        gameTextArea.setWrapStyleWord(true);
        gameTextPanel.add(gameTextArea);
        
        //confirm button
        confirmButton = createButton("[Confirm]", e -> confirmName());
        userInputPanel.add(confirmButton);
        
        screen.add(userInputPanel);
        screen.add(gameTextPanel);
        
        // screen refresh
        screen.revalidate();
        screen.repaint();
    }
    
    //action for confirming player name
    public void confirmName() 
    {
        String name = nameField.getText().trim();
        if (name.isEmpty()) 
        {
            JOptionPane.showMessageDialog(screen, "Hey! you didn't say your name!");
        } else 
        {
            player = new Player(name);
            JOptionPane.showMessageDialog(screen, "So your name is " + name + ".");
            gameGUI();
            playerTrait.martialScroll();// change to martial scroll chooser(player trait)
        }
    }
    
    //where the gameplay will be displayed
    public void gameGUI() 
    {
        clearScreen();
        
        //main text area
        gameTextPanel = createPanel(100, 100, 600, 250, Color.BLACK);
        gameTextArea = createTextArea("Hello World!", gameFont, Color.WHITE, true);
        gameTextArea.setBounds(100, 100, 600, 250);
        gameTextPanel.add(gameTextArea);
        
        actionMenuPanel = createPanel(250, 350, 300, 150, Color.BLACK);
        actionMenuPanel.setLayout(new GridLayout(4, 1));
        
        //  displays action buttons
        action1 = createButton("Action 1", null);
        action2 = createButton("Action 2", null);
        action3 = createButton("Action 3", null);
        action4 = createButton("Action 4", null);
        
        action1.setActionCommand("0");
        action2.setActionCommand("1");
        action3.setActionCommand("2");
        action4.setActionCommand("3");
        
        // action/choices
        actionMenuPanel.add(action1);
        actionMenuPanel.add(action2);
        actionMenuPanel.add(action3);
        actionMenuPanel.add(action4);
        
        //save button
        saveButton = createButton("[Save Game]", e -> gameMechanic.saveGame());
        saveButtonPanel = createPanel(570, 350, 120, 33, Color.BLACK);
        saveButtonPanel.add(saveButton);
        
        //menu button goes back to the main menu
        menuButton = createButton("[Back to title screen]", e -> titleScreen());
        menuButtonPanel = createPanel(325, 500, 170, 33, Color.BLACK);
        menuButtonPanel.add(menuButton);
        
        //player window button(whwre stats can be seen)
        playerWindowButton = createButton("[Player Window]", e -> playerWindow.playerWindowUI());
        playerWindowPanel = createPanel(100, 350, 130, 33, Color.BLACK);
        playerWindowPanel.add(playerWindowButton);
        
        //player panel, where player related stuff is displayed
        playerPanel = createPanel(100, 15, 600, 50, Color.BLACK);
        playerPanel.setLayout(new GridLayout(2, 2));
        
        //name display
        playerNameLabel = createLabel("[Name] " + player.getName(), gameFont, Color.WHITE);
        playerPanel.add(playerNameLabel);
        
        //health display
        hpLabel = createLabel("[HP] " + player.getHealth(), gameFont, Color.WHITE);
        playerPanel.add(hpLabel);
        
        //shows on the screen
        screen.add(playerWindowPanel);
        screen.add(gameTextPanel);
        screen.add(actionMenuPanel);
        screen.add(playerPanel);
        screen.add(menuButtonPanel);
        screen.add(saveButtonPanel);
        
        //calls game location
        switch (GameMechanic.location) 
        {
            case 0:
                gameLocation.firstArea();
                break;
            case 1:
                gameLocation.secondArea();
                break;
            case 2:
                gameLocation.thirdArea();
                break;
            case 3:
                gameLocation.fourthArea();
                break;
            default:
                gameLocation.firstArea();
                break;
        }
        
        // screen refresh
        screen.revalidate();
        screen.repaint();
    }
    
    // helper method for creating panels
    public JPanel createPanel(int x, int y, int width, int height, Color bgColor) 
    {
        JPanel panel = new JPanel();
        panel.setBounds(x, y, width, height);
        panel.setBackground(bgColor);
        return panel;
    }
    
    // helper method for editing buttons (adding text and changing actions)
    public void buttonEditor(JButton button, String text, ActionListener action) 
    {
        button.setText(text);
        for (ActionListener al : button.getActionListeners()) 
        {
            button.removeActionListener(al);
        }
        button.addActionListener(action);
    }
    
    // helper method for creating labels
    public JLabel createLabel(String text, Font font, Color fgColor) 
    {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(fgColor);
        return label;
    }
    
    // helper method for clearing screen
    public void clearScreen() 
    {
        screen.getContentPane().removeAll();
        screen.revalidate();
        screen.repaint();
    }
    
    // helper method for creating buttons and panels in one go
    public JPanel createButtonPanel(int x, int y, String buttonText, ActionListener action) 
    {
        JPanel panel = createPanel(x, y, 120, 31, Color.BLACK);
        JButton button = createButton(buttonText, action);
        panel.add(button);
        return panel;
    }
    
    // helper method for creating buttons
    public JButton createButton(String text, ActionListener action) 
    {
        JButton button = new JButton(text);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.addActionListener(action);
        button.setFocusPainted(false);
        return button;
    }
    
    // helper method for adding text areas
    public JTextArea createTextArea(String text, Font font, Color fgColor, boolean lineWrap) 
    {
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(font);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(fgColor);
        textArea.setLineWrap(lineWrap);
        textArea.setWrapStyleWord(lineWrap);
        return textArea;
    }
}
