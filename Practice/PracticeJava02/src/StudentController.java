import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentController {
    Scanner sc = new Scanner(System.in);

    public void add(ArrayList<StudentModel> arr) {
        System.out.println("---------------------------");
        String addNext;
        do {
            System.out.print("Please enter StudentID: ");
            String newID = sc.nextLine();
            System.out.print("Please enter Student Name: ");
            String newName = sc.nextLine();
            System.out.print("Please enter Address of Student: ");
            String newAddress = sc.nextLine();
            System.out.print("Please enter Phone number of student: ");
            String newPhone = sc.nextLine();

            // Truyen gia tri vao mang
            arr.add(new StudentModel(newID, newName, newAddress, newPhone));
            System.out.println("The student you just add is: " + arr.get(arr.size()-1));

            System.out.print("Do you want to add more students? (Y/N): ");
            addNext = sc.nextLine();
        } while (addNext.equalsIgnoreCase("y"));

    }

    public void display(ArrayList<StudentModel> arr) {
        System.out.println("---------------------------");
        System.out.println("All the student records in the collection is: ");
        String id = "StudentID";
        String name = "StudentName";
        String address = "Address";
        String phone = "Phone";
        System.out.printf("%-30s%-30s%-30s%-30s\n", id, name, address, phone);
        for (StudentModel studentModel: arr) {
            System.out.printf("%-30s%-30s%-30s%-30s\n", studentModel.getStudentID(), studentModel.getStudentName(),
                    studentModel.getAddress(), studentModel.getPhone());
        }
    }

    public void save(ArrayList<StudentModel> arr) throws SQLException{
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practicejava02", "root", "");
                Statement st = con.createStatement()
        ) { try {
                con.setAutoCommit(false);
                con.commit();
                int checkUpdate = 0;
                for (StudentModel studentModel: arr) {
                    String strUpdate = "insert into Student values ('" + studentModel.getStudentID() + "', '" +
                            studentModel.getStudentName() + "', '" + studentModel.getAddress() + "', '" +
                            studentModel.getPhone() + "')";
                    System.out.println("The SQL Insert Statement is: " + strUpdate);
                    if (st.executeUpdate(strUpdate) > 0) checkUpdate++;
                }
                con.commit();

                System.out.println("Total " + checkUpdate + " records are saved");

                System.out.println("Check inserted records:");
                String strSelect = "select * from student";
                ResultSet rs = st.executeQuery(strSelect);
                ResultSetMetaData rsMD = rs.getMetaData();

                int numCols = rsMD.getColumnCount();
                for (int i=1; i<=numCols; i++) {
                    System.out.printf("%-30s", rsMD.getColumnName(i));
                }
                System.out.println();

                while (rs.next()) {
                    for (int i=1; i<=numCols; i++) {
                        System.out.printf("%-30s", rs.getString(i));
                    }
                    System.out.println();
                }

                con.close();
                if (con.isClosed()) {
                    System.out.println("Connection closed.");
                }
            } catch (SQLException ex) {
                con.rollback();
                ex.printStackTrace();
                System.exit(-1);
            }
        }
    }
}
