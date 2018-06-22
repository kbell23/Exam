import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.Scanner;

import static javafx.application.Platform.exit;

public class ExamBuilder {
    Scanner scan  = ScannerFactory.getKeyboardScanner();

    public static void main(String[] args) throws FileNotFoundException {
        int choice = 0;
        Scanner scan = new Scanner(System.in);
        Exam exam1 = null;
        boolean flag = true;
        while(flag == true) {
            System.out.println("Please make a choice");
            System.out.println("1. Load a saved Exam from a file.");
            System.out.println("2. Add a question");
            System.out.println("3. Remove a question");
            System.out.println("4. Reorder the questions");
            System.out.println("5. Reorder the answers");
            System.out.println("6. Print the exam to the screen");
            System.out.println("7. Print the exam to a file");
            System.out.println("8. Save the exam");
            System.out.println("9. Quit");
            choice = scan.nextInt();
            scan.nextLine();
            switch(choice) {
                case 1:
                    String filename = " ";
                    System.out.println("Please enter the file name of which file to load");
                    filename = scan.nextLine();
                    File examFile = new File(filename);
                    Scanner examScanner = new Scanner(examFile);
                    exam1 = new Exam(examScanner);
                    break;
                case 2:
                    if (exam1 == null){
                        exam1 = new Exam("Exam 1");
                    }
                    int qType;
                    boolean flag2 = true;
                    Scanner qScan = new Scanner(System.in);
                    while (flag2 == true) {
                        System.out.println("Enter corresponding number for the question you would like to add");
                        System.out.println("1. MCMA Question");
                        System.out.println("2. MCSA Question");
                        System.out.println("3. SA Question");
                        System.out.println("4. Num Question");
                        System.out.println("5. Exit");
                        qType = scan.nextInt();
                        scan.nextLine();

                        switch (qType) {

                            case 1:
                                System.out.println("1. Enter question value");
                                System.out.println("2. Enter question");
                                System.out.println("3. Enter base credit");
                                System.out.println("4. Enter the number of answers");
                                System.out.println("5. For each answer, enter its value then the answer");
                                MCMAQuestion q1 = new MCMAQuestion(qScan);
                                exam1.addQuestion(q1);
                                break;
                            case 2:
                                System.out.println("1. Enter question value");
                                System.out.println("2. Enter question");
                                System.out.println("3. Enter the number of answers");
                                System.out.println("4. For each answer, enter its value then the answer");
                                MCSAQuestion q2 = new MCSAQuestion(qScan);
                                exam1.addQuestion(q2);
                                break;
                            case 3:
                                System.out.println("1. Enter question value");
                                System.out.println("2. Enter question");
                                System.out.println("3. Enter the right answer");
                                SAQuestion q3 = new SAQuestion(qScan);
                                exam1.addQuestion(q3);
                                break;
                            case 4:
                                System.out.println("1. Enter question value");
                                System.out.println("2. Enter question");
                                System.out.println("3. Enter the right answer");
                                System.out.println("4. Enter the tolerance number");
                                NumQuestion q4 = new NumQuestion(qScan);
                                exam1.addQuestion(q4);
                                break;
                            case 5:
                                System.out.println("in case 5");
                                flag2 = false;break;
                            default:
                                System.out.println("Invalid Entry");
                                break;
                        }

                    }
                    break;
                case 3:
                    System.out.println("Enter which question number to remove");
                    int pos = scan.nextInt();
                    scan.nextLine();
                    exam1.removeQuestion(pos);
                    break;

                case 4:
                    exam1.reorderQuestions();
                    break;

                case 5:
                    System.out.println("Enter which question would you like to reorder or enter -1 for all");
                    int input = scan.nextInt();
                    scan.nextLine();
                    while (input > exam1.getNumQuestions() || input != -1){
                        System.out.println("Invalid entry ,try again");
                        input = scan.nextInt();
                        scan.nextLine();
                    }
                    exam1.reorderMCAnswers(input);
                    break;

                case 6:
                    exam1.print();break;

                case 7:
                    System.out.println("Please enter the file you would like to print to");
                    String saveFileName1 = scan.nextLine();
                    File saveFile1 = new File(saveFileName1);
                    PrintWriter pw1 = new PrintWriter(saveFile1);
                    exam1.studentPrint(pw1);
                    pw1.close();
                    break;

                case 8:
                    System.out.println("Please enter the file you would like to save to");
                    String saveFileName2 = scan.nextLine();
                    File saveFile2 = new File(saveFileName2);
                    PrintWriter pw2 = new PrintWriter(saveFile2);
                    exam1.save(pw2);
                    pw2.close();
                    break;

                case 9:
                    flag = false;
                    break;

            }

        }
    }

}
