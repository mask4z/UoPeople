package ren.cs1102.Entities;

public class Unit2Switch {

    public static void main(String[] args) {
        newSwitch();

//        String computerMove;
//
//        switch ( (int)(3*Math.random())) {
//            case 0:
//                computerMove = "Rock";
//                break;
//            case 1:
//                computerMove = "Paper";
//                break;
//            default:
//                computerMove = "Scissor";
//                break;
//        }
//            System.out.println("The computer's move was " + computerMove);
    }

    public static void newSwitch() {

        int N = 2;

        switch (N) {
            case 1 -> {
                System.out.println("The number is 1!");
            }
            default -> {
                System.out.println("The number is not 1!");
            }
        }
    }
}
