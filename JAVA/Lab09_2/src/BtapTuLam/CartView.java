package BtapTuLam;

import BtapChua.Controllers.CartController;
import BtapChua.Models.*;
import BtapChua.Controllers.*;

import java.util.ArrayList;
import java.util.Scanner;

public class CartView {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        ArrayList<Books> myArr = new ArrayList<>();
        BtapChua.Controllers.CartController cartController = new CartController();
        boolean nextChoice = true;

        System.out.println("Welcome to our E-Book Store ^.^");
        do {
            System.out.println("==========================");
            System.out.println("Menu: ");
            System.out.println("1. Show Books");
            System.out.println("2. Add to Cart");
            System.out.println("3. Checkout");
            System.out.println("4. Show Bills");
            System.out.println("5. Exit Program");
            System.out.print("Please choose your action: ");
            int cartChoice = Integer.parseInt(sc.nextLine());
            switch (cartChoice) {
                case 1:
                    cartController.showBooks();
                    break;
                case 2:
                    cartController.addCarts(myArr);
                    break;
                case 3:
                    cartController.checkOut(myArr);
                    break;
                case 4:
                    cartController.showBills(myArr);
                    nextChoice = false;
                    System.out.println("---------------------------\n");
                    System.out.println("Hope to see you soon! Goodbye ^_^");
                    break;
                case 5:
                    nextChoice = false;
                    break;
                default:
                    System.out.println("Wrong input, try again...");
            }
        } while (nextChoice);

    }
}
