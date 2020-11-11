import java.sql.*;

public class TestBatch {
    public static void main(String[] args) throws SQLException{
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore", "root", "");
                Statement st = con.createStatement()
        ) {
            try {
                con.setAutoCommit(false);

                con.commit();
                st.executeUpdate("delete from books where BookId = 8004");
                st.executeUpdate("delete from books where BookId = 8005");
                st.executeUpdate("delete from books where BookId = 8006");
                st.addBatch("insert into books(BookId, Name, Price, status, year) values (8004, 'Java XYZ', 80.80, 1, 2020)");
                st.addBatch("insert into books(BookId, Name, Price, status, year) values (8005, 'Java ABC', 80.80, 1, 2020)");
                st.addBatch("insert into books(BookId, Name, Price, status, year) values (8006, 'Java aAWD', 80.80, 1, 2020)");

                int[] returnCodes = st.executeBatch();

                System.out.println("Return codes are: ");
                for (int code: returnCodes) {
                    System.out.print(code + ", ");
                }
                System.out.println();

                con.commit();
            } catch (SQLException ex) {
                System.out.println("-- Rolling back... --");
                con.rollback();
                ex.printStackTrace();
            }
        }
    }
}
