import java.sql.*;

public class TestEBookStore {
    public static void main(String[] args) {
        try (
                Connection conn = DriverManager.getConnection("jbdc:mysql//localhost:3306/ebookstore", "root", "");
                Statement stmt = conn.createStatement();
        ) {
            String strSelectTop10 = "select * from books " +
                    "order by createdDate desc limit 2";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
