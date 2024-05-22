package net.sdp1.main;

import java.util.Scanner;

// for designing menus/creating space
public class Print 
{
    
    // scan input
    static Scanner scanner = new Scanner(System.in);
    
    // method to get user input from console
    public static int userInput(String prompt, int playerChoices) 
    {
        int input;
        
        do{
            System.out.println(prompt);
            try{
                input = Integer.parseInt(scanner.next());
            }catch(Exception e) 
            {
                input = -1;
                System.out.println("Please enter a valid number!");
            }
        }while(input < 1 || input > playerChoices);
        return input;
    }
    
    // method to clear the console & make space
    public static void emptySpace() 
    {
        for(int i = 0; i < 25; i++)
            System.out.print("\n");
    }
    
    //method to center the title
    public static void center(String text, int width) 
    {
        int spaces = (width - text.length()) / 2;
        System.out.printf("%" + spaces + "s%s%" + spaces + "s%n", "", text, "");
    }
    
    //method to print a seperator with length n
    public static void separator(int n) 
    {
        for(int i = 0; i < n; i++)
            System.out.print("=");
        System.out.println("");
    }
    
    public static void heading(String title) 
    {
        separator(30);
        System.out.println(title);
        separator(30);
    }
    
    //method to continue the game with the user's pace
    public static void enterOneToContinue() 
    {
        System.out.println("\nEnter 1 to continue...");
        scanner.next();
    }
}
