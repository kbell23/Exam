import java.io.PrintWriter;
import java.util.Scanner;

public class NumQuestion extends Question {
    private double tolerance;

    public NumQuestion(String text, double maxValue, double tolerance){
        super(text, maxValue);
        this.tolerance = tolerance;
    }

    public NumQuestion(Scanner scan){
        super(" ", 0.0);
        super.maxValue = scan.nextDouble();
        scan.nextLine();
        super.text = scan.nextLine();

        if (rightAnswer == null){
            rightAnswer = new NumAnswer(scan);
        }
        else
            studentAnswer = new NumAnswer(scan);

        this.tolerance = scan.nextDouble();
        if (scan.hasNextLine()) {
            scan.nextLine();
        }
    }

    public Answer getNewAnswer(){
        NumAnswer ans = new NumAnswer(0.0);
        studentAnswer = ans;
        return ans;
    }



    public void getAnswerFromStudent(){
        Scanner s = new Scanner(System.in);
        String maybeSkip = s.nextLine();
        maybeSkip = maybeSkip.toUpperCase();
        if (maybeSkip.contains("SKIP")) {       // Checking if it is skip, if its not put string into double
            NumAnswer randomNum = new NumAnswer(Double.POSITIVE_INFINITY);
            studentAnswer = randomNum;
            return;
        }
        double value = Double.parseDouble(maybeSkip);
        NumAnswer na = new NumAnswer(value);
        studentAnswer = na;

    }

    public double getValue(){
        double ans = studentAnswer.getCredit(null);
        double rightCredit = rightAnswer.getCredit(null);
        double checkVal = Math.abs(ans - rightCredit);
        if (checkVal <= tolerance){
            return maxValue;
        }
        else
            return 0.0;
    }

    public void save(PrintWriter pw){
        if(studentAnswer == null) {
            pw.println("NumQuestion");
            pw.println(maxValue);
            pw.println(text);
            rightAnswer.save(pw);
            pw.println(tolerance);
            pw.println();
        }
        else {
            pw.println("NumAnswer");
            studentAnswer.save(pw);
            pw.println();
        }
    }


}
