import java.sql.*;

public class JdbcInsertTest {
    public static void main(String[] args) {
        try (
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore", "root", "");
            Statement st = con.createStatement()
        ) {
            // DELETE records with bookID >= 5 and bookID < 7
            String sqlDelete = "delete from books where bookID >= 5 and bookID < 100";
            System.out.println("The SQL statement is: " + sqlDelete);
            int countDeleted = st.executeUpdate(sqlDelete);
            System.out.println(countDeleted + " records deleted.\n");

            // INSERT a record
            String sqlInsert = "insert into books values (null, 1, 'The Dog Running', 120000, 1, 2019, default, default, 20)";
            System.out.println("The SQL statement is: " + sqlInsert);
            int countInserted = st.executeUpdate(sqlInsert);
            System.out.println(countInserted + " records inserted. \n");

            // INSERT multiple records
            sqlInsert = "insert into books values " +
                    "(null, 3, 'Gone Fishing 2', 120000, 2, 2019, default, default, 10), " +
                    "(null, 3, 'Gone Fishing 3', 200000, 1, 2020, default, default, 10);";
            System.out.println("The SQL statement is: " + sqlInsert);
            countInserted = st.executeUpdate(sqlInsert);
            System.out.println(countInserted + " records inserted.\n");

            // Issue a SELECT to check the changes
            String strSelect = "select * from books";
            System.out.println("The SQL statement is: " + strSelect);
            ResultSet rset = st.executeQuery(strSelect);
            ResultSetMetaData rsetMD = rset.getMetaData();
            int numColumns = rsetMD.getColumnCount();

            // Print column class names
            for (int i = 1; i <= numColumns; i++) {
                System.out.printf("%-30s", rset.getString(i));
            }
            System.out.println();

            // Print column contents for all the rows
            while (rset.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    System.out.printf("%-30s", rset.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
