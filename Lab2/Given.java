/**
 * Given.java
 * 
 * @author Joel Swanson
 * @version 01/27/2013
 */

import java.util.Scanner;

/**
 * Given is a class for allowing static input from the user.  It is intended
 * only to be a guide for how to use the Scanner class.  It was given to you
 * to simplify input before you learn about Scanner.
 */
public class Given
{

    /**
     * Read an integer value from the keyboard. If the user enters anything 
     * that is not an integer, discard the input and read another value from
     * the user.
     * 
     * @return The integer that the user entered from the keyboard.
     */
    public static int getInt()
    {
        Scanner scanner = new Scanner(System.in);
        
        //Open the bluej terminal window if it isnt already open
        //by printing essentially nothing.
        System.out.print("");
        
        //This variable will become true when the user enters
        //an integer.
        boolean done = false;

        //Temporary storage for the input integer.
        int a = 0;
        
        //Read input until a good integer is read.
        while (!done) 
        {
            if (scanner.hasNextInt())  
            {
                a = scanner.nextInt();
                done = true; 
            }  
            else  
            { 
                System.out.println("\nDATA ERROR: You must enter an integer!");
                System.out.print("Try again: ");               
                //Get rid of ant non integer input
                scanner.nextLine();           
            }
        }
        return a;
    }
    
    /**
     * Read a double value from the keyboard. If the user enters anything 
     * that is not an double, discard the input and read another value from
     * the user.
     * 
     * @return The integer that the user entered from the keyboard.
     */
    public static double getDouble()
    {
        Scanner scanner = new Scanner(System.in);
        
        //Open the bluej terminal window if it isnt already open
        //by printing essentially nothing.
        System.out.print("");
        
        //This variable will become true when the user enters
        //an integer.
        boolean done = false;

        //Temporary storage for the input integer.
        double d = 0;
        
        //Read input until a good integer is read.
        while (!done) 
        {
            if (scanner.hasNextDouble())  
            {
                d = scanner.nextDouble();
                done = true; 
            }  
            else  
            {   
                System.out.println("\nDATA ERROR: You must enter a double!");
                System.out.print("Try again: ");
                
                //Get rid of any non integer input
                scanner.nextLine();           
            }
        }
        return d;
    }

    /**
     * Read a string from the user and return it.
     * 
     * @return The string that the user entered from the keyboard.
     */
    public static String getString()
    {
        Scanner scanner = new Scanner(System.in);
        
        //Open the bluej terminal window if it isnt already open.
        System.out.print("");
        
        if (scanner.hasNextLine())
        {
            return scanner.nextLine();
        }
        else
        {
            return "Error";
        }
    }

}

