import java.sql.*;

public class TestAtomicTransaction {
    public static void main(String[] args) throws SQLException {
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore", "root", "");
                Statement st = con.createStatement()
        ) {
            try {
                con.setAutoCommit(false);
                String strQuery = "select qty, BookId from books where BookId in (1, 2);";
                System.out.println();
                ResultSet rs = st.executeQuery(strQuery);
                System.out.println("-- Before UPDATE --");
                while (rs.next()) {
                    System.out.println(rs.getInt("BookId") + ", " + rs.getInt("qty"));
                }
                con.commit(); // Commit Select

                // 2 UPDATES :
                st.executeUpdate("update books set qty = qty + 1 where BookId = 1");
                st.executeUpdate("update books set qty = qty + 1 where BookId = 2");
                con.commit(); // Commit Updates

                rs = st.executeQuery("select BookId, qty from books where BookId in (1, 2)");
                System.out.println("-- Ater UPDATE and commit --");
                while (rs.next()) {
                    System.out.println(rs.getInt("BookId") + ", " + rs.getInt("qty"));
                }

                con.commit(); // Commit SELECT

                st.executeUpdate("update books set qty = qty - 99 where BookId = 1");
                st.executeUpdate("update books set qty = qty - 99 where BookId = 2");
                con.rollback();

                rs = st.executeQuery("select BookId, qty from books where BookId in (1, 2)");
                System.out.println("-- After UPDATE and Rollback --");
                while (rs.next()) {
                    System.out.println(rs.getInt("BookId") + ", " + rs.getInt("qty"));
                }

            } catch (SQLException ex){
                System.out.println("-- Rolling bak changes --");
                con.rollback();
                ex.printStackTrace();
                System.exit(-1);
            }
        }
    }

}
