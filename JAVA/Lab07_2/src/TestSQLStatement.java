import java.sql.*;
import java.util.Scanner;

public class TestSQLStatement {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore", "root", "");
                Statement st = con.createStatement()
        ) {
            boolean again = true;
            do {
                System.out.println("Please enter your username: ");
                String userName = sc.nextLine();
                System.out.println("Please enter user password: ");
                String password = sc.nextLine();
                String strLogin = "select count(1) from users\n" +
                        "    where username = '" + userName + "' and password = '" + password + "';";
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
