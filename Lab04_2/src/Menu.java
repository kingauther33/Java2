import java.sql.*;

public class Menu {
    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%" + n + "s", s);
    }

    public void displayMenu() {
        System.out.println("Welcome to E-Book Online Program ^_^");
        System.out.println("Please choose what you want to do");
        System.out.println("1. Show top 10 newest books");
        System.out.println("2. Show top 10 most popular books");
        System.out.println("3. Find books by Category");
        System.out.println("4. Find books by Name");
        System.out.println("5. Show top 10 newest orders");
        System.out.println("5. Show orders by customer ID");
        System.out.println("6. Show order status by order ID");
        System.out.println("7. Show orders by order ID");
        System.out.println("8. Show orders waiting to be delivered");
        System.out.println("9. Show orders packed");
        System.out.println("10. Show orders delivering");
        System.out.println("11. Show successful delivered orders");
        System.out.println("12. Show cancelled orders");
        System.out.println("13. Back to menu");
        System.out.println("14. Exit from the Program");
        System.out.println();
    }

//    public void tryCatchSqlandShow(String strSelect) {
//        try (
//                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "root", "");
//                Statement stmt = conn.createStatement();
//        ) {
//            System.out.println("The SQL statement is: " + strSelect);
//            ResultSet rset = stmt.executeQuery(strSelect);
//            System.out.println("The records for displaying all products");
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/ebookstore", "root", "");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return con;
    }

    public void menu1() {
        Connection con = getConnection();
        Statement st;
        ResultSet rs;

        try {
            st = con.createStatement();
            rs = st.executeQuery("select * from books" +
                    " order by year desc limit 10;");
            System.out.println("The records for displaying top 10 newest books are: ");
            while (rs.next()) {
                int BookId = rs.getInt("BookId");
//                int CatID = rs.getInt("CatID");
                String Name = rs.getNString("Name");
//                float price = rs.getFloat("Price");
//                int status = rs.getInt("status");
                int year = rs.getInt("year");
//                String createdDate = rs.getTimestamp("createdDate").toString();
//                String updatedDate = rs.getTimestamp("updatedDate").toString();

                System.out.println(padRight(Integer.toString(BookId), 10) +
                        padRight(Name,50) + year);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
