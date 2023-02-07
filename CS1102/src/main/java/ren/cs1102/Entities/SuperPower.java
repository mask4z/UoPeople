package ren.cs1102.Entities;

import javax.swing.JOptionPane;

/**
 * This program is intended to prompt you with a dialog that asks a question about your super power.
 * Then another dialog will prompt a message with the input received from the previous dialog.
 */
public class SuperPower {

    public static void main(String[] args) {
        
        String power = JOptionPane.showInputDialog("What is your super power?");
        power = power.toUpperCase();
        
        JOptionPane.showMessageDialog(null, power+" TO THE RESCUE!");

    }
}
