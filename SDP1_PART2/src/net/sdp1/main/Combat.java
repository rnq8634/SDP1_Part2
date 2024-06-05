package net.sdp1.main;

import java.awt.Color;

public class Combat {   
    //class reference
    public UI ui;
    public Enemy enemy;
    public PlayerDeath playerDeath;
    
    public Combat(UI ui) 
    {
        this.ui = ui;
    }
    
    //includes attack and death of enemy or player
    public void attack() {
        // calculate dmg and dmgReceived (dmg enemy deals to player)
        int atk = ui.player.attack() - enemy.defend();
        int atkReceived = enemy.attack() - ui.player.defend();
        
        //deal dmg to both
        ui.player.health -= atkReceived;
        enemy.health -= atk;
        
        //refresh player hp
        refreshHP();
        
        //printing attack results
        ui.gameTextArea.setText(enemy.name + " dealt " + atkReceived + " damage to you.");
        
        if (ui.player.health <= 0) 
        {
            ui.playerDeath.death();
        } else if (enemy.health <= 0) {
            
            ui.player.qi += enemy.qi;
            
            ui.gameTextArea.setText("You have killed " + enemy.name);
            
            ui.gameGUI();
        }
        
    }
    
    public void refreshHP() {
        ui.hpLabel.setText("[HP] " + ui.player.getHealth());
        
        //refresh screen
        ui.screen.revalidate();
        ui.screen.repaint();
    }
    
    //player can half the damage they receive
    public void defend() {
        
        //dodging
        
    }
    
    public void battleSystem(Enemy enemy) {
        this.enemy = enemy;
        
        //game text
        ui.gameTextArea.setText(enemy.name + " HP: " + enemy.health + "/" + enemy.maxHealth + "\n\nWhat will you do?");
        
        //actions
        ui.buttonEditor(ui.action1, "Attack", e -> attack());//attack method
            
        ui.buttonEditor(ui.action2, "Defend", e -> defend());//defend method
            
        ui.buttonEditor(ui.action3, "Drink Elixir", null);
            
        ui.buttonEditor(ui.action4, "Run away", e -> ui.gameGUI());
        
    }
    
    // BATTLE SYSTEM
    public static void battleSystemREF(Enemy enemy) 
    {
        // main battle loop
        while(true) 
        {
            //int input = Print.userInput("-> ", 3);
            //react to user input
            //if(input == 1) 
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
                
                //Print.center("[RESULT]", 35);
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
                    
                    //Print.center("[BATTLE RESULTS]", 38);
                    
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
                        //Print.separator(50);
                    }
                    
                    break;
                }
            }//else if(input == 2) 
            {
                //USE ELIXIR
                
                if(Player.player.elix > 0 && Player.player.health < Player.player.maxHealth) 
                {
                    //player CAN USE a elixir
                    //Print.heading("[HEALTH] " + Player.player.health + "/" + Player.player.maxHealth);
                    System.out.println("[ELIXIR HELD] " + Player.player.elix);
                    System.out.println("Do you want to consume this elixir?");
                    
                    System.out.println("[1] Yes, I'm in a pinch!\n[2] Not now.");
                    //input = Print.userInput("-> ", 2);
                    //if(input == 1) 
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
                    //Print.heading("You do not have any elixir!");
                    
                }
            }//else{
                //RUN AWAY
                
                //check that the player isnt in the last act so that they can't run awawy from boss
                //if(part != 4) 
                {
                    //chance to run away
                    if(Math.random() * 10 + 1 <= 4.5) 
                    {
                        //Print.heading("You have escaped from " + enemy.name + "!");
                        
                        break;
                    }else
                    {
                        //Print.heading("You didn't manage to escape.");
                        //calculate the damage the player takes
                        int dmgReceived = enemy.attack();
                        System.out.println("You took " + dmgReceived + " damage while trying to flee!");
                        
                        Player.player.health -= dmgReceived;
                        
                        //check if the player is alive
                        if(Player.player.health <= 0) {
                            //Player.death();
                        }
                    }
                }//else
                {
                    //Print.heading("Escape is futile...");
                    
                }
            }
        }
    }
