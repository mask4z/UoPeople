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

    static int sum = 0;

    static void add(int i) {
        i++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            add(sum);
        }
        System.out.println(sum);
    }

    public boolean isMyLogicSound() {

        /*This if statement will check
        whether 2==2 is true or not.
        The intention is to return true if it is, and false if not*/
        if (2 == 2) {
            return false;
        } else {
            return true;
        }
    }
}
