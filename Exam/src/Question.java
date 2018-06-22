import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

abstract class Question {
	
	protected String text;
	protected Answer rightAnswer;
	protected Answer studentAnswer;
	protected double maxValue;
	
	protected Question (String text, double maxValue) {
		this.text = text;
		this.maxValue = maxValue;
		rightAnswer = null;
		studentAnswer = null;
	}
	
	public Question(Scanner scan) {
		
	}
	
	
	public void print () {
		System.out.println(text);
	}

	public void studentPrint(PrintWriter pw){ pw.println(text);}
	
	public void setRightAnswer(Answer a) {		// Setting the right answer
		rightAnswer = a;
	}
	
	public abstract Answer getNewAnswer();
	
	public abstract void getAnswerFromStudent();
 	
	public abstract double getValue();
	
	public abstract void save(PrintWriter pw);
	
	public void saveStudentAnswers(PrintWriter pw) {
		if (this instanceof MCSAQuestion) {
			pw.println("MCSAAnswer");
		}
		if (this instanceof SAQuestion) {
			pw.println("SAAnswer");
		}
		if (this instanceof NumQuestion){			//refactored step
			pw.println("NumAnswer");
		}
		studentAnswer.save(pw);
	}
	
	public void restoreStudentAnswers(Scanner scan) {
		String x = scan.nextLine();
		if (x.equals("SAAnswer")) {
			SAAnswer a = new SAAnswer(scan);
			studentAnswer = a;
		}
		else if (x.equals("NumAnswer")){				//refactored step
			NumAnswer a = new NumAnswer(scan);
			studentAnswer = a;
		}
		else {
			MCSAAnswer a = new MCSAAnswer(scan);
			studentAnswer = a;
		}
	}
	
	
	
	
	
	
	
}
