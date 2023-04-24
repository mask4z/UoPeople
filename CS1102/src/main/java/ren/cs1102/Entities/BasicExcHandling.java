package ren.cs1102.Entities;

import lib.TextIO;

public class BasicExcHandling {

    public static void main(String[] args) {

        try {
            System.out.println("My name is " + getName());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getName() {
        String name = TextIO.getln();

        if (name == null) {
            throw new IllegalArgumentException("No name was supplied.");
        } else if (name.matches("0-9")) {
            throw new IllegalArgumentException("A number has been inputted, instead of a String.");
        }

        return name;
    }
}
