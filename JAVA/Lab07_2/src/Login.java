import java.sql.*;
import java.util.Scanner;

public class Login {
    Scanner sc = new Scanner(System.in);
    public void register() {
        System.out.print("Please enter user username: ");
        String userName = sc.nextLine();
        System.out.print("Please enter user password: ");
        String password = sc.nextLine();
        System.out.print("Enter role of User: ");
        int role = Integer.parseInt(sc.nextLine());
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore", "root", "");
                Statement st = con.createStatement()
        ) {
            String strInsert = "insert into users values (null, " + userName + ", " + password + ", " + role + ")";
            System.out.println("The SQL Statement is: " + strInsert);
            int numUpdateCount = st.executeUpdate(strInsert);
            System.out.println(numUpdateCount + " records inserted");

            String strCheckInsert = "select * from users";
            System.out.println("The SQL statement is: " + strCheckInsert);
            ResultSet rs = st.executeQuery(strCheckInsert);
            ResultSetMetaData rsMD = rs.getMetaData();

            int numCols = rsMD.getColumnCount();
            rs.next();
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
