package Test;

public class AdditionProblem {

    public static void main(String[] args) {
        AdditionProblem problem = new AdditionProblem();
        System.out.println(problem.getProblem());
        System.out.println("What is the solution?");
        Integer userAnswer = TextIO.getlnInt();
        System.out.println(userAnswer.equals(problem.getAnswer())
                ? "Correct!"
                : ("Incorrect! The correct answer is "
                + problem.getAnswer()));
    }

    private int x, y, answer;

    public AdditionProblem() {
        x = (int) (10 + 40 * Math.random());
        y = (int) (30 * Math.random());
        answer = x + y;
    }

    public String getProblem() {
        return "Compute the sum: " + x + " + " + y;
    }

    public int getAnswer() {
        return answer;
    }
}
