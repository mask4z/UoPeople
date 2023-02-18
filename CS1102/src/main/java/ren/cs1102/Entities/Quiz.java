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
    
    static int nQuestions = 0;
    static int nCorrect = 0;

    public static void main(String[] args) {

        String question1 = "What is the highest mountain above sea level?\n";
        question1 += "A. Kangchenjunga\n";
        question1 += "B. Mount Elbrus\n";
        question1 += "C. Mount Everest\n";
        question1 += "D. Mount Kilimanjaro\n";
        question1 += "E. K2\n";
        String answer1 = "C. Mount Everest";

        String question2 = "Other than in programming, what else is java known as?\n";
        question2 += "A. Coffee.\n";
        question2 += "B. Mud.\n";
        question2 += "C. A building.\n";
        question2 += "D. Grass.\n";
        question2 += "E. A computer.\n";
        String answer2 = "A. Coffee.";

        String question3 = "What is the speed of light?\n";
        question3 += "A. 300 000m/s.\n";
        question3 += "B. 3 000 000m/s.\n";
        question3 += "C. 300 000 000m/s.\n";
        question3 += "D. 300m/s.\n";
        question3 += "E. 300 000 000km/s.\n";
        String answer3 = "C. 300 000 000m/s";
        
        check(question1, answer1);
        check(question2, answer2);
        check(question3, answer3);
        JOptionPane.showMessageDialog(null, nCorrect + " correct out of " + nQuestions + " questions.");
    }

    static String ask(String question) {

        boolean validAnswer = false;
        String answer = null;

        while (!validAnswer) {

            answer = JOptionPane.showInputDialog(question);
            answer = answer.toUpperCase();

            if (answer.matches("[A-E]")) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid answer. Please enter A, B, C, D, or E.");
            }
        }

        return answer;
    }
    
    static void check(String question, String correctAnswer) {
        
        String answer = ask(question);
        
        if (correctAnswer.startsWith(answer)) {
            JOptionPane.showMessageDialog(null, "Correct!");
            nCorrect++;
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect. The correct answer is " + correctAnswer);
        }
        nQuestions++;
        
    }
}
