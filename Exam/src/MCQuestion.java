import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public abstract class MCQuestion extends Question  {

	protected ArrayList<MCAnswer> answers;

	public MCQuestion(String text, double maxValue) {
		super(text, maxValue);
		answers = new ArrayList<>();
	}
	
	public void print () {
		System.out.println(text);
		int i = 0;
		for (Answer a: answers) {
			char x = (char) ('A' + i);
			System.out.print(x + ". ");
			a.print();
			System.out.println();
			i++;
		}
	}

	public void studentPrint(PrintWriter pw){
		pw.println(text);
		int i = 0;
		for (Answer a: answers) {
			char x = (char) ('A' + i);
			pw.print(x + ". ");
			a.studentPrint(pw);
			i++;
		}
	}
	
	public void addAnswer(MCAnswer x) {		// Adding an answer to answers
		answers.add(x);
	}
	
						
	public void reorderAnswers() {		// Reordering all the answers
		Collections.shuffle(answers);
	}
	
	public double getValue(Answer ans) {
		double credit = 0.0;
		for (MCAnswer a : answers) {
			credit += a.getCredit(ans);
		}
		return credit * maxValue;
	}

	public void save(PrintWriter pw) {}	
}
