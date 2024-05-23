package net.sdp1.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Player extends Character implements Serializable
{
    // health bar for player health
    private transient HealthBarGui healthBar;
    
    //integers to store number of upgrades/skills in each path
    public int numMartialSkill, numMartialBody;
    
    static Player player;
    
    //additional player stats
    int gold, recoverRemaining, elix;
    
    //arrays to store skill names
    public String[] martialSkill = {"Luminous Star Sword Technique", "Supreme Sky Demon Void Sword Art", "Extreme Art of the Blade God", "Sword Force of the Heavenly Demon"};
    public String[] martialBody = {"Cool Blood Blockage", "Extreme Martial Body", "Heavenly Killing Star", "Heavenly Body"};
    
    //player specific constructor
    public Player(String name) 
    {
        // calling constructor of superclass
        super(name, 100, 0);
        // setting of upgrades to 0
        this.numMartialSkill = 0;
        this.numMartialBody = 0;
        //sets additional stats
        this.gold = 5;
        this.recoverRemaining = 1;
        this.elix = 0;
        // health bar GUI
        this.healthBar = new HealthBarGui(this);
        // let the player choose a trait when starting a first playthrough
        martialScroll();
    }
    
    //method to show player health
    public void displayHealthBar() 
    {
        this.healthBar.setVisible(true);
    }
    
    public void receiveDamage(int damage) 
    {
        super.receiveDamage(damage);
        this.healthBar.updateHealth();
    }
    
    private static final long serialVersionUID = 1L;
    
    public Player() {
        super("", 100, 0);
        this.numMartialSkill = 0;
        this.numMartialBody = 0;
        this.gold = 5;
        this.recoverRemaining = 1;
        this.elix = 0;
    }
    
    //player specific method
    @Override
    public int attack() 
    {
        return (int) (Math.random() * (qi/4 + numMartialSkill * 3 + 3) + qi/10 + numMartialSkill * 2 + numMartialBody + 1);
    }
    
    @Override
    public int defend() 
    {
        return (int) (Math.random() * (qi/4 + numMartialBody * 3 + 3) + qi/10 + numMartialBody * 2 + numMartialSkill + 1);
    }
    
    //character stats
    // INFO
    public static void characterInfo() 
    {
        Print.emptySpace();
        Print.separator(30);
        Print.center("[CHARACTER INFO]", 30);
        Print.separator(30);
        System.out.println("[" + player.name + "] " + " [HEALTH] " + player.health + "/" + player.maxHealth);
        player.realm();
        player.combatPower();
        Print.separator(30);
        //player qi and gold
        System.out.println("[QI ACCUMULATED] " + player.qi + " [GOLD] " + player.gold);
        Print.separator(30);
        // number of potions
        System.out.println("[POUCH]");
        System.out.println("[Elixir(s) Held] " + player.elix);
        Print.separator(30);
        //chosen traits
        if(player.numMartialSkill > 0) 
        {
            Print.separator(45);
            System.out.println("[Martial Art] " + player.martialSkill[player.numMartialSkill - 1]);
            Print.separator(45);
        }
        if(player.numMartialBody > 0) 
        {
            Print.separator(45);
            System.out.println("[Martial Body] " + player.martialBody[player.numMartialBody - 1]);
            Print.separator(45);
        }
        player.qiStrengthening();
        Print.enterOneToContinue();
    }
    
    //
    //method to show player's playthrough
    //end results of game
    public static void death() 
    {
        Print.emptySpace();
        Print.separator(50);
        System.out.println("You can feel your QI seeping away as you leave this world behind...");
        System.out.println("You accumulated " + player.qi + " QI in this life. Try to earn more in the next!!!");
        Print.separator(50);
        GameMechanic.isRunning = false;
    }
    
    //combat power, calculates players power based on their current QI and number of martial skill/body they currently have
    public int combatPower() {
        int cPower = qi * 4 + numMartialSkill * 100 + numMartialBody * 10 - (player.maxHealth - player.health);
        System.out.println("[COMBAT POWER] " + cPower);
        return cPower;
    }
    
    //taking a rest
    public static void recover() 
    {
        Print.emptySpace();
        if(player.recoverRemaining >= 1) 
        {
            Print.separator(30);
            System.out.println("Do you want to recover?");
            System.out.println("[RECOVER] " + player.recoverRemaining);
            Print.separator(30);
            System.out.println("[1] Yes, my body's beat...\n[2] No, I'm brimming with QI!!!");
            Print.separator(30);
            int input = Print.userInput("-> ", 2);
            if(input == 1) 
            {
                //player takes a rest
                Print.emptySpace();
                if(player.health < player.maxHealth) 
                {
                    int hpRestored = (int) (Math.random() * (player.qi/4 + 2) + 10);
                    player.health += hpRestored;
                    if(player.health > player.maxHealth)
                        player.health = player.maxHealth;
                    Print.separator(40);
                    System.out.println("You did Restorative Breathing and regained "+ hpRestored + " health.");
                    Print.separator(40);
                    System.out.println("You're now at " + player.health + "/" + player.maxHealth + " health.");
                    Print.separator(30);
                    Print.enterOneToContinue();
                    player.recoverRemaining--;
                }else if(player.health == player.maxHealth) 
                {
                    Print.separator(50);
                    System.out.println("There is no reason to rest since you are at full health!");
                    Print.separator(50);
                    Print.enterOneToContinue();
                }
            }else 
            {
                Print.emptySpace();
                Print.separator(45);
                System.out.println("You felt that there is no reason to rest.");
                Print.separator(45);
                Print.enterOneToContinue();
            }
        }
    }
    
    //method to let the player choose their path
    public void martialScroll() 
    {
        Print.emptySpace();
        Print.heading("Choose a Martial Technique:");
        System.out.println("[1] " + martialSkill[numMartialSkill]);
        System.out.println("[2] " + martialBody[numMartialBody]);
        Print.separator(30);
        //system gets the player's choice
        int input = Print.userInput("-> ", 2);
        Print.emptySpace();
        // deal with both cases
        if(input == 1) 
        {
            Print.separator(50);
            System.out.println("You havearnt " + martialSkill[numMartialSkill] + "!");
            Print.separator(50);
            numMartialSkill++;
        } else 
        {
            Print.separator(50);
            System.out.println("You have attained " + martialBody[numMartialBody] + "!");
            Print.separator(50);
            numMartialBody++;
        }
        Print.enterOneToContinue();
    }
    
    //qi strengthening, increases max health
    public void qiStrengthening() 
    {
        if(qi >= 20 && qi < 60 && maxHealth == 100) 
        {
            maxHealth = 150;
            health = maxHealth;
            Print.emptySpace();
            Print.separator(50);
            System.out.println("You have attained the realm of Primodial Soul.");
            System.out.println("The QI flowing in your veins has strengthened your body!!");
            System.out.println("Maximum Health has been increased from 100 -> 150.");
            Print.separator(50);
        } else if(qi >= 60 && qi < 200 && maxHealth == 150) 
        {
            maxHealth = 200;
            health = maxHealth;
            Print.emptySpace();
            Print.separator(50);
            System.out.println("You have attained the realm of Body Unification.");
            System.out.println("The QI flowing in your veins has united your body and soul!!");
            System.out.println("Maximum Health has been increased from 150 -> 200.");
            Print.separator(50);
        } else if(qi >= 200 && qi < 600 && maxHealth == 200) 
        {
            maxHealth = 800;
            health = maxHealth;
            Print.emptySpace();
            Print.separator(50);
            System.out.println("You are no longer bound by mortal realms...");
            System.out.println("For you, shrouded in limitless QI, are the honored one...");
            System.out.println("Maximum Health has been increased from 200 -> 800.");
            Print.separator(50);
        }
    }
    
    //realms
    public void realm() 
    {
        String title = null;
        if(player.qi < 30) 
        {
            title = "Foundation Building";
        } else if(player.qi < 75) 
        {
            title = "Primordial Soul";
        } else if(player.qi < 200) 
        {
            title = "Unification Stage";
        } else if(player.qi < 600) 
        {
            title = "Supreme Divinity";
        }
        System.out.println("[Realm] " + title);
    }
    
    //loading 
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException 
    {
        in.defaultReadObject();
        String name = (String) in.readObject();
        this.setName(name);
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException 
    {
        out.defaultWriteObject();
        out.writeObject(this.getName());
    }
}
