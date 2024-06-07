package net.sdp1.main;

public class Random 
{
    
    public Combat combat;
    public UI ui;
    
    public String[] turns = {"Battle", "Battle", "Battle", "Recover", "Trader"};
    
    //enemy names
    public String[] enemies = {"Corrupt Demon Cultist", "Low Demon Soldier", "Wandering Thug", "Crazed Bandit", "Orthodox Sect Member"};
    
    public Random(UI ui) 
    {
        this.ui = ui;
    }
    
    //random battle method
    public void randomNameEnemy() 
    {   
        // method for random enemy name
        ui.combat.battleSystem(new Enemy(enemies[(int)(Math.random() * enemies.length)], ui.player.qi));
    }
    
}
