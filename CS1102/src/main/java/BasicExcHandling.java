

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author renos
 */


public class BasicExcHandling {

    public static void main(String[] args) {
        January();
    }

    private static class Ex1 extends Exception {

        public Ex1() {
        }
    }

    private static class Ex2 extends Exception {

        public Ex2() {
        }
    }

    private static class Ex3 extends Exception {

        public Ex3() {
        }
    }

    private static class Ex4 extends Exception {

        public Ex4() {
        }
    }
     static void e() {
      // Might cause any of the following unchecked exceptions to be
      // thrown:
      // Ex1, Ex2, Ex3, Ex4
    }
   
    static void April() {
      try {
          e();
      } catch (Ex1 ex) {
          System.out.println("April caught Ex1");
      }
    }
   
    static void March() {
      try {
          April();
      } catch (Ex2 ex) {
          System.out.println("March caught Ex2");
          // now cause exception Ex1 to be thrown
      }
    }
   
    static void February() {
      try {
          March();
      } catch (Ex1 ex) {
          System.out.println("February caught Ex1");
      } catch (Ex3 ex) {
          System.out.println("February caught Ex3");
      }
    }
   
    static void January() {
      try {
          February();
      } catch (Ex4 ex) {
          System.out.println("January caught Ex4");
          // now cause exception Ex1 to be thrown
      } catch (Ex1 ex) {
          System.out.println("January caught Ex1");
      }
    }
   
}
