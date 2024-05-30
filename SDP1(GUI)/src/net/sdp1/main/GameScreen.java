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
    
    private JFrame screen;
    private JPanel gameTextPanel, userInputPanel, actionMenuPanel, playerPanel;
    private JPanel titleNamePanel, newGameButtonPanel, loadGameButtonPanel, exitGameButtonPanel;
    private JLabel titleNameLabel;
    private JButton newGameButton, loadGameButton, exitGameButton, confirmButton;
    private JButton action1, action2, action3, action4;
    private JTextArea gameTextArea;
    private JTextField nameField;
    
    private final Font titleFont = new Font("Times New Roman", Font.PLAIN, 50);
    private final Font gameFont = new Font("Times New Roman", Font.PLAIN, 20);
    
    public GameScreen() {
        SwingUtilities.invokeLater(this::createAndShowApp);
    }
    
    private void createAndShowApp() {
        screen = new JFrame("Game");
        screen.setSize(800, 600);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.getContentPane().setBackground(Color.BLACK);
        screen.setLayout(null);
        
        titleScreen();
        screen.setVisible(true);
    }
    
    //where the title screen is displayed
    private void titleScreen() {
        titleNamePanel = createPanel(100, 100, 600, 65, Color.BLACK);
        titleNameLabel = createLabel("RPG MURIM SIMULATOR", titleFont, Color.WHITE);
        titleNamePanel.add(titleNameLabel);
        
        newGameButtonPanel = createButtonPanel(300, 400, "NEW GAME", e -> startGame());
        loadGameButtonPanel = createButtonPanel(300, 428, "LOAD GAME", e -> GameMechanic.loadGame());
        exitGameButtonPanel = createButtonPanel(300, 455, "EXIT GAME", e -> System.exit(0));
        
        screen.add(titleNamePanel);
        screen.add(newGameButtonPanel);
        screen.add(loadGameButtonPanel);
        screen.add(exitGameButtonPanel);
    }
    
    private JPanel createPanel(int x, int y, int width, int height, Color bgColor) {
        JPanel panel = new JPanel();
        panel.setBounds(x, y, width, height);
        panel.setBackground(bgColor);
        return panel;
    }
    
    private JLabel createLabel(String text, Font font, Color fgColor) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(fgColor);
        return label;
    }
    
    private JPanel createButtonPanel(int x, int y, String buttonText, ActionListener action) {
        JPanel panel = createPanel(x, y, 200, 31, Color.BLACK);
        JButton button = createButton(buttonText, action);
        panel.add(button);
        return panel;
    }
    
    private JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.addActionListener(action);
        return button;
    }
    
    // clears screen just like the clear console method
    private void clearScreen() {
        screen.getContentPane().removeAll();
        screen.revalidate();
        screen.repaint();
    }
    
    private JTextArea createTextArea(String text, Font font, Color fgColor) {
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(font);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(fgColor);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }
    
    // start of game(where user inputs name)
    public void startGame() {
        clearScreen();
        userInputPanel = createPanel(250, 250, 300, 58, Color.BLACK);
        nameField = new JTextField(20);
        userInputPanel.add(nameField);
        
        gameTextPanel = createPanel(100, 100, 600, 250, Color.BLACK);
        gameTextArea = createTextArea("Warrrior, what is your name?", gameFont, Color.WHITE);
        gameTextPanel.add(gameTextArea);
        
        confirmButton = createButton("Confirm", e -> confirmName());
        
        userInputPanel.add(confirmButton);
        screen.add(userInputPanel);
        screen.add(gameTextPanel);
        
        screen.revalidate();
        screen.repaint();
    }
    
    //action for confirming player name
    public void confirmName() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(screen, "You didn't input a name!");
        } else {
            JOptionPane.showMessageDialog(screen, "Your name is " + name + ".");
            gameGUI();
        }
    }
    
    //where the gameplay will be displayed
    public void gameGUI() {
        clearScreen();
        
        gameTextPanel = createPanel(100, 100, 600, 250, Color.BLUE);
        gameTextArea = createTextArea("", gameFont, Color.WHITE);
        gameTextPanel.add(gameTextArea);
        
        actionMenuPanel = createPanel(250, 350, 300, 150, Color.BLACK);
        actionMenuPanel.setLayout(new GridLayout(4, 1));
        
        action1 = createButton("Action 1", e -> performAction(1));
        action2 = createButton("Action 2", e -> performAction(2));
        action3 = createButton("Action 3", e -> performAction(3));
        action4 = createButton("Action 4", e -> performAction(4));
        
        actionMenuPanel.add(action1);
        actionMenuPanel.add(action2);
        actionMenuPanel.add(action3);
        actionMenuPanel.add(action4);
        
        playerPanel = createPanel(100, 15, 600, 50, Color.BLUE);
        playerPanel.setLayout(new GridLayout(1, 4));
        
        screen.add(gameTextPanel);
        screen.add(actionMenuPanel);
        screen.add(playerPanel);
        
        screen.revalidate();
        screen.repaint();
    }
    
    // method for button action/choices
    private void performAction(int actionNumber) {
        
    }
    
}
