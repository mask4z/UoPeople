/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ren.cs1102.Entities;

/**
 *
 * @author renos
 */
public class Unit2Control {

    public static void main(String[] args) {
        forLoop();
    }

    public static void countTillBlastoff() {

        int counter = 10;

        while (counter > 0) {
            System.out.println(counter);
            counter--;
        }
        System.out.println("Blastoff!");

        counter = 10;

        do {
            System.out.println(counter);
            counter--;
        } while (counter > 0);
        System.out.println("Blastoff!!");

        for (int timer = 10; timer > 0; timer--) {
            System.out.println(timer);
        }
        System.out.println("Blastoff!!!");
    }

    public static void doWhileLoop() {

        int lives = 9;
        
        do {
            Game.playGame();
            System.out.println("You died! You have " + lives + " left. Try again.");
            lives--;
        } while (lives >= 0);
        
        lives = 9;
        
        while (lives >= 0) {
            Game.playGame();
            System.out.println("You died! You have " + lives + " left. Try again.");
            lives--;
        }
        
        for (lives = 9; lives >= 0; lives --) {
            Game.playGame();
            System.out.println("You died! You have " + lives + " left. Try again.");
            lives--;
        }
    }

    public static void forLoop() {

        for (int j = 0; j <= 20; j++) {
            System.out.print("-|");
        }

        int j = 0;

        do {
            System.out.print("-|-");
            j++;
        } while (j <= 20);

        j = 0;

        while (j <= 20) {
            System.out.print("-|-");
            j++;
        }
    }
}
