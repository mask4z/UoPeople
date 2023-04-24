package ren.cs1102.Entities.ProgrammingAssignment;

import javax.swing.*;
import java.awt.*;

public class TrueFalseQuestion extends Question {

    public TrueFalseQuestion(String question, String answer) {
        super(question);

        JPanel buttons = new JPanel();
        addButton(buttons, "TRUE");
        addButton(buttons, "FALSE");

        this.question.add(buttons);
        initQuestionDialog();

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

    public void addButton(JPanel buttons, String label) {
        JButton button = new JButton(label);
        button.addActionListener(question);
        buttons.add(button);
    }
}
