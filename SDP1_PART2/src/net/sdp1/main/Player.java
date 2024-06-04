package net.sdp1.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import static net.sdp1.main.GameMechanic.ui;

public class Player extends Character implements Serializable
{
    
    //integers to store number of upgrades/skills in each path
    public int numMartialSkill, numMartialBody;
    
    public String title;
    
    static Player player;
    
    //additional player stats
    int gold, recoverRemaining, elix, cPower;
    
    //arrays to store skill names
    public String[] martialSkill = 
    {
        "Luminous Star Sword", 
        "Supreme Sky Demon Void Sword Art", 
        "Extreme Art of the Blade God", 
        "Sword Force of the Heavenly Demon"};
    public String[] martialBody = 
    {
        "Cool Blood Blockage", 
        "Extreme Martial Body", 
        "Heavenly Killing Star", 
        "Heavenly Body"};
    
    //player specific constructor
    public Player(String name) 
    {
        // calling constructor of superclass
        super(name, 100, 0);
        // setting of upgrades to 0
        this.numMartialSkill = 0;
        this.numMartialBody = 0;
        //sets additional stats
        this.gold = 0;
        this.recoverRemaining = 1;
        this.elix = 0;
        this.title = "";
        
        // let the player choose a trait when starting a first playthrough
        //martialScroll();
    }
    
    //character stats
    // INFO
    /*public static void characterInfo() 
    {
        
        Print.center("[CHARACTER INFO]", 30);
        
        System.out.println("[" + player.name + "] " + " [HEALTH] " + player.health + "/" + player.maxHealth);
        player.realm();
        player.combatPower();
        
        //player qi and gold
        System.out.println("[QI ACCUMULATED] " + player.qi + " [GOLD] " + player.gold);
        
        // number of potions
        System.out.println("[POUCH]");
        System.out.println("[Elixir(s) Held] " + player.elix);
        
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
        
    }*/
    
    //end results of game
    /*public static void death() 
    {
        
        System.out.println("You can feel your QI seeping away as you leave this world behind...");
        System.out.println("You accumulated " + player.qi + " QI in this life. Try to earn more in the next!!!");
        
    }
    
    //method to let the player choose their path
    public void martialScroll() 
    {
        Print.heading("Choose a Martial Technique:");
        System.out.println("[1] " + martialSkill[numMartialSkill]);
        System.out.println("[2] " + martialBody[numMartialBody]);
        
        //system gets the player's choice
        int input = Print.userInput("-> ", 2);
    
        // deal with both cases
        if(input == 1) 
        {
            System.out.println("You havearnt " + martialSkill[numMartialSkill] + "!");
            
            numMartialSkill++;
        } else 
        {
            System.out.println("You have attained " + martialBody[numMartialBody] + "!");
            
            numMartialBody++;
        }
        
    }
    
    //qi strengthening, increases max health
    public void qiStrengthening() 
    {
        if(qi >= 20 && qi < 60 && maxHealth == 100) 
        {
            maxHealth = 150;
            health = maxHealth;
            
            System.out.println("You have attained the realm of Primodial Soul.");
            System.out.println("The QI flowing in your veins has strengthened your body!!");
            System.out.println("Maximum Health has been increased from 100 -> 150.");
            
        } else if(qi >= 60 && qi < 200 && maxHealth == 150) 
        {
            maxHealth = 200;
            health = maxHealth;
            
            System.out.println("You have attained the realm of Body Unification.");
            System.out.println("The QI flowing in your veins has united your body and soul!!");
            System.out.println("Maximum Health has been increased from 150 -> 200.");
            
        } else if(qi >= 200 && qi < 600 && maxHealth == 200) 
        {
            maxHealth = 800;
            health = maxHealth;
            
            System.out.println("You are no longer bound by mortal realms...");
            System.out.println("For you, shrouded in limitless QI, are the honored one...");
            System.out.println("Maximum Health has been increased from 200 -> 800.");
        }
    }*/
    
    //realms
    public String realm() 
    {
        if(ui.player.qi < 30) 
        {
            title = "Foundation Building";
        } else if(ui.player.qi < 75) 
        {
            title = "Primordial Soul";
        } else if(ui.player.qi < 200) 
        {
            title = "Unification Stage";
        } else if(ui.player.qi < 600) 
        {
            title = "Supreme Divinity";
        }
        return title;
    }
    
    //getters and setters
    public int getNumMartialSkill() 
    {
        return numMartialSkill;
    }
    
    public void setNumMartialSkill(int numMartialSkill) 
    {
        this.numMartialSkill = numMartialSkill;
    }
    
    public int getNumMartialBody() 
    {
        return numMartialBody;
    }
    
    public void setNumMartialBody(int numMartialBody) 
    {
        this.numMartialBody = numMartialBody;
    }
    
    public int getGold() 
    {
        return gold;
    }
    
    public int getRecoverRemaining() 
    {
        return recoverRemaining;
    }
    
    public void setRecoverRemaining(int recoverRemaining) 
    {
        this.recoverRemaining = recoverRemaining;
    }
    
    public String getTitle() 
    {
        return title;
    }
    
    public int getElix() 
    {
        return elix;
    }
    
    public void setElix(int elix) 
    {
        this.elix = elix;
    }
    
    public String[] getMartialSkill() 
    {
        return martialSkill;
    }
    
    public String[] getMartialBody() 
    {
        return martialBody;
    }
    
    public void setMartialSkill(String[] martialSkill) 
    {
        this.martialSkill = martialSkill;
    }
    
    public void setMartialBody(String[] martialBody) 
    {
        this.martialBody = martialBody;
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
