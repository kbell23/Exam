import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Exam {
	private String title;
	private ArrayList<Question> questions;

									
	Exam(String title) {					// Exam constructor
		this.title = title;
		questions = new ArrayList<Question>();
	}
	
	Exam(Scanner scan) {
		questions = new ArrayList<Question>();
		this.title = scan.nextLine();
		scan.nextLine();
		while(scan.hasNextLine()) {
			String q = scan.nextLine();
			if (q.equals("MCMAQuestion")) {
				addQuestion(new MCMAQuestion(scan));
			}
			else if (q.equals("MCSAQuestion")) {
				addQuestion(new MCSAQuestion(scan));
			}
			else if (q.equals("SAQuestion")) {
				addQuestion(new SAQuestion(scan));
			}
			else if (q.equals("NumQuestion")) {	
				addQuestion(new NumQuestion(scan));
			}
		}
	}
	
	
	public void print() {				// Printing out the Exam and it's contents
		System.out.println(title);
		System.out.println();
		int i = 1;
		for (Question q: questions) {
			System.out.print(i + ". ");
			q.print();
			System.out.println();
			i++;
		}
	}

	public void studentPrint(PrintWriter pw) {
		pw.println(title);
		pw.println();
		int i = 1;
		for (Question q: questions) {
			pw.print(i + ". ");
			q.studentPrint(pw);
			pw.println();
			i++;
		}
	}




	
	
	public void addQuestion(Question q) {		// Adding questions to the exam
		questions.add(q);
	}

	public void removeQuestion(int position){
		questions.remove(position-1);
		return;
	}
	
									
	public void reorderQuestions() {			// Reordering the questions
		Collections.shuffle(questions);
	}
	
	
	public void reorderMCAnswers(int position) {				
		if (position < 0) { //reorder all the MCAnswers
			int i = 0;
			for (Question q: questions) {
				 if (q instanceof MCQuestion) {					// Only reorder if it is an MCAnswer
					 
					 ((MCQuestion)questions.get(i)).reorderAnswers();
				 }
				 i++;
			}
		}
		
		else {
			
			((MCQuestion) questions.get(position-1)).reorderAnswers();
		}
	}
	
	
	public void getAnswerFromStudent(int position) {
		if (position < 0) { 				//Getting all answers from student if negative
			int i = 1;
			for (Question q: questions) {
				System.out.print("Question " + i + ":");
				q.getAnswerFromStudent();
				i++;
			}
			
		}
		else {
			questions.get(position-1).getAnswerFromStudent();			//if position is unsigned, just get answer from student for that question
		}
	}
	
	public double getValue() {
		double value = 0;					//Going through all the questions and getting the values
		for (Question q: questions) {
			value = value + q.getValue();
		}
		return value;
	}
	
	public void reportQuestionValues() {						// Reporting each question value 
		int i = 1;
		for (Question q: questions) {
			System.out.println("Question " + i + ":" + q.getValue() );
			i++;
		}
	}

	public double reportQuestionValue(int pos){
		return questions.get(pos).getValue();

	}
	
	public void save(PrintWriter pw) {
		pw.println(title);

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		pw.println(sdf.format(date));



		for(Question q: questions) {
			q.save(pw);
		}
	}
	
	public void saveStudentAnswers(PrintWriter pw) {
		System.out.println("Please enter your name");
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		pw.println(name);
		System.out.println("Please enter the exam file name");
		String fileName = scan.nextLine();
		pw.println(fileName);

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		pw.println(sdf.format(date));

		for(Question q: questions) {
			q.saveStudentAnswers(pw);
			pw.println();
		}
	}
	
	public void restoreStudentAnswers(Scanner scan) {
		scan.nextLine();
		for(Question q: questions) {
			q.restoreStudentAnswers(scan);
			scan.nextLine();
		}
	}
	
	public int getNumQuestions(){
		return questions.size();
	}

	public void printQuestion(int pos){
		questions.get(pos-1).print();
	}
}
