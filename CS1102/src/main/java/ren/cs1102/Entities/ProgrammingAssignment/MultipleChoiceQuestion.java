package ren.cs1102.Entities.ProgrammingAssignment;

import javax.swing.JOptionPane;

public class MultipleChoiceQuestion extends Question {

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
}
