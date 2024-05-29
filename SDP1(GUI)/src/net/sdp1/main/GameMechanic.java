package net.sdp1.main;

import java.io.*;

//GAME MECHANIC
public class GameMechanic 
{   
    
    public static boolean isRunning;
    
    //random encounters
    public static String[] turns = {"Battle", "Battle", "Battle", "Recover", "Trader"};
    
    //enemy names
    public static String[] enemies = {"Corrupt Demon Cultist", "Low Demon Soldier", "Wandering Thug", "Crazed Bandit", "Orthodox Sect Member"};
    
    //location
    public static int location = 0, part = 1;
    public static String[] locations = {"Demon Cult Barracks: West Wing", "West Jiang-Hu", "Huangshan Mountain", "Ten Thousand Mountains"};
    
    // method to start the game
    /*public static void startGame() //(THIS WILL BE A BUTTON!)
    {
        // set to false to create a new name
        boolean setName = false;
        String name;
        // to check if loading is required
        boolean loadGame = false;
        
        //check if a saved game exists
        File savedData = new File("saveData_game.ser");
        if(savedData.exists()) 
        {
            Print.emptySpace();
            Print.separator(110);
            System.out.println("[1] New Game \n[2] Load Game\n[3] Exit Game");
            Print.separator(15);
            int choice = Print.userInput("->", 3);
            if(choice == 2) 
            {
                loadGame = true;
            } else if(choice == 3) 
            {
                System.exit(0);
            }
        }
        
        if(!loadGame) 
        {
        //getting the player's name
        do{
            Print.emptySpace();
            Print.heading("Warrior, what is your name?");
            name = Print.scanner.next();
            //asking the player if he wants to correct his choice
            Print.emptySpace();
            Print.heading("Your name is " + name + ".\nIs that correct?");
            System.out.println("[1] Yes it's my name!");
            System.out.println("[2] No, let me change it.");
            int input = Print.userInput("-> ", 2);
            if(input == 1)
                setName = true;
        }while(!setName);
        
        //print the story intro
        Story.gameIntro();
        
        //create new player object with the player's chosen name
        Player.player = new Player(name);
        
        //print the first story act intro
        Story.storyPartOneStart();
        
        // setting isRunning 
        isRunning = true;
        } else 
        {
            // load the saved data
            loadGame();
        }
        
        //start main game loop
        gameLoop();
        
    }
    */
    
    //method to save the game (THIS WILL BE A BUTTON!)
    public static void saveGame() 
    {
        try {
            FileOutputStream fileOut = new FileOutputStream("saveData_game.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Player.player);
            out.writeInt(location);
            out.writeInt(part);
            out.close();
            fileOut.close();
            Print.emptySpace();
            Print.heading("Progess has been saved!");
            Print.enterOneToContinue();
        } catch(IOException e) 
        {
            Print.emptySpace();
            Print.heading("Error saving game: " + e.getMessage());
            Print.enterOneToContinue();
        }
    }
    
    // method to load game (THIS WILL BE A BUTTON!)
    public static void loadGame() 
    {
        try {
            FileInputStream fileIn = new FileInputStream("saveData_game.ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Player loadedPlayer = (Player) objectIn.readObject();
            Player.player = loadedPlayer;
            location = objectIn.readInt();
            part = objectIn.readInt();
            objectIn.close();
            fileIn.close();
            Print.emptySpace();
            Print.separator(45);
            System.out.println("You have returned to your saving point!");
            System.out.println("Loaded Save: " + Player.player);
            Print.separator(45);
            Print.enterOneToContinue();
            isRunning = true;
            gameLoop();
        } catch(IOException | ClassNotFoundException e) 
        {
            Print.emptySpace();
            System.out.println("Error loading game: " + e.getMessage());
            isRunning = false;
        }
    }
    
    public static void storyProgress() {
        if(Player.player.qi >= 40 && part == 1) {
            storyProgression(2, 1, Story::storyPartOneEnd, Story::storyPartTwoStart);
        } else if(Player.player.qi >= 100 && part == 2) {
            storyProgression(3, 2, Story::storyPartTwoEnd, Story::storyPartThreeStart);
        } else if(Player.player.qi >= 250 && part == 3) {
            storyProgression(4, 3, Story::storyPartThreeEnd, Story::storyPartFourStart);
            Boss.finalFight();
        }
    }
    
    //story progression, calls encounters and story starts and ends
    private static void storyProgression(int newPart, int newLocation, Runnable storyPartEnd, Runnable storyPartStart) {
        part = newPart;
        location = newLocation;
        storyPartEnd.run();
        Player.player.martialScroll();
        storyPartStart.run();
        rngEncounters();
        Player.player.health = Player.player.maxHealth;
    }
    
    //encounters in the story
    private static void rngEncounters() {
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
            Random.encounter();
        }
    }
    
    // BATTLE SYSTEM
    public static void battleSystem(Enemy enemy) 
    {
        // main battle loop
        while(true) 
        {
            Print.emptySpace();
            Print.heading(enemy.name + "\n[HEALTH] " + enemy.health + "/" + enemy.maxHealth);
            Print.heading(Player.player.name + "\n[HEALTH] " + Player.player.health + "/" + Player.player.maxHealth);
            Print.center("[ACTIONS]", 30);
            Print.separator(30);
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
                Print.emptySpace();
                Print.separator(40);
                Print.center("[RESULT]", 35);
                //System.out.println("You dealt " + atk + " damage to " + enemy.name + ".");
                Print.separator(40);
                if(atk > 0) {
                    System.out.println("You dealt " + atk + " damage to " + enemy.name + ".");
                    Print.separator(40);
                } else {
                    System.out.println(enemy.name + " has dodged your attack!");
                    Print.separator(40);
                }
                if(atkReceived > 0) {
                    System.out.println(enemy.name + " dealt " + atkReceived + " damage to you.");
                } else {
                    System.out.println("You dodged " + enemy.name + "'s attack!");
                }
                
                //System.out.println(enemy.name + " dealt " + atkReceived + " damage to you.");
                Print.separator(40);
                Print.enterOneToContinue();
                //check if the player is still alive
                if(Player.player.health <= 0) 
                {
                    Player.death();
                    break;
                }else if(enemy.health <= 0) 
                {
                    //to tell the player has won
                    Print.emptySpace();
                    Print.separator(50);
                    Print.center("[BATTLE RESULTS]", 38);
                    Print.separator(50);
                    System.out.println("You killed " + enemy.name + " and harvested their QI!");
                    Print.separator(50);
                    //random qi gained based on enemy qi(idea)
                    //int qiGained = (int) (Math.random() * (enemy.qi / 2) + 3);
                    //increase player qi
                    Player.player.qi += enemy.qi;
                    System.out.println("You accumulated " + enemy.qi + " QI!");
                    Print.separator(50);
                    //enemy drops
                    boolean addRecover = (Math.random() * 5 + 1 <= 0.5);
                    int goldEarned = (int) (Math.random() * enemy.qi);
                    if(addRecover) 
                    {
                        Player.player.recoverRemaining++;
                        System.out.println("You earned another chance to recover!");
                        Print.separator(50);
                    }
                    if(goldEarned > 0) 
                    {
                        Player.player.gold += goldEarned;
                        System.out.println("You looted " + goldEarned + "G from " + enemy.name + "'s corpse!");
                        Print.separator(50);
                    }
                    Print.enterOneToContinue();
                    break;
                }
            }else if(input == 2) 
            {
                //USE ELIXIR
                Print.emptySpace();
                if(Player.player.elix > 0 && Player.player.health < Player.player.maxHealth) 
                {
                    //player CAN USE a elixir
                    Print.heading("[HEALTH] " + Player.player.health + "/" + Player.player.maxHealth);
                    System.out.println("[ELIXIR HELD] " + Player.player.elix);
                    System.out.println("Do you want to consume this elixir?");
                    Print.separator(30);
                    System.out.println("[1] Yes, I'm in a pinch!\n[2] Not now.");
                    input = Print.userInput("-> ", 2);
                    if(input == 1) 
                    {
                        // if player uses elixir
                        Player.player.health = Player.player.maxHealth;
                        Print.emptySpace();
                        Print.separator(45);
                        System.out.println("You consumed an elixir.");
                        System.out.println("Health has been restored to " + Player.player.maxHealth);
                        Print.separator(45);
                        Player.player.elix--;
                        Print.enterOneToContinue();
                    }
                }else 
                {
                    //player doesnt have an elixir
                    Print.heading("You do not have any elixir!");
                    Print.enterOneToContinue();
                }
            }else{
                //RUN AWAY
                Print.emptySpace();
                //check that the player isnt in the last act so that they can't run awawy from boss
                if(part != 4) 
                {
                    //chance to run away
                    if(Math.random() * 10 + 1 <= 4.5) 
                    {
                        Print.heading("You have escaped from " + enemy.name + "!");
                        Print.enterOneToContinue();
                        break;
                    }else
                    {
                        Print.heading("You didn't manage to escape.");
                        //calculate the damage the player takes
                        int dmgReceived = enemy.attack();
                        System.out.println("You took " + dmgReceived + " damage while trying to flee!");
                        Print.separator(30);
                        Player.player.health -= dmgReceived;
                        Print.enterOneToContinue();
                        //check if the player is alive
                        if(Player.player.health <= 0)
                            Player.death();
                    }
                }else
                {
                    Print.heading("Escape is futile...");
                    Print.enterOneToContinue();
                }
            }
        }
    }
    
    // main game loop
    public static void gameLoop() 
    {
        while(isRunning)
        {
            CUI.actionMenu();
            int input = Print.userInput("-> ", 4);
            if(input == 1) {
                continueJourney();
            } else if(input == 2) {
                Player.characterInfo();
            } else if(input == 3) {
                saveGame();
            } else if(input == 4) {
                isRunning = false;
            }
        }
    }
}
