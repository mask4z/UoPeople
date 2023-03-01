package ren.cs1102.Entities.ProgrammingAssignment;

import javax.swing.JOptionPane;

public class TrueFalseQuestion extends Question {

    @Override
    public String ask() {

        boolean validAnswer = false;
        String answer = null;

        while (!validAnswer) {

            answer = JOptionPane.showInputDialog(question);
            answer = answer.toUpperCase();

            if (answer.equals("F") || answer.equals("FALSE")
                    || answer.equals("N") || answer.equals("NO")
                    || answer.equals("T") || answer.equals("TRUE")
                    || answer.equals("Y") || answer.equals("YES")) {
                validAnswer = true;
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid answer. Please enter TRUE or FALSE.");
            }
        }
        return answer;
    }

    public TrueFalseQuestion(String question, String answer) {
        this.question = "TRUE or FALSE: " + question;
        answer = answer.toUpperCase();

        if (answer.equals("F") || answer.equals("FALSE")
                || answer.equals("N") || answer.equals("NO")
                || answer.equals("T") || answer.equals("TRUE")
                || answer.equals("Y") || answer.equals("YES")) {

            if (answer.equals("T") || answer.equals("TRUE")
                    || answer.equals("Y") || answer.equals("YES")) {
                this.correctAnswer = "TRUE";
            } else {
                this.correctAnswer = "FALSE";
            }
        } else {
            System.out.println("Please supply a TRUE or FALSE as an answer when you construct TrueFalseQuestion objects");
        }
    }
}
