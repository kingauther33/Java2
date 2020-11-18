import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentView {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        ArrayList<StudentModel> studentModels = new ArrayList<>();
        StudentController studentController = new StudentController();
        int choice;
        System.out.println("Welcome to Student Management System ^^");
        do {
            System.out.println("==========================");
            System.out.println("Menu: ");
            System.out.println("1. Add new student records");
            System.out.println("2. Display student records");
            System.out.println("3. Save new records.");
            System.out.println("4. Exit the program");
            System.out.print("Please enter your action: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    studentController.add(studentModels);
                    break;
                case 2:
                    studentController.display(studentModels);
                    break;
                case 3:
                    studentController.save(studentModels);
                    break;
                case 4:
                    System.out.println("Exiting the program!!!");
                    choice = 4;
                    break;
                default:
                    System.out.println("Invalid input... Please try again :(");
            }
        } while (choice != 4);
    }
}
