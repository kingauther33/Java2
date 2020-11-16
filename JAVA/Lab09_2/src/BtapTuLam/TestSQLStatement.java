package BtapTuLam;

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
            String str = "select * from users where username = 'ilovemumu';";
            ResultSet rs = st.executeQuery(str);


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
