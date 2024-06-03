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
    
    //random encounters
    public static String[] turns = {"Battle", "Battle", "Battle", "Recover", "Trader"};
    
    //enemy names
    public static String[] enemies = {"Corrupt Demon Cultist", "Low Demon Soldier", "Wandering Thug", "Crazed Bandit", "Orthodox Sect Member"};
    
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
    
    public static void storyProgress() 
    {
        if(Player.player.qi >= 40 && part == 1) 
        {
            storyProgression(2, 1, Story::storyPartOneEnd, Story::storyPartTwoStart);
        } else if(Player.player.qi >= 100 && part == 2) 
        {
            storyProgression(3, 2, Story::storyPartTwoEnd, Story::storyPartThreeStart);
        } else if(Player.player.qi >= 250 && part == 3) 
        {
            storyProgression(4, 3, Story::storyPartThreeEnd, Story::storyPartFourStart);
            Boss.finalFight();
        }
    }
    
    //story progression, calls encounters and story starts and ends
    private static void storyProgression(int newPart, int newLocation, Runnable storyPartEnd, Runnable storyPartStart) 
    {
        part = newPart;
        location = newLocation;
        storyPartEnd.run();
        //Player.player.martialScroll();
        storyPartStart.run();
        rngEncounters();
        Player.player.health = Player.player.maxHealth;
    }
    
    //encounters in the story
    private static void rngEncounters() 
    {
        String[] enemies = new String[] {"Evil Mercenary", "Cultist", "Wandering Swordsman", "Demon Cultist", "Orthodox Sect Member"};
        String[] turns = new String[] {"Battle", "Battle", "Battle", "Recover", "Trader"};
        System.arraycopy(enemies, 0, enemies, 0, enemies.length);
        System.arraycopy(turns, 0, turns, 0, turns.length);
    }
    
    //method to continue the journey (THIS WILL BE A BUTTON!!!)
    public static void continueJourney() 
    {
        //check which part of the story the player is on
        storyProgress();
        //check if the player isnt in the last part of the story
        if(part != 4) {
            //Random.encounter();
        }
    }
    
    // BATTLE SYSTEM
    public static void battleSystem(Enemy enemy) 
    {
        // main battle loop
        while(true) 
        {
            
            Print.heading(enemy.name + "\n[HEALTH] " + enemy.health + "/" + enemy.maxHealth);
            Print.heading(Player.player.name + "\n[HEALTH] " + Player.player.health + "/" + Player.player.maxHealth);
            Print.center("[ACTIONS]", 30);
            
            System.out.println("[1] Fight\n[2] Use an Elixir\n[3] Retreat");
            int input = Print.userInput("-> ", 3);
            //react to user input
            if(input == 1) 
            {
                //FIGHT
                // calculate dmg and dmgReceived (dmg enemy deals to player)
                int atk = Player.player.attack() - enemy.defend();
                int atkReceived = enemy.attack() - Player.player.defend();
                //check that the dmg isnt negative
                if(atkReceived < 0) 
                {
                    // add some dmg if player defends very well
                    atk -= atkReceived/2;
                    atkReceived = 0;
                }
                if(atk < 0) atk = 0;
                //deal dmg to both parties
                Player.player.health -= atkReceived;
                enemy.health -= atk;
                // print the info of the battle
                
                Print.center("[RESULT]", 35);
                //System.out.println("You dealt " + atk + " damage to " + enemy.name + ".");
                
                if(atk > 0) {
                    System.out.println("You dealt " + atk + " damage to " + enemy.name + ".");
                    
                } else {
                    System.out.println(enemy.name + " has dodged your attack!");
                    
                }
                if(atkReceived > 0) {
                    System.out.println(enemy.name + " dealt " + atkReceived + " damage to you.");
                } else {
                    System.out.println("You dodged " + enemy.name + "'s attack!");
                }
                
                //System.out.println(enemy.name + " dealt " + atkReceived + " damage to you.");
                
                //check if the player is still alive
                if(Player.player.health <= 0) 
                {
                    //Player.death();
                    break;
                }else if(enemy.health <= 0) 
                {
                    //to tell the player has won
                    
                    Print.center("[BATTLE RESULTS]", 38);
                    
                    System.out.println("You killed " + enemy.name + " and harvested their QI!");
                    
                    //random qi gained based on enemy qi(idea)
                    //int qiGained = (int) (Math.random() * (enemy.qi / 2) + 3);
                    //increase player qi
                    Player.player.qi += enemy.qi;
                    System.out.println("You accumulated " + enemy.qi + " QI!");
                    
                    //enemy drops
                    boolean addRecover = (Math.random() * 5 + 1 <= 0.5);
                    int goldEarned = (int) (Math.random() * enemy.qi);
                    if(addRecover) 
                    {
                        Player.player.recoverRemaining++;
                        System.out.println("You earned another chance to recover!");
                        
                    }
                    if(goldEarned > 0) 
                    {
                        Player.player.gold += goldEarned;
                        System.out.println("You looted " + goldEarned + "G from " + enemy.name + "'s corpse!");
                        Print.separator(50);
                    }
                    
                    break;
                }
            }else if(input == 2) 
            {
                //USE ELIXIR
                
                if(Player.player.elix > 0 && Player.player.health < Player.player.maxHealth) 
                {
                    //player CAN USE a elixir
                    Print.heading("[HEALTH] " + Player.player.health + "/" + Player.player.maxHealth);
                    System.out.println("[ELIXIR HELD] " + Player.player.elix);
                    System.out.println("Do you want to consume this elixir?");
                    
                    System.out.println("[1] Yes, I'm in a pinch!\n[2] Not now.");
                    input = Print.userInput("-> ", 2);
                    if(input == 1) 
                    {
                        // if player uses elixir
                        Player.player.health = Player.player.maxHealth;
                        
                        System.out.println("You consumed an elixir.");
                        System.out.println("Health has been restored to " + Player.player.maxHealth);
                        
                        Player.player.elix--;
                        
                    }
                }else 
                {
                    //player doesnt have an elixir
                    Print.heading("You do not have any elixir!");
                    
                }
            }else{
                //RUN AWAY
                
                //check that the player isnt in the last act so that they can't run awawy from boss
                if(part != 4) 
                {
                    //chance to run away
                    if(Math.random() * 10 + 1 <= 4.5) 
                    {
                        Print.heading("You have escaped from " + enemy.name + "!");
                        
                        break;
                    }else
                    {
                        Print.heading("You didn't manage to escape.");
                        //calculate the damage the player takes
                        int dmgReceived = enemy.attack();
                        System.out.println("You took " + dmgReceived + " damage while trying to flee!");
                        
                        Player.player.health -= dmgReceived;
                        
                        //check if the player is alive
                        if(Player.player.health <= 0) {
                            //Player.death();
                        }
                    }
                }else
                {
                    Print.heading("Escape is futile...");
                    
                }
            }
        }
    }
    
    // main game loop
    public void gameLoop() 
    {
        while(isRunning)
        {
            CUI.actionMenu();
            int input = Print.userInput("-> ", 4);
            if(input == 1) {
                continueJourney();
            } else if(input == 2) {
                //Player.characterInfo();
            } else if(input == 3) {
                saveGame();
            } else if(input == 4) {
                isRunning = false;
            }
        }
    }
}
