/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ren.cs1102.Entities;

import lib.TextIO;
/**
 *
 * @author renos
 */
public class CS1102 {

    public static void main(String[] args) {
        int x = 1;

        while ( x < 6 ) {

            System.out.println(x);

            x += 1;
        }
        System.out.println("Done!");

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
