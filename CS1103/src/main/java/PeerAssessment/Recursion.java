public class Recursion {

  public static void main(String[] args) {}

  static int fibonacci(int n) {
    if ((n == 0) || (n == 1)) {
      return n;
    } else {
      return fibonacci(n - 1) + fibonacci(n - 2);
    }
  }

  static int factorial(int n) {
    if (n <= 1) {
      return 1;
    } else {
      return n * factorial(n - 1);
    }
  }
}
