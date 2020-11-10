import java.sql.*;
import java.util.Scanner;

public class TestSQLStatement {
    private BooksModel model = new BooksModel();
    private static Scanner sc = new Scanner(System.in);

    static void showEdit(Statement st, String strShowEdit) throws SQLException {
        ResultSet rs = st.executeQuery(strShowEdit);
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
    }

    public static void main(String[] args) {
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore", "root", "");
                Statement st = con.createStatement()
        ) {
            String strShowEdit = "select * from books";
            showEdit(st, strShowEdit);
            System.out.print("Please enter the id of the book you want to delete: ");
            int delID = Integer.parseInt(sc.nextLine());
            String strSelectAll = "select * from ordersdetails;";
            ResultSet rsAll = st.executeQuery(strSelectAll);
            rsAll.next();
            if (delID == rsAll.getInt("bookID")) {
                System.out.println("You cannot delete this book!!!");
            } else {
                String strDel = "delete from books\n" +
                        "    where bookID = " + delID + ";";
                System.out.println("The SQL Del Statement is: " + strDel);
                int numDels = st.executeUpdate(strDel);
                System.out.println(numDels + " records affected.");
                showEdit(st, strShowEdit);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
