package BtapChua.Controllers;

import BtapChua.Models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CartController {
    User userModel = new User();
    UsersController user = new UsersController();
    public static Scanner sc = new Scanner(System.in);
    String url = "jdbc:mysql://localhost:3306/ebookstore";
    String userDriver = "root";
    String passwordDriver = "";

    public void showBooks(){
        System.out.println("---------------------------");
        System.out.println("Below are the books in our stores");
        try (
                Connection con = DriverManager.getConnection(url, userDriver, passwordDriver);
                Statement st = con.createStatement()
        ) {
            String strSelect = "select BookId, Name, Price, year, qty from books";
            showEdit(st, strSelect);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addCarts(ArrayList<Books> myArr) {
        System.out.println("==========================");
        try (
                Connection con = DriverManager.getConnection(url, userDriver, passwordDriver);
                Statement st = con.createStatement()
        ) {
            String nextBuy;
            do {
                boolean checkBook;
                do {
                    System.out.print("Please enter the ID of the book you want to purchase: ");
                    int cartBookID =  Integer.parseInt(sc.nextLine());

                    System.out.print("Enter the number of the book you want to purchase: ");
                    int qtyBuy = Integer.parseInt(sc.nextLine());
                    System.out.println();

                    String strSelect = "select * from books where BookId = " + cartBookID;
                    ResultSet rs = st.executeQuery(strSelect);

                    rs.first();
                    if ((rs.getInt("BookId") == cartBookID) && (rs.getInt("qty") > qtyBuy && rs.getInt("status") != 3 )) {
                        myArr.add(new Books(rs.getInt("BookId"), rs.getString("Name"),
                                rs.getDouble("Price"), qtyBuy));

                        checkBook = true;
                    } else if (rs.getInt("BookId") != cartBookID) {
                        System.out.println("There is no such as BoookId like that in the store, please choose another book :( ");
                        checkBook = false;
                    } else {
                        System.out.println("The BookID you enter isn't available to purchase, please choose another book  :(");
                        checkBook = false;
                    }
                } while (!checkBook);

                System.out.println("My Carts:");
                for (Books str: myArr) {
                    System.out.println("Item number " + (myArr.indexOf(str) + 1) + ": " + str);
                }

                System.out.print("Do you want to continue purchasing stuff? (Y/N): ");
                nextBuy = sc.nextLine();
            } while (nextBuy.equalsIgnoreCase("Y"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void checkOut(ArrayList<Books> myArr){
        System.out.println("==========================");
        user.login(userModel);
        showPaymentMethod();
        System.out.println("----------------------------------");
        try (
                Connection con = DriverManager.getConnection(url, userDriver, passwordDriver);
                Statement st = con.createStatement()
        ) {
            try {
                con.setAutoCommit(false);
                con.commit();
                for (Books Books : myArr) {
                    String updateQty = "update books\n" +
                            "    set qty = qty - " + Books.getQty() + "\n" +
                            "    where BookId = " + Books.getBookID() + ";";
                    st.executeUpdate(updateQty);
                }

                int total = 0;
                for (Books Books : myArr) {
                    total += Books.getPrice();
                }

                String strSelectUser = "select * from users where username = '" + userModel.getUserName() + "';";
                ResultSet rsUser = st.executeQuery(strSelectUser);

                rsUser.first();
                userModel.setUserID(rsUser.getInt("id"));
                String strInsert = "insert into orders (CustomerID, status, total) " +
                        "values  (" + userModel.getUserID() + ", 1, " + total + ");";
                int numOrder = st.executeUpdate(strInsert, Statement.RETURN_GENERATED_KEYS);
                System.out.println(numOrder + " records updated on orders table.");

                ResultSet rsOrderID = st.getGeneratedKeys();
                int orderIDInserted = 0;
                if (rsOrderID.next()) {
                    orderIDInserted = rsOrderID.getInt(1);
                }

                int numOrderDetail = 0;
                for (Books Books : myArr) {
                    strInsert = "insert into ordersdetails(orderID, bookID, price, qty) values " +
                            "(" + orderIDInserted + ", " + Books.getBookID() + ", " +
                            Books.getPrice() + ", " + Books.getQty() + ")";
                    st.executeUpdate(strInsert);
                    numOrderDetail++;
                }
                System.out.println(numOrderDetail + " records updated on ordersDetail table.");
                con.commit();


            } catch (SQLException ex) {
                System.out.println("We don't have enough books for you to purchase ...");
                con.rollback();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void showPaymentMethod() {
        System.out.println("==========================");
        boolean checkPayment;
        do {
            System.out.print("Please enter your payment method (COD/VISA): ");
            String checkOutMethod = sc.nextLine();
            if (checkOutMethod.equalsIgnoreCase("cod") || checkOutMethod.equalsIgnoreCase("visa")) {
                System.out.println("Please enter your address for us to ship: ");
                String address = sc.nextLine(); // khong co address trong database -> bo qua
                checkPayment = true;
            } else {
                System.out.println("You enter wrong payment method, please try again...");
                checkPayment = false;
            }
        } while (!checkPayment);
    }

    public void showBills(ArrayList<Books> myArr) {
        System.out.println("==========================");
        System.out.println("The Bill of your Cart is as below");
        double total = 0;
        for (Books books: myArr) {
            System.out.printf( "%-30s%-30s\n", books.getName(), books.getPrice() * books.getQty());
            total += books.getPrice();
        }
        System.out.println("Total money need to pay: " + total);
    }

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
}
