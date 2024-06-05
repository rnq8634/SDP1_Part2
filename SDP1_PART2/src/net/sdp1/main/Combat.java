package net.sdp1.main;

public class Combat 
{   
    //class reference
    public UI ui;
    public Enemy enemy;
    public PlayerDeath playerDeath;
    
    public Combat(UI ui, PlayerDeath playerDeath) 
    {
        this.ui = ui;
        this.playerDeath = playerDeath;
    }
    
    //includes attack and death of enemy or player
    public void attack() 
    {
        while(ui.player.health > 0 && enemy.health > 0) 
        {
            int playerDamage = calculateDamage(ui.player.attack(), enemy.defend());
            int enemyDamage = calculateDamage(enemy.attack(), ui.player.defend());
            
            dealDamage(ui.player, enemyDamage);
            dealDamage(enemy, playerDamage);
            
            //refreshes hp screen
            refreshHP();
            //prints attack results
            attackResults(playerDamage, enemyDamage);
            
            //actions
            ui.buttonEditor(ui.action1, ">", e -> continueBattle());
        
            ui.buttonEditor(ui.action2, "", null);
        
            ui.buttonEditor(ui.action3, "", null);
        
            ui.buttonEditor(ui.action4, "", null);
        }
    }
    
    //continue input
    public void continueBattle() 
    {
        if (ui.player.health <= 0) 
        {
            playerDeathHandler();
        } else if (enemy.health <= 0) 
        {
            enemyDrops();
        } else 
        {
            ui.buttonEditor(ui.action1, "Attack", e -> attack());
        
            ui.buttonEditor(ui.action2, "Defend", e -> defend());
        
            ui.buttonEditor(ui.action3, "Drink Elixir", e -> elixir());
        
            ui.buttonEditor(ui.action4, "Run away", e -> ui.gameGUI());
        }
    }
    
    //deals damage to characters
    public void dealDamage(Character character, int damage) 
    {
        character.health -= damage;
    }
    
    //calculates damage for player and enemy
    public int calculateDamage(int attack, int defense) 
    {
        int damage = attack - defense;
        return Math.max(damage, 0);
    }
    
    //prints the attack results
    public void attackResults(int playerDamage, int enemyDamage) 
    {
        ui.gameTextArea.setText(
                enemy.name + " dealt " + enemyDamage + " damage to you." + "\n\n" +
                "You dealt " + playerDamage + " damage to " + enemy.name + "!");
    }
    
    //handles player death
    public void playerDeathHandler() 
    {
        ui.playerDeath.death();
    }
    
    //prints battle results and player gets drops/qi
    public void enemyDrops() 
    {
        ui.player.qi += enemy.qi;
        ui.gameTextArea.setText("You have killed " + enemy.name);
        
        //actions
        ui.buttonEditor(ui.action1, ">", e -> ui.gameGUI());
        
        ui.buttonEditor(ui.action2, "", null);
        
        ui.buttonEditor(ui.action3, "", null);
        
        ui.buttonEditor(ui.action4, "", null);
    }
    
    //refreshes the hp of the player on screen
    public void refreshHP() 
    {
        ui.hpLabel.setText("[HP] " + ui.player.getHealth());
        
        //refresh screen
        ui.screen.revalidate();
        ui.screen.repaint();
    }
    
    //player can half the damage they receive
    public void defend() 
    {
        //dodging/defend
    }
    
    public void elixir() 
    {
        //drinking recovery elixir
    }
    
    public void battleSystem(Enemy enemy) 
    {
        this.enemy = enemy;
        
        //game text
        ui.gameTextArea.setText(enemy.name + " HP: " + enemy.health + "/" + enemy.maxHealth + "\n\nWhat will you do?");
        
        //actions
        ui.buttonEditor(ui.action1, "Attack", e -> attack());//attack method
            
        ui.buttonEditor(ui.action2, "Defend", e -> defend());//defend method
            
        ui.buttonEditor(ui.action3, "Drink Elixir", e -> elixir());
            
        ui.buttonEditor(ui.action4, "Run away", e -> ui.gameGUI());
    }
}
