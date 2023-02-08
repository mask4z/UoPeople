/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ren.cs1102.Entities;

import javax.swing.JOptionPane;

/**
 *
 * @author renos
 */
public class Quiz {

    public static void main(String[] args) {

        String question = "In programming, what does OOP stand for?\n";
        question += "A. Optic Oriented Programming.\n";
        question += "B. Object Oriented Projecting.\n";
        question += "C. Object Object Programming.\n";
        question += "D. Object Oriented Programming.\n";
        question += "E. Obvious Object Programming.\n";

        boolean correctAnswer = false;

        while (!correctAnswer) {

            String answer = JOptionPane.showInputDialog(question);
            answer = answer.toUpperCase();

            if (!answer.equals("D")) {
                if (answer.matches("[A-E]")) {
                    JOptionPane.showMessageDialog(null, "Incorrect. Please try again.");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid answer. Please enter A, B, C, D, or E.");
                }
            }
            if (answer.equals("D")) {
                correctAnswer = true;
                break;
            }
        }
        JOptionPane.showMessageDialog(null, "Correct!");
    }
}
