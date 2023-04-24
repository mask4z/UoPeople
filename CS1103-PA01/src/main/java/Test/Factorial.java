package Test;

public class Factorial {

    public static double factorial(int n) {
        if (n < 0) return -1;
        if (n < 2) return 1;

        return n * factorial(n-1);
    }

    public static double iterativeFactorial(int n) {
        if (n < 0) return -1;
        int factorial = 1;

        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(factorial(80));
        long runTime = System.currentTimeMillis() - startTime;
        System.out.println(runTime);

        long startTime1 = System.currentTimeMillis();
        System.out.println(iterativeFactorial(100));
        long runTime1 = System.currentTimeMillis() - startTime1;
        System.out.println(runTime1);

    }
}
