/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ren.cs1102;

/**
 *
 * @author renos
 */
public class CS1102 {

    public static void main(String[] args) {
        int x = 3;
        x = x++;
        System.out.println(x);

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
