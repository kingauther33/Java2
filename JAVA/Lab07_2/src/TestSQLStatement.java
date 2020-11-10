import java.sql.*;
import java.util.Scanner;

public class TestSQLStatement {
    private static Scanner sc = new Scanner(System.in);
    private static UserModel model;


    static void showEdit(Statement st, String strShowEdit) throws SQLException {
        ResultSet rs = st.executeQuery(strShowEdit);
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
    }

    public static void main(String[] args) {
        model = new UserModel();
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore", "root", "");
                Statement st = con.createStatement()
        ) {
            boolean again;
            do {
                System.out.print("Please enter username: ");
                model.setUserName(sc.nextLine());
                System.out.print("Please enter user password: ");
                model.setUserPassword(sc.nextLine());
                String strLogin = "select count(1) from users\n" +
                        "    where username = '" + model.getUserName() + "' and password = '" + model.getUserPassword() + "';";
                System.out.println("The SQL Statement is: " + strLogin);
                ResultSet rs = st.executeQuery(strLogin);

                rs.next();
                if (rs.getInt("count(1)") == 1) {
                    System.out.println("Login Successfully");
                    again = true;
                } else {
                    System.out.println("You have failed logging in, please try again :(");
                    again = false;
                }
                System.out.println();
            } while (!again);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
