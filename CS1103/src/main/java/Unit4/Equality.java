package Unit4;

import java.util.Random;

/**
 * This class just demonstrates how an equals() method would be practically
 * used.
 *
 * @author renos
 */
public class Equality {

    static class Student {
        private int studentId;

        public Student(int i) {
            this.studentId = i;
        }

        /**
         * The equals method is inherited from the class Object.
         */
        @Override
        public boolean equals(Object obj) {
            Student st = (Student) obj;
            return this.studentId == st.studentId;
        }
    }

    public static void main(String[] args) {

        // Construct two students of the same student ids.
        Student st1 = new Student(003365);
        Student st2 = new Student(003365);

        System.out.println("This equality check will return false as the students aren't in the same memory.");
        System.out.println("Student 1's id is " + st1.studentId + " and memory location is " + st1);
        System.out.println("Student 2's id is " + st2.studentId + " and memory location is " + st2);
        System.out.println(st1 == st2); // the expression passed as a parameter would've evaluated the same as Object.equals(Object obj) method.

        System.out.println("This equality check will return true as the student's ids " +
                "are the same even though they're in different memory locations.");
        System.out.println("Student 1's id is " + st1.studentId);
        System.out.println("Student 2's id is " + st2.studentId);
        System.out.println(st1.equals(st2)); // Uses the overridden equals method to check equality of student's ids.
    }
}
