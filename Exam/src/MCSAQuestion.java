import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class MCSAQuestion extends MCQuestion {
	
	
	protected MCSAQuestion(String text, double maxValue) {	
		super(text, maxValue);
	
	}
	
	public MCSAQuestion(Scanner scan) {
		super(" ",0.0);
		double x = scan.nextDouble();
		scan.nextLine();
		String z = scan.nextLine();
		super.text = z;
		super.maxValue = x;
		int y = scan.nextInt();
		while ( y < 2 || y > 26) {
			System.out.println("Enter a value within range 2-26");
			y = scan.nextInt();
		}
		int numOfAnswers = y;
		int i = 0;
		while ( i < numOfAnswers) {
			addAnswer(new MCSAAnswer(scan));
			i++;
		}
	}

	public Answer getNewAnswer() {				// Getting new answer
		MCSAAnswer a = new MCSAAnswer(" ", 0);
		return a;
	}
	
	public Answer getNewAnswer(String text) {		// Getting new answer given the text
		MCSAAnswer a = new MCSAAnswer(text,0.0);
		return a;
	}
	
	public Answer getNewAnswer(String text, double creditIfSelected) {		// Getting new answer given string and value
		MCSAAnswer a = new MCSAAnswer(text, creditIfSelected);
		return a;
	}
	

	public void getAnswerFromStudent() {					// getting the answer from student
		Scanner scan = ScannerFactory.getKeyboardScanner();
		char x = scan.next().charAt(0);  
		x = Character.toUpperCase(x);
		if (x == 'S') {
			MCSAAnswer a = new MCSAAnswer("blank",0.0);
			this.studentAnswer = a;
			return;
		}
		int pos = x - 'A';
		MCSAAnswer a =  (MCSAAnswer) answers.get(pos);
		this.studentAnswer = a;
		
	}
	
	public double getValue() {

		return super.getValue(studentAnswer);
	}
	
	public void save(PrintWriter pw) {
		if (studentAnswer == null) {
			pw.println("MCSAQuestion");
			pw.println(maxValue);
			pw.println(text);
			pw.println(answers.size());
			for(Answer a: answers) {
				a.save(pw);
			}
			pw.println();
	    	}
		else {
			pw.println("MCSAAnswer");
			studentAnswer.save(pw);
			pw.println();
		}
	}
}
