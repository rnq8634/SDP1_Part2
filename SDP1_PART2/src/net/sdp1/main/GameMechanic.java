package net.sdp1.main;

import java.awt.Color;
import java.io.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

//GAME MECHANIC
public class GameMechanic 
{   
    public static UI ui;
    
    public static boolean isRunning;
    
    //location
    public static int location = 0, part = 1;
    public static String[] locations = {"Demon Cult Barracks: West Wing", "West Jiang-Hu", "Huangshan Mountain", "Ten Thousand Mountains"};
    
    public JButton backToGameButton;
    public JPanel backToGameButtonPanel, saveGameTextPanel;
    public JTextArea saveGameTextArea;
    
    public GameMechanic(UI ui) 
    {
        this.ui = ui;
    }
    
    //method to save the game
    public void saveGame() 
    {
        try 
        {
            //screen transition
            ui.clearScreen();
            
            FileOutputStream fileOut = new FileOutputStream("saveData_game.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Player.player);
            out.writeInt(location);
            out.writeInt(part);
            out.close();
            fileOut.close();
            
            //text area saying saved
            saveGameTextPanel = ui.createPanel(100, 100, 600, 250, Color.BLACK);
            saveGameTextArea = ui.createTextArea("Game saved!", ui.gameFont, Color.WHITE, false);
            saveGameTextPanel.add(saveGameTextArea);
            
            //button to go back to game
            backToGameButton = ui.createButton("Back to Game", e -> ui.gameGUI());
            backToGameButtonPanel = ui.createPanel(325, 500, 130, 32, Color.BLACK);
            backToGameButtonPanel.add(backToGameButton);
            
            //add to screen
            ui.screen.add(backToGameButtonPanel);
            ui.screen.add(saveGameTextPanel);
            
            //screen transition
            ui.screen.revalidate();
            ui.screen.repaint();
            
        } catch(IOException e) 
        {
            //screen transition
            ui.clearScreen();
            //text saying error
            JOptionPane.showMessageDialog(ui.screen, "Error saving game...");
            //screen transition
            ui.screen.revalidate();
            ui.screen.repaint();
        }
    }
    
    // method to load game (THIS WILL BE A BUTTON!)
    public void loadGame() 
    {
        try 
        {
            ui.clearScreen();
            
            FileInputStream fileIn = new FileInputStream("saveData_game.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Player loadedPlayer = (Player) objectIn.readObject();
            Player.player = loadedPlayer;
            location = objectIn.readInt();
            part = objectIn.readInt();
            objectIn.close();
            fileIn.close();
            
            //loads to gameGUI
            JOptionPane.showMessageDialog(ui.screen, "Game successfully loaded!");
            ui.gameGUI();
            
            //screen transition
            ui.screen.revalidate();
            ui.screen.repaint();
        } catch(IOException | ClassNotFoundException e) 
        {
            ui.clearScreen();
            // says text error
            JOptionPane.showMessageDialog(ui.screen, "You dont have a saved file!");
            //screen transition
            ui.screen.revalidate();
            ui.screen.repaint();
        }
    }
}

