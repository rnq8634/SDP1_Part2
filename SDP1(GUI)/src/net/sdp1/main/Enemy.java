package net.sdp1.main;

public class Enemy extends Character 
{
    
    //display health
    private HealthBarGui healthBar;
    
    //variables to store the players current xp
    int playerQi;
    
    //enemy specific constructor
    public Enemy(String name, int playerQi) 
    {
        super(name, (int) (Math.random() * playerQi + playerQi/3 + 5), (int) (Math.random() * (playerQi/4 + 2) + 1));
        //assigning variable
        this.playerQi = playerQi;
        this.healthBar = new HealthBarGui(this);
    }
    
    @Override
    public void receiveDamage(int damage) {
        super.receiveDamage(damage);
        this.healthBar.updateHealth();
    }
    
    //display health bar
    public void displayHealthBar() {
        this.healthBar.setVisible(true);
    }
    
    //enemy specific attack and defence calculations
    // can be adjusted
    @Override
    public int attack() 
    {
        return (int) ((Math.random() * (playerQi / 4 * 0.3)) + (playerQi / 4 * 0.7) + 5);
    }
    
    @Override
    public int defend() 
    {
        return (int) ((Math.random() * (playerQi / 4 * 0.3)) + (playerQi / 4 * 0.5) + 3);
    }
}
