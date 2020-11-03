import java.sql.*;

public class TestProducts {
    public static void main(String[] args) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "root", "");
                Statement stmt = conn.createStatement()
        ) {
            String displayAll = "Select * From product";
            String displayName = "Select name From product";
            String searchName = "Select * from product " +
                    "where name LIKE '%Iphone 5%'";
            System.out.printf("The SQL Statement is: %s, \n%s, \n%s\n", displayAll, displayName, searchName);

            ResultSet rsetAll = stmt.executeQuery(displayAll);
            System.out.println("The records for displaying all products are: ");
            int rowCount = 0;
            while (rsetAll.next()) {
                int id = rsetAll.getInt("id");
                String name = rsetAll.getString("name");
                float price = rsetAll.getFloat("price");
                ++rowCount;
                System.out.println(id + ", " + name + ", " + price);
            }
            System.out.println("Total number of records displaying all = " + rowCount);
            System.out.println();

            System.out.println("The records for displaying names of products are: ");
            ResultSet rsetName1 = stmt.executeQuery(displayName);
            rowCount = 0;
            while (rsetName1.next()) {
                String name = rsetName1.getString("name");
                ++rowCount;
                System.out.println(name);
            }
            System.out.println("Total number of records displaying names = " + rowCount);
            System.out.println();

            System.out.println("The records for displaying names of iphone 5 are: ");
            ResultSet rsetName2 = stmt.executeQuery(searchName);
            rowCount = 0;
            while (rsetName2.next()) {
                int id = rsetName2.getInt("id");
                String name = rsetName2.getString("name");
                float price = rsetName2.getFloat("price");
                ++rowCount;
                System.out.println(id + ", " + name + ", " + price);
            }
            System.out.println("Total number of records displaying iphone 5 = " + rowCount);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
