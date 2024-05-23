package net.sdp1.main;

import java.io.Serializable;

public abstract class Character implements Serializable
{
    //variables // stats
    public String name;
    public int maxHealth, health, qi;
    
    //constructor for character
    public Character(String name, int maxHealth, int qi) 
    {
        this.name = name;
        this.maxHealth = maxHealth;
        this.qi = qi;
        this.health = maxHealth;
    }
    
    public void receiveDamage(int damage) 
    {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
    }
    
    public int getHealth() 
    {
        return health;
    }
    
    public int getMaxHealth() 
    {
        return maxHealth;
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
