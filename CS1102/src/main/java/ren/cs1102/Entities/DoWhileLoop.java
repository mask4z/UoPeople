/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ren.cs1102.Entities;

import lib.TextIO;
/**
 *
 * @author renos
 */
public class DoWhileLoop {
    
    public static void main(String[] args) {
        
        boolean wantsToContinue;
        
        do {
            Game.playGame();
            System.out.println("Do you want to play again?");
            wantsToContinue = TextIO.getlnBoolean();
            
        } while (wantsToContinue == true);
    }
}
