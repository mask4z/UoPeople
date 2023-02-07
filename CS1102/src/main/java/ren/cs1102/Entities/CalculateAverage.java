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
public class CalculateAverage {
    
    public static void main(String[] args) {
        
        int inputNumber; // One of the integeres input by the user.
        int sum;         // The sum of the positive integers;
        int count;       //The number of positive integers.
        double average;  // The average of the positive integers.
        
        /*
        Initialization of variables
        */
        
        sum = 0;
        count = 0;
        
        /* Read and process the user's input. */
        
        System.out.print("Enter your first positive integer: ");
        inputNumber = TextIO.getlnInt();
        
        while (inputNumber != 0) {
            
            sum += inputNumber;
            count++;
            System.out.print("Enter your next positive integer: ");
            inputNumber = TextIO.getlnInt();
        }
        
        if (count == 0) {
            System.out.println("You didn't enter any data!");
        } else {
            average = ((double)sum) / count;
            System.out.println();
            System.out.println("You entered: " + count + " positive integers.");
            System.out.printf("Their average is %1.3f.\n", average);
        }
    }
}
