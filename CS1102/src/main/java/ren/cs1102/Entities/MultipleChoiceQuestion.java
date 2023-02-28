package ren.cs1102.Entities;

import javax.swing.JOptionPane;

public class MultipleChoiceQuestion {

    static int nQuestions = 0;
    static int nCorrect = 0;

    String question;
    String correctAnswer;

    public MultipleChoiceQuestion(String query, String a, String b, String c, String d, String e, String answer) {
        question = query + "\n";
        question += "A. " + a + "\n";
        question += "B. " + b + "\n";
        question += "C. " + c + "\n";
        question += "D. " + d + "\n";
        question += "E. " + e + "\n";
        correctAnswer = answer.toUpperCase();
    }

    String ask() {

        boolean validAnswer = false;
        String answer = null;

        while (!validAnswer) {

            answer = JOptionPane.showInputDialog(question);
            answer = answer.toUpperCase();

            if (answer.matches("[A-E]")) {
                validAnswer = true;
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid answer. Please enter A, B, C, D, or E.");
            }
        }

        return answer;
    }

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
