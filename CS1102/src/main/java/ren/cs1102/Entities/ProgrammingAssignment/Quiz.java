/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ren.cs1102.Entities.ProgrammingAssignment;
/**
 *
 * @author renos
 */
public class Quiz {
    
    public static void main(String[] args) {

//        Question question1 = new MultipleChoiceQuestion("What fuel did the nuclear reactor use at the Chernobyl nuclear power plant?",
//                "Uranium dioxide",
//                "Mixed oxide",
//                "TRIGA",
//                "Actinide",
//                "Molten plutonium",
//                "a");
//
//        question1.check();
//
//        Question question2 = new MultipleChoiceQuestion("What is the highest mountain above sea level?",
//                "Kangchenjunga",
//                "Mount Elbrus",
//                "Mount Everest",
//                "Mount Kilimanjaro",
//                "K2",
//                "c");
//
//        question2.check();
//
//        Question question3 = new MultipleChoiceQuestion("Other than in programming, what else is java known as?",
//                "Coffee",
//                "Mud",
//                "A building",
//                "Grass",
//                "A computer",
//                "a");
//
//        question3.check();
//
//        Question question4 = new MultipleChoiceQuestion("What is the speed of light?",
//                "300 000m/s",
//                "3 000 000m/s",
//                "300 000 000m/s",
//                "300m/s",
//                "300 000 000km/s",
//                "c");
//
//        question4.check();
//
//        Question question5 = new MultipleChoiceQuestion("How many cards are there in a deck?",
//                "55",
//                "60",
//                "100",
//                "52",
//                "49",
//                "d");
//
//        question5.check();
//
//        Question question6 = new MultipleChoiceQuestion("How far is the moon from Earth?",
//                "384 400 m",
//                "400 000 km",
//                "384 400 km",
//                "285 655 km",
//                "150 759 km",
//                "c");
//
//        question6.check();
//        question6.showResults();

        Question question7 = new TrueFalseQuestion("Can you drink java?", "Y");
        question7.check();
        Question.showResults();

    }
}
