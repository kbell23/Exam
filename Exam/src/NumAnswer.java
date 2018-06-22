import java.io.PrintWriter;
import java.util.Scanner;

public class NumAnswer extends Answer {
    private double value;

    public NumAnswer(double value){
        this.value = value;
    }

    public NumAnswer(Scanner a){
        value = a.nextDouble();
        a.nextLine();
    }

    public void print(){
        System.out.println(value);
    }

    public void studentPrint(PrintWriter pw){
        pw.println(value);
    }

    public double getCredit(Answer rightAnswer){
        return value;
    }

    public void save(PrintWriter pw){
        pw.println(value);
    }
}
