/*
 * File: MagicSquare.java
 *
 * Author: Adrian Gonzalez
 * 
 * 1. A Magic Square is a N by N (N being a given odd positive integer) square
 *    with a collection of integers inside (N^2) that has the same, equal sum 
 *    when you find the sum of a row, column, or diagonal. 
 * 
 * 2. The magic square can be verified to be 'magical' by using the isMagical()
 *    method, which returns a boolean value that states true if it's magical, or 
 *    false if not.
 * 
 * 3. The magic square is populated with numbers using the following algorithm
 *    in order for its magical properties to be in place.  
 *  - Number of sides must be positive and be an odd number.
 *  - The element in the middle column of the last row must be 1 and begin here.
 *  - Attempt to place each progressive number in the row below, and column to 
 *    the right of the previous number.
 *  - If following row is non-existent, then the number is placed instead in
 *    in the first row, one column to the right.
 *  - If the following column is non-existent, then the number is placed instead
 *    in the first column, one row down.
 *  - If the following column and row is non-existent, then the number is placed
 *    directly aboce the previous number.
 *  - If the placement of the number leads to a position that is not empty, then
 *    the number is placed directly above the previous number.
 * 4. The magic square is formatted in a multi-lined string in the toString()
 *    method to form the shape of the magic square with the numbers.
 * 
 * I affirm that this program is entirely my own work and none of it 
 * is the work of any other person.
 * 
 */
package assignment3;

/**
 * A Magic Square is a N by N (N being a given odd positive integer) square with
 * a collection of integers inside (N^2) that has the same, equal sum when you 
 * find the sum of a row, column, or diagonal. 
 */
public class MagicSquare
{
    private int[][] magicSquare;    // a magic square
    
    /**
     * Creates a MagicSquare object.
     * 
     * @param numOfRowsAndColumns the number of rows and columns the square has
     */
    public MagicSquare(int numOfRowsAndColumns)
    {
        // initializes magic square with given size of a side number
        magicSquare = new int[numOfRowsAndColumns][numOfRowsAndColumns] ;
        
        // populates array with 0, indicating the space is empty
        for (int i = 0; i < magicSquare.length; i++)
        {
            for(int j = 0; j < magicSquare[i].length; j++)
            {
                magicSquare[i][j] = 0 ;
            }
        }
        
        fillSquare() ; // populates square with numbers
    }
    
    /*
    * Populates the square with numbers, making it magical. Magical meaning that
    * the sums of a row is equal to the sum of the rest of the rows, columns,
    * and diagonals in the square. 
    */
    private void fillSquare()
    {
        // the setting of numbers start on the bottom row, middle column with 1
        int column = magicSquare.length / 2 ;   // current column element
        int row = magicSquare.length - 1 ;      // current row element
        int size = magicSquare.length ;         // size of one side of square
        
        magicSquare[row][column] = 1 ; // sets #1 in middle column, bottom row
        
        // populates the square with numbers 2 - N^2, N being a given number
        for(int num = 2; num <= Math.pow(size, 2); num++)
        {
            int newRow = row ;      // row to test if new space is open
            int newColumn = column ;// column to test if new space is open
            
            // if a row below exists
            if(newRow < size - 1)
            {
                // new row moves down a 
                newRow++ ;
                
                // if a column to the right exists..
                if(newColumn < size - 1)
                    newColumn++ ;
                else
                    newColumn = 0 ;
            }
            else // otherwise..
            {   
                // if column to the right exists but row below is non-existent..
                if(newColumn < size - 1)
                {
                    newRow = 0 ;
                    newColumn++ ;
                }
                else // otherwise if they both are non-existent..
                {
                    // number goes directly above the previous number
                    newRow-- ;
                }
            }
            
            // if the new space is occupied...
            if(magicSquare[newRow][newColumn] != 0)
            {
                // number goes directly above the previous number
                row-- ;
                magicSquare[row][column] = num ; 
            }
            else // otherwise..
            {
                // number goes in avaliable space
                magicSquare[newRow][newColumn] = num ;
                // new row and column become the official row and column
                row = newRow ;
                column = newColumn ;
            }
        }
    }
    
    /**
     * Determines if the sums of all rows, columns, and diagonals are equal.
     * 
     * @return a boolean value that is true if the sums of all rows, columns, 
     * and diagonals are equal, otherwise false. 
     */
    public boolean isMagical()
    {
        boolean isMagical = false ;     // whether the square is magical
        int squareSize = magicSquare.length ;   // size of 1 side of square 
        int sumNum = 0 ;                        // sum that all sums must = to
        int[] columnSum = new int[squareSize] ; // list of sums of columns
        int[] rowSum = new int[squareSize] ;    // list of sums of rows
        int[] diagonalSum = new int[2] ;        // list of sums of 2 diagonals
        
        // collects all sums of rows, columns, and diagonals and stores in list
        for(int i = 0; i < magicSquare.length; i++)
        {
            for(int j = 0; j < magicSquare[i].length; j++)
            {
                columnSum[i] += magicSquare[j][i] ;
                rowSum[i] += magicSquare[i][j] ;
            }
            
            // collects diagonal sum going top left to bottom right
            diagonalSum[0] += magicSquare[i][i] ;
            squareSize-- ;
            // collects diagonal sum going top right to bottom left
            diagonalSum[1] += magicSquare[squareSize][squareSize] ;
        }
        
        // a sum is declared to sumNum
        sumNum = rowSum[0] ;
        
        // traverses the sum lists to see if they are all equal to eachother
        for(int i = 0; i < magicSquare.length; i++)
        {
            // if all rows equal the special sumNum..
            if(rowSum[i] == sumNum)
                // if all columns equal the special sumNum..
                if(columnSum[i] == sumNum)
                    // if both diagonals equal the special sumNum..
                    if(diagonalSum[0] == sumNum && diagonalSum[1] == sumNum)
                        // the square is magical
                        isMagical = true ;
            
            // if isMagical is false, break out of loop
            if(!isMagical)
                break ;
        }
        
        return isMagical ; // returns result
    }
    
    /**
     * Returns a multi-lined string in the shape of the magic square.
     * 
     * @return a formated string in the shape of the magic square 
     */
    public String toString()
    {
        String output = ""; // string to be formated as the magic square
        
        // traverses the array for rows
        for(int i = 0; i < magicSquare.length; i++)
        {
            // traverses the array for columns
            for(int j = 0; j < magicSquare[i].length; j++)
            {
                // formats number to a string for each num in a row
                output += String.format(("%6d"), magicSquare[i][j]) ;
            }
            // next line for new row
            output += String.format("%n") ; 
        }
        // returns formatted string
        return output ;
    }
} // end of MagicSquare class declaration
