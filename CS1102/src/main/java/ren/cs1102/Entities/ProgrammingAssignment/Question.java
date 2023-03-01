package ren.cs1102.Entities.ProgrammingAssignment;

import javax.swing.*;

public abstract class Question {

    static int nQuestions = 0;
    static int nCorrect = 0;

    String question;
    String correctAnswer;

    abstract String ask();

    void check() {

        String answer = ask();

        if (correctAnswer.startsWith(answer)) {
            JOptionPane.showMessageDialog(null, "Correct!");
            nCorrect++;
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect. The correct answer is " + correctAnswer);
        }
        nQuestions++;
    }

    static void showResults() {
        JOptionPane.showMessageDialog(null, nCorrect + " correct out of " + nQuestions + " questions.");
    }
}
