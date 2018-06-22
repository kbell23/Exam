import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExamTester {

	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println("Please enter a file name: ");
		String fileName = ScannerFactory.getKeyboardScanner().nextLine();
		
		File newfile = new File(fileName);
		Scanner scan = null;
		try {
			scan = new Scanner(newfile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Exam exam1 = new Exam(scan); 
		exam1.print();			
		
		exam1.reorderQuestions();
		exam1.reorderMCAnswers(-1);
		
		exam1.print();

		PrintWriter pw = null;
		FileWriter emptyfile;
		try {
			emptyfile = new FileWriter("emptyexam.txt");
			pw = new PrintWriter(emptyfile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		exam1.save(pw);
		pw.close();
		
		exam1.getAnswerFromStudent(-1);			// Getting all the answer students
		PrintWriter sa = null;
		FileWriter safile;
		try {
			safile = new FileWriter("student.txt");
			sa = new PrintWriter(safile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		exam1.saveStudentAnswers(sa);					// Saving the student answers with student.txt
		sa.close();
		
		File xfile = new File("emptyexam.txt");			// Loading the exam into this file
		Scanner scan2 = new Scanner(xfile);
		Exam exam2 = new Exam(scan2);
		scan2.close();
		
		File studentFile = new File("student.txt");			// Loading the student.txt to restore answers
		Scanner scan3 = new Scanner(studentFile);
		exam2.restoreStudentAnswers(scan3);		
		scan3.close();
		
		exam2.reportQuestionValues();					// Printing out the values for the questions 
		
		System.out.println("Exam Grade: " + exam2.getValue());		// Printing out the exam grade
	}

}
