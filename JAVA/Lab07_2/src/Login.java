import java.sql.*;
import java.util.Scanner;

public class Login {
    private UserModel model;
    Scanner sc = new Scanner(System.in);

    public void register() {
        System.out.print("Please enter user username: ");
        model.setUserName(sc.nextLine());
        System.out.print("Please enter user password: ");
        model.setUserPassword(sc.nextLine());
        System.out.print("Enter role of User: ");
        int role = Integer.parseInt(sc.nextLine());
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore", "root", "");
                Statement st = con.createStatement()
        ) {
            String strInsert = "insert into users values (null, '" + model.getUserName() + "', '" + model.getUserPassword() + "'', " + role + ")";
            System.out.println("The SQL Statement is: " + strInsert);
            int numUpdateCount = st.executeUpdate(strInsert);
            System.out.println(numUpdateCount + " records inserted");

            String strCheckInsert = "select * from users";
            System.out.println("The SQL statement is: " + strCheckInsert);
            BooksManagement.showEdit(st, strCheckInsert);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void login() {
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore", "root", "");
                Statement st = con.createStatement()
        ) {
            boolean again = true;
            do {
                System.out.print("Please enter user username: ");
                model.setUserName(sc.nextLine());
                System.out.print("Please enter user password: ");
                model.setUserPassword(sc.nextLine());
                String strLogin = "select count(1) from users\n" +
                        "    where username = '" + model.getUserName() + "' and password = '" + model.getUserPassword() + "';";
                System.out.println("The SQL Statement Is: " + strLogin);
                ResultSet rs = st.executeQuery(strLogin);
                ResultSetMetaData rsMD = rs.getMetaData();
                int numColumns = rsMD.getColumnCount();

                if (numColumns == 1) {
                    System.out.println("Login Successfully");
                    again = true;
                } else {
                    System.out.println("You have failed logging in, please try again :(");
                    again = false;
                }
            } while (!again);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
