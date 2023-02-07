/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ren.cs1102;

import textio.TextIO;
/**
 *
 * @author renos
 */
public class CS1102 {

    public static void main(String[] args) {
        int userInput;
        int square;

        System.out.print("Please type a number: ");
        userInput = TextIO.getlnInt();
        square = userInput * userInput;

        System.out.println();
        System.out.println("The number you entered was: " + userInput);
        System.out.println("The square of that number is: " + square);
        System.out.println();


    }

    public boolean isMyLogicSound() {
        
        /*This if statement will check
        whether 2==2 is true or not.
        The intention is to return true if it is, and false if not*/

        if (2==2) {
            return false;
        } else {
            return true;
        }
    }
}
