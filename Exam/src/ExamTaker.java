import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExamTaker {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = ScannerFactory.getKeyboardScanner();

        int qChoice = 0;
        System.out.println("Please enter a file name: ");
        String fileName = scan.nextLine();
        File examFile = new File(fileName);
        Scanner examScanner = new Scanner(examFile);
        Exam exam1 = new Exam(examScanner);

        exam1.print();
        System.out.println("Enter skip to skip a question");
        exam1.getAnswerFromStudent(-1);
        System.out.println("Would you like to enter a question to revisit? Enter yes or no");
        scan.nextLine();    // Reading in the empty space

        String flag = scan.nextLine();
        flag = flag.toLowerCase();

        while(flag.contains("y")) {
            System.out.println("Enter the question number, or press 0 to continue");
            qChoice = scan.nextInt();
            scan.nextLine();
            if(qChoice > exam1.getNumQuestions() || qChoice < -1) {
                continue;
            }
            if (qChoice == 0 ) {
                flag = "no";
                continue;
            }
            exam1.printQuestion(qChoice);
            exam1.getAnswerFromStudent(qChoice);
        }

        System.out.println("Please enter the filename to save the answers to");

        String studentFileName = scan.nextLine();
        File studentFile = new File(studentFileName);
        PrintWriter pw = new PrintWriter(studentFile);
        exam1.saveStudentAnswers(pw);
        pw.close();
    }
}
