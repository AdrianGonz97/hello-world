/*
 * File: TestClass.java
 *
 * Author: Adrian Gonzalez
 * 
 * This class is a test class used to test MagicSquare.java.
 * 
 * I affirm that this program is entirely my own work and none of it 
 * is the work of any other person.
 *
 */

import assignment3.MagicSquare ;
import javax.swing.JOptionPane ;

/**
 * A class that tests MagicSquare.java.
 * 
 */
public class TestClass
{
    public static void main (String[] args)
    {
        String input ;       // user input
        int rowsAndColumns ; // number of rows and columns of square
        MagicSquare square ; // magic square declaration
        
        // do once while input must be positive and an odd integer 
        do
        {
            // requests user to input a number
            input = JOptionPane.showInputDialog("Please enter the number of rows"
            + " and columns you would like to have in your magic square.") ;

            // converts string to integer
            rowsAndColumns = Integer.parseInt(input) ;
            
        } while(rowsAndColumns <= 0 || (rowsAndColumns % 2) == 0) ; 
        
        // initializes magic square object
        square = new MagicSquare(rowsAndColumns) ;
        
        // if the magic square is indeed magical
        if(square.isMagical())
            // prints the magic square
            System.out.println(square.toString()) ;
        else // otherwise..
            // print error message
            System.out.println("Error! Square is not magical!") ; 
    }
} // end of TestClass.java
