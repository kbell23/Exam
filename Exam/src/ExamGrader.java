import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExamGrader {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = ScannerFactory.getKeyboardScanner();
        Exam exam1 = null;
        File examFile;
        Scanner examScanner;
        String studentName;
        int qChoice = 0;
        System.out.println("Please enter a exam file name or enter skip to continue: ");
        String fileName = scan.nextLine();
        if (!fileName.equals("skip")){
            examFile = new File(fileName);
            examScanner = new Scanner(examFile);
            exam1 = new Exam(examScanner);
        }

        System.out.println("Please enter a file name for the Student Answers");
        String studentFileName = scan.nextLine();
        File studentFile = new File(studentFileName);
        Scanner studentScanner = new Scanner(studentFile);
        studentName = studentScanner.nextLine(); //getting student name
        String studentExamFile = studentScanner.nextLine();

        while(!studentExamFile.equals(fileName)) { // if student exam file is not the same as exam file
            if (fileName.equals("skip")){
                examFile = new File(studentExamFile);
                examScanner = new Scanner(examFile);
                exam1 = new Exam(examScanner);
                examScanner.close();
                break;
            }
            System.out.println("Please insert a student file with matching exam file");
            studentFileName = scan.nextLine();
            studentFile = new File(studentFileName);
            studentScanner = new Scanner(studentFile);
            studentName = studentScanner.nextLine(); //Skipping the name of the student
            studentExamFile = studentScanner.nextLine();
        }
        exam1.restoreStudentAnswers(studentScanner);

        exam1.reportQuestionValues();
        System.out.println(exam1.getValue());

        PrintWriter pw = new PrintWriter(new File("StudentScores.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("Student Name");
        sb.append(',');
        sb.append("Exam Score");
        sb.append(',');
        for (int i = 1; i< (exam1.getNumQuestions() + 1);i++){
            sb.append("Q" + i);
            sb.append(',');
        }

        sb.append('\n');
        sb.append(studentName);
        sb.append(',');
        sb.append(exam1.getValue());
        sb.append(',');
        for (int i = 0; i<exam1.getNumQuestions();i++){
            sb.append(exam1.reportQuestionValue(i));
            sb.append(',');
        }
        pw.write(sb.toString());
        pw.close();
    }
}
