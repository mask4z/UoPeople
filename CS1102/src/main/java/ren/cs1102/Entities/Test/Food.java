/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ren.cs1102.Entities.Test;

/**
 *
 * @author renos
 */
public class Food {

    static int count;
    private String flavor = "sweet";

    Food() {
        count++;
    }

    void setFlavor(String s) {
        flavor = s;
    }

    String getFlavor() {
        return flavor;
    }

    static public void main(String[] args) {
        Food pepper = new Food();
        pepper.setFlavor("spicy");
        System.out.println(pepper.getFlavor());
    }
}
