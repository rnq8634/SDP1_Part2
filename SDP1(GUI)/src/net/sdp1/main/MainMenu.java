package net.sdp1.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainMenu extends JFrame {
    
    public MainMenu() {
        initUI();
    }
    
    private void initUI() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new GridLayout(3, 1));
        
        // button for new game
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
                closeMenu();
            }
        });
        panel.add(newGameButton);
        
        // button for load game
        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // load a saved game
                GameMechanic.loadGame();
                closeMenu();
            }
        });
        panel.add(loadGameButton);
        
        // button to leave the game
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.exit(0);
           }
        });
        panel.add(exitButton);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private void startGame() {
        boolean setName = false;
        String name;
        boolean loadGame = false;
        
        File savedData = new File("saveData_game.ser");
        if(savedData.exists()) {
            JOptionPane.showMessageDialog(this, "Saved game detected. Choose an option.", "Game Menu", JOptionPane.INFORMATION_MESSAGE);
            int choice = JOptionPane.showOptionDialog(this, "Do you want to start a new game or load a saved game?", "New Game or Load Game", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"New Game", "Load Game", "Exit Game"}, "New Game");
            if (choice == JOptionPane.NO_OPTION) {
                loadGame = true;
            } else if (choice == JOptionPane.CANCEL_OPTION) {
                System.exit(0);
            }
        }
        
        if (!loadGame) {
            do {
                name = JOptionPane.showInputDialog(this, "Warrior, what is your name?", "Enter your name.", JOptionPane.PLAIN_MESSAGE);
                int input = JOptionPane.showConfirmDialog(this, "Your name is " + name + ". Is that correct?", "Confirm Name", JOptionPane.YES_NO_OPTION);
                if (input == JOptionPane.YES_OPTION)
                    setName = true;
            } while (!setName);
            
            JOptionPane.showMessageDialog(this, "Welcome " + name + "! Prepare to experience the world of the murim!", "Game Start", JOptionPane.INFORMATION_MESSAGE);
            
            Player.player = new Player(name);
            
            GameMechanic.isRunning = true;
            GameMechanic.gameLoop();
            
            GameScreen gameScreen = new GameScreen();
            gameScreen.setVisible(true);
        } else {
            GameMechanic.loadGame();
        }
    }
    
    private void closeMenu() {
        setVisible(false);
        dispose();
    }
    
}
