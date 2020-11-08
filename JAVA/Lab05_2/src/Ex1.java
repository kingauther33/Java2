import java.sql.*;

public class Ex1 {
    public static void main(String[] args) {
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookshop" , "root", "");
                Statement st = con.createStatement()
        ) {
            String sqlDelete = "delete from";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
