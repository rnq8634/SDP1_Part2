package net.sdp1.main;

import java.io.Serializable;
import static net.sdp1.main.GameMechanic.ui;

public abstract class Character implements Serializable
{
    //variables // stats
    public String name;
    public int maxHealth, health, qi, cPower;
    
    //constructor for character
    public Character(String name, int maxHealth, int qi) 
    {
        this.name = name;
        this.maxHealth = maxHealth;
        this.qi = qi;
        this.health = maxHealth;
    }
    
    //combat power, calculates players power based on their current QI and number of martial skill/body they currently have
    public int combatPower() {
        cPower = qi * 4 + ui.player.numMartialSkill * 100 + ui.player.numMartialBody * 10 - (ui.player.maxHealth - ui.player.health);
        return cPower;
    }
    
    public String getName() 
    {
        return name;
    }
    
    public void setName(String name) 
    {
        this.name = name;
    }
    
    //methods that every character has
    public abstract int attack();
    public abstract int defend();
    
}
