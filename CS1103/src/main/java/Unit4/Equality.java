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
        private Integer studentId;

        public Student(int i) {
            this.studentId = i;
        }

        public Student() {

            Random random = new Random();

            this.studentId = random.nextInt();
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

        Student st1 = new Student();
        Student st2 = new Student(003365);

        System.out.println("This equality check will return false as the student's ids aren't the same.");
        System.out.println("Student 1's id is " + st1.studentId);
        System.out.println("Student 2's id is " + st2.studentId);
        System.out.println(st1.equals(st2));

        st1.studentId = st2.studentId;

        System.out.println("This equality check will return true as the student's ids are the same.");
        System.out.println("Student 1's id is " + st1.studentId);
        System.out.println("Student 2's id is " + st2.studentId);
        System.out.println(st1.equals(st2));


    }
}
