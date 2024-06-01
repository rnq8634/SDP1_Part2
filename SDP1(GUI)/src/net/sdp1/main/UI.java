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
    private JFrame screen;
    private JPanel gameTextPanel, userInputPanel, actionMenuPanel, playerPanel, saveButtonPanel;
    private JPanel titleNamePanel, newGameButtonPanel, loadGameButtonPanel, exitGameButtonPanel, menuButtonPanel;
    private JLabel titleNameLabel, hpLabel, martialLabel, martialBodyLabel, playerNameLabel;
    private JButton confirmButton, menuButton, saveButton;
    private JButton action1, action2, action3, action4;
    private JTextArea gameTextArea;
    private JTextField nameField;
    
    private Character character;
    private Player player;
    
    private final Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
    private final Font gameFont = new Font("Times New Roman", Font.PLAIN, 20);
    
    public UI() 
    {
        SwingUtilities.invokeLater(this::createAndShowUI);
    }
    
    
    private void createAndShowUI() 
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
    private void titleScreen() 
    {
        clearScreen();
        
        // game title
        titleNamePanel = createPanel(100, 100, 600, 65, Color.BLACK);
        titleNameLabel = createLabel("RPG MURIM SIMULATOR", titleFont, Color.WHITE);
        titleNamePanel.add(titleNameLabel);
        
        //buttons
        newGameButtonPanel = createButtonPanel(300, 400, "NEW GAME", e -> startGame());
        loadGameButtonPanel = createButtonPanel(300, 428, "LOAD GAME", e -> GameMechanic.loadGame());
        exitGameButtonPanel = createButtonPanel(300, 455, "EXIT GAME", e -> System.exit(0));
        
        screen.add(titleNamePanel);
        screen.add(newGameButtonPanel);
        screen.add(loadGameButtonPanel);
        screen.add(exitGameButtonPanel);
        
        screen.revalidate();
        screen.repaint();
    }
    
    // helper method
    private JPanel createPanel(int x, int y, int width, int height, Color bgColor) 
    {
        JPanel panel = new JPanel();
        panel.setBounds(x, y, width, height);
        panel.setBackground(bgColor);
        return panel;
    }
    
    // helper method
    private JLabel createLabel(String text, Font font, Color fgColor) 
    {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(fgColor);
        return label;
    }
    
    // helper method
    private JPanel createButtonPanel(int x, int y, String buttonText, ActionListener action) 
    {
        JPanel panel = createPanel(x, y, 120, 31, Color.BLACK);
        JButton button = createButton(buttonText, action);
        panel.add(button);
        return panel;
    }
    
    // helper method
    private JButton createButton(String text, ActionListener action) 
    {
        JButton button = new JButton(text);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.addActionListener(action);
        return button;
    }
    
    // helper method
    private void clearScreen() 
    {
        screen.getContentPane().removeAll();
        screen.revalidate();
        screen.repaint();
    }
    
    // helper method
    private JTextArea createTextArea(String text, Font font, Color fgColor, boolean lineWrap) 
    {
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(font);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(fgColor);
        textArea.setLineWrap(lineWrap);
        textArea.setWrapStyleWord(lineWrap);
        return textArea;
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
        confirmButton = createButton("Confirm", e -> confirmName());
        
        userInputPanel.add(confirmButton);
        screen.add(userInputPanel);
        screen.add(gameTextPanel);
        
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
        }
    }
    
    //where the gameplay will be displayed
    public void gameGUI() 
    {
        clearScreen();
        
        //main text area
        gameTextPanel = createPanel(100, 100, 600, 250, Color.BLUE);
        gameTextArea = createTextArea("Hello World!", gameFont, Color.WHITE, true);
        gameTextPanel.add(gameTextArea);
        
        actionMenuPanel = createPanel(250, 350, 300, 150, Color.BLACK);
        actionMenuPanel.setLayout(new GridLayout(4, 1));
        
        //  displays action buttons
        action1 = createButton("Action 1", e -> performAction(1));
        action2 = createButton("Action 2", e -> performAction(2));
        action3 = createButton("Action 3", e -> performAction(3));
        action4 = createButton("Action 4", e -> performAction(4));
        
        // action/choices
        actionMenuPanel.add(action1);
        actionMenuPanel.add(action2);
        actionMenuPanel.add(action3);
        actionMenuPanel.add(action4);
        
        //save button
        saveButton = createButton("Save Game", e -> GameMechanic.saveGame());
        saveButtonPanel = createPanel(570, 350, 100, 31, Color.BLACK);
        saveButtonPanel.add(saveButton);
        
        //menu button goes back to the main menu
        menuButton = createButton("Back to title screen", e -> titleScreen());
        menuButtonPanel = createPanel(325, 500, 150, 32, Color.BLACK);
        menuButtonPanel.add(menuButton);
        
        //qi display
        
        //player panel, where player related stuff is displayed
        playerPanel = createPanel(100, 15, 600, 50, Color.BLACK);
        playerPanel.setLayout(new GridLayout(2, 2));
        
        //name display
        playerNameLabel = createLabel("Name: " + player.getName(), gameFont, Color.WHITE);
        playerPanel.add(playerNameLabel);
        
        //converts String[] to string
        String martialBodyText = (player.getMartialBody() != null && player.getMartialBody().length > 0)
                                ? player.getMartialBody()[0]
                                : "None";
        
        //displayer for martial body
        martialBodyLabel = createLabel("Martial Body: " + martialBodyText, gameFont, Color.WHITE);
        playerPanel.add(martialBodyLabel);
        
        //health display
        hpLabel = createLabel("HP: " + player.getHealth(), gameFont, Color.WHITE);
        playerPanel.add(hpLabel);
        
        //converts String[] to string
        String martialArtText = (player.getMartialSkill() != null && player.getMartialSkill().length > 0)
                                ? player.getMartialSkill()[0]
                                : "None";
        
        //martial art display
        martialLabel = createLabel("Martial Art: " + martialArtText, gameFont, Color.WHITE);
        playerPanel.add(martialLabel);
        
        screen.add(gameTextPanel);
        screen.add(actionMenuPanel);
        screen.add(playerPanel);
        screen.add(menuButtonPanel);
        screen.add(saveButtonPanel);
        
        screen.revalidate();
        screen.repaint();
    }
    
    // method for button action/choices
    private void performAction(int actionNumber) 
    {
        
    }
    
}
