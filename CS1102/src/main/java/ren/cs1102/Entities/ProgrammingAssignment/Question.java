package ren.cs1102.Entities.ProgrammingAssignment;

import javax.swing.*;
import java.awt.*;

    public abstract class Question {

        static int nQuestions = 0;
        static int nCorrect = 0;

    QuestionDialog  question;
    String correctAnswer;

    public String ask() {

        question.setVisible(true);

        return question.answer;
    }

    Question(String question) {
        this.question = new QuestionDialog();
        this.question.setLayout(new GridLayout(0, 1));
        this.question.add(new JLabel("    " + question + "    ", JLabel.CENTER));
    }

    void initQuestionDialog() {
        question.setModal(true);
        question.pack();
        question.setLocationRelativeTo(null);
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
