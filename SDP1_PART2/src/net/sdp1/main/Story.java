package net.sdp1.main;
// story/plot for the game
public class Story 
{
    
    public static void gameIntro() 
    {
        
        Print.center("[STORY]", 50);
        
        System.out.println("You are a scout from the infamous Cult of Demons who");
        System.out.println("seek nothing but power. During your routine patrol,");
        System.out.println("You and your squad members unexpectedly  ran into the");
        System.out.println("the sworn enemies of the Cult of Demons, the Mt. Hua Sect.");
        System.out.println("Without a warning, a Master from the Mt. Hua Sect");
        System.out.println("slaughters you and your comrades. As you loathe your");
        System.out.println("weakness, a game prompt appeared. Asking if you would");
        System.out.println("start over again. Given the chance to start over and");
        System.out.println("with the ability to get stronger faster than anyone else, you");
        System.out.println("vow to survive in order to rise top to the top of the Cult.");
        
    }
    
    public static void storyPartOneStart() 
    {
        
        Print.center("PART - I - BEGINNING", 50);
        
        System.out.println("Waking up from the barracks, just days before your");
        System.out.println("scheduled routine patrol, the game prompt that you");
        System.out.println("thought was a dream couldn't be shaken away from your");
        System.out.println("face. As your fellow comrades walked past, none seem");
        System.out.println("to notice the game prompt. To your surprise, you have");
        System.out.println("the ability to accumulate QI based on who you have killed.");
        System.out.println("This spurs you into action making sure you get stronger");
        System.out.println("before that fateful day....");
        
    }
    
    public static void storyPartOneEnd() 
    {
        
        Print.center("PART - I - END", 50);
        
        System.out.println("You have gathered enough strength and QI in a couple");
        System.out.println("days, but it wasn't enough to face the monster from");
        System.out.println("the Mt. Hua Sect. But you can definitely feel yourself");
        System.out.println("getting stronger in such a short amount of time. You");
        System.out.println("are only about as strong as 5 Demon Footsoldiers, but ");
        System.out.println("you are still far from your goal...");
        
    }
    
    public static void storyPartTwoStart() 
    {
        
        Print.center("PART - II - BEGINNING", 50);
        
        System.out.println("part 2 goes here");
        
    }
    
    public static void storyPartTwoEnd() 
    {
        
        Print.center("PART - II - END", 50);
        
        System.out.println("part 2 goes here");
        
    }
    
    public static void storyPartThreeStart() 
    {
        
        Print.center("PART - III - BEGINNING", 50);
        
        System.out.println("part 3 goes here");
        
    }
    
    public static void storyPartThreeEnd() 
    {
        
        Print.center("PART - III - END", 50);
        
        System.out.println("part 3 goes here");
        
    }
    
    public static void storyPartFourStart() 
    {
        
        Print.center("PART - IV", 50);
        
        System.out.println("part 4 goes here");
        
    }
    
    public static void gameEnd(Player player) 
    {
        
        Print.center("[GAME END]", 50);
        System.out.println("[Well done! " + player.name + "]");
        System.out.println("[You have reached the pinnacle of strength and rose to the top of the Clan!]");
        
    }
}
