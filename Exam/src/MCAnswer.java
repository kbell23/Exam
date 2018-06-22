import java.io.PrintWriter;
import java.util.Scanner;

public abstract class MCAnswer extends Answer {

	protected String text;
	protected double creditIfSelected;
	
	protected MCAnswer(String text, double creditIfSelected) {
		this.text = text;
		this.creditIfSelected = creditIfSelected;
		
	}
	
	public MCAnswer(Scanner scan) {
		if (scan.hasNextDouble()) {					//Changes 
			creditIfSelected = scan.nextDouble();
		}
		else {
			creditIfSelected = 0;
		}
		String a = scan.nextLine();
		this.text = a;
		//this.creditIfSelected = creditIfSelected;
		
	}
	
	@Override
	public void print() {					// Printing out the question
		System.out.print(text);
	}

	public void studentPrint(PrintWriter pw){pw.println(text);}
	
	public double getCredit(Answer rightAnswer) {
		if(this.text.trim().equalsIgnoreCase(((MCAnswer)rightAnswer).text.trim())) {		//From student Answer data file we are reading in strings
			return creditIfSelected;
		}	
		else {
			return 0.0;
		}
	}
	
	public void save(PrintWriter pw) {
		pw.println(creditIfSelected + " " + text);
	}
}