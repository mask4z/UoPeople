package ren.cs1102;

import lib.TextIO;

public class Interest {

    public static void main() {
        double principal;   //The value of the investment.
        double rate;    //The annual interest rate.

        System.out.print("Enter the investment amount: ");
        principal = TextIO.getlnDouble();

        System.out.println();
        System.out.println("Enter the annual interest rate.");
        System.out.print("Enter a decimal, not a percentage: ");
        rate = TextIO.getlnDouble();
        System.out.println();

        int years;

        years = 0;

        while (years < 5) {
            double interest; // Interest for this year.
            interest = principal * rate;
            principal = principal + interest; // Add it to principal.
            years = years + 1; // Count the current year.
            System.out.print("The value of the investment after ");
            System.out.print(years);
            System.out.print(" years is $");
            System.out.printf("%1.2f", principal);
            System.out.println();
        }
    }

}
