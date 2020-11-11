import java.sql.*;

public class TestPreparedSt {
    public static void main(String[] args) throws SQLException{
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore", "root", "");
                PreparedStatement pst = con.prepareStatement("insert into books(BookId, " +
                        "Name, Price, status, year) values (?, ?, ?, ?, ?)");
                PreparedStatement pstSelect = con.prepareStatement("select * from books");
        ) {
            try {
                con.setAutoCommit(false);
                pst.setInt(1, 7009);
                pst.setString(2, "Mahhong 2");
                pst.setDouble(3, 88.88);
                pst.setInt(4, 1);
                pst.setInt(5, 2019);

                int rowsInserted = pst.executeUpdate(); // execute statement
                System.out.println(rowsInserted + " rows affected.");

                pst.setInt(1, 7008);
                pst.setString(2, "MahJong 102");

                rowsInserted = pst.executeUpdate();
                System.out.println(rowsInserted + " rows affected");

                ResultSet rs = pstSelect.executeQuery();
                ResultSetMetaData rsMD = rs.getMetaData();
                for (int i=1; i<=rsMD.getColumnCount(); i++) {
                    System.out.printf("%-30s", rsMD.getColumnName(i));
                }
                System.out.println();

                while (rs.next()) {
                    for (int i=1; i<=rsMD.getColumnCount(); i++) {
                        System.out.printf("%-30s", rs.getString(i));
                    }
                    System.out.println();
                }
            } catch (SQLException ex) {
                System.out.println("-- Rolling Back .... --");
                con.rollback();
                ex.printStackTrace();
            }
        }
    }
}
