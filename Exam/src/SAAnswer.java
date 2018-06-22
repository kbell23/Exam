import java.io.PrintWriter;
import java.util.Scanner;

public class SAAnswer extends Answer {

	protected String text;
	

	protected SAAnswer(String text) {
		this.text = text;
	}
	
	public SAAnswer(Scanner scan) {
		String x = scan.nextLine();
		this.text = x;
	}
	
	public void print() {
		System.out.println(text);
	}

	public void studentPrint(PrintWriter pw){pw.println(text);}
	
	public double getCredit(Answer rightAnswer) {
		SAAnswer rightAnswer1 = (SAAnswer) rightAnswer;
		if (text.trim().equalsIgnoreCase(rightAnswer1.text.trim()) ) {
			return 1.0;
		}
		else {
			return 0.0;
		}
 	}
	
	public void save(PrintWriter pw) {
		pw.println(text);
	}

		
}
