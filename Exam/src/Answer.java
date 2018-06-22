import java.io.PrintWriter;
import java.util.Scanner;


abstract class Answer {

	protected Answer() {}
	
	public Answer(Scanner scan) {}
	
	public abstract void print();

	public abstract void studentPrint(PrintWriter pw);

	public abstract double getCredit(Answer rightAnswer);
	
	public abstract void save(PrintWriter pw);
}
