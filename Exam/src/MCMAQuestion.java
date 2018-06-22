import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MCMAQuestion extends MCQuestion {		//extends MCQuestion

	protected ArrayList<Answer> studentAnswer; 
	public double baseCredit;
	
	
	public MCMAQuestion(String text, double maxValue, double baseCredit) {
		super(text,maxValue);
		this.baseCredit = baseCredit;
		studentAnswer = new ArrayList<>();
	}
	
	public MCMAQuestion(Scanner scan) {
		super(" ", 0.0);
		studentAnswer = new ArrayList<>();
		double x = scan.nextDouble();
		scan.nextLine();
		String z = scan.nextLine();
		super.text = z;
		super.maxValue = x;
		this.baseCredit = scan.nextDouble();
		int numOfAnswers = scan.nextInt();
		while ( numOfAnswers < 2 || numOfAnswers > 26) {
			System.out.println("Enter a value within range 2-26");
			numOfAnswers = scan.nextInt();
		}
		int i = 0;
		while ( i < numOfAnswers) {
			addAnswer(new MCMAAnswer(scan));
			i++;
		}
	}
	
	public Answer getNewAnswer() {
		MCMAAnswer a = new MCMAAnswer(" ", 0.0);
		return a;
	}
	
	public Answer getNewAnswer(String text, double creditIfSelected) {
		MCMAAnswer a = new MCMAAnswer(text,creditIfSelected);
		return a;
	}
	
	public void getAnswerFromStudent() {
		System.out.println("Please input how many answers you will select or 0 to skip question");
		Scanner scan = ScannerFactory.getKeyboardScanner();
		int number = scan.nextInt();
		if (number <= 0) {
			MCMAAnswer a = new MCMAAnswer("blank",0.0);
			studentAnswer.add(a);
			return;
		}
		if (studentAnswer.get(0) != null) {
			studentAnswer.remove(0);
		}
		int i = 0;
		System.out.println("Please enter your choices");
		while (i < number) {
			char x = scan.next().charAt(0);  
			x = Character.toUpperCase(x);
			int pos = x - 'A';
			studentAnswer.add(answers.get(pos));
			i++;
		}	
	}
	
	public double getValue() {			
		double credit = 0.0;
		for (Answer a: studentAnswer) {
			credit += super.getValue(a);
		}
		return credit + (baseCredit*maxValue);
	}
	
	public void save(PrintWriter pw) { 
		if(this.studentAnswer.isEmpty()) {
			pw.println("MCMAQuestion");
			pw.println(maxValue);
			pw.println(text);
			pw.println(baseCredit);
			pw.println(answers.size());
			for (Answer a: answers) {
				a.save(pw);
			}
			pw.println();
		}
		else {
			pw.println("MCMAAnswer");
			pw.println(studentAnswer.size());
			for (Answer a: studentAnswer) {
				a.save(pw);
			}
			pw.println();
		}
	}
	
	public void saveStudentAnswers(PrintWriter pw) {
		pw.println("MCMAAnswer");
		for (Answer a: studentAnswer) {
			((MCAnswer)a).save(pw);
		}
		
	}
	
	public void restoreStudentAnswers(Scanner scan) {
		String answerType = scan.nextLine();
		while( scan.hasNextDouble() ) {
			studentAnswer.add(new MCMAAnswer(scan));

		}
	}
 	
}
