package Unit4;

import java.util.Random;

/**
 * This class just demonstrates how an equals() method would be practically
 * used.
 * 
 * @author renos
 *
 */
public class Equality {

	static class Student{
		private Integer studentId;
		
		public Student(int i) {
			this.studentId = i;
		}
		
		public Student ( ) {
			
			Random random = new Random();
			
			this.studentId = random.nextInt();
		}
	
		/**
		 * The equals method is inherited from the class Object.
		 * 
		 */
		private boolean equals(Student st) {

			if (this.studentId == st.studentId) {
				return true;
			} else {
				return false;
			}
	}
		
	public static void main(String[] args) {
		
		Student st1 = new Student();
		Student st2 = new Student(003365);
		
		
	}
}
