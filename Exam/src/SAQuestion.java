import java.io.PrintWriter;
import java.util.Scanner;

public class SAQuestion extends Question {
	
	public SAQuestion(String text, double maxValue) {
		super(text, maxValue);
		
	}
	
	public SAQuestion(Scanner scan) {
		super(" ", 0.0);
		super.maxValue = scan.nextDouble();
		scan.nextLine();
		super.text = scan.nextLine();
		if(rightAnswer == null) {
			rightAnswer = new SAAnswer(scan);
		}
		else {
			studentAnswer = new SAAnswer(scan);
		}
	}
			
	public Answer getNewAnswer() {			// Getting the new answer
		SAAnswer a = new SAAnswer(" ");
		return a;
	}
	
	public Answer getNewAnswer(String text) {		// Getting the new answer given the string
		SAAnswer a = new SAAnswer(text);
		return a;
	}
	
	public void  getAnswerFromStudent() {		// Getting the answer from student
	
		Scanner scan = new Scanner(System.in);
		String x = scan.nextLine();
		SAAnswer ans = new SAAnswer(x);
		studentAnswer = ans;
				
	}
	
	public double getValue() {							      // Getting the value of each answer
		SAAnswer studentAnswer = (SAAnswer) this.studentAnswer;
		SAAnswer rightAnswer = (SAAnswer) this.rightAnswer;
		double max = studentAnswer.getCredit(rightAnswer) * maxValue;
 		return max;
		
	}
	public void save(PrintWriter pw) {
		if(studentAnswer == null) {
			pw.println("SAQuestion");
			pw.println(maxValue);
			pw.println(text);
			rightAnswer.save(pw);
			pw.println();
		}
		else {
			pw.println("SAAnswer");
			studentAnswer.save(pw);
			pw.println();
		}
	}
}
