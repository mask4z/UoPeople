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
public class Unit3Parameter {

    public static void main(String[] args) {
        System.out.println("This program takes a parameter and multiplies with two.");
        System.out.println("Please enter a number: ");
        int actualParameter = TextIO.getlnInt();
        multiplyByTwo(actualParameter);
    }

    static void multiplyByTwo(int formalParameter) {
        int result = formalParameter * 2;
        
        System.out.println(formalParameter + " multiplied by 2 equals " + result);
    }
}
