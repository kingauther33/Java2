package giftshop;

import java.util.Scanner;

public class GiftView {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        int choice;
        System.out.println("Welcome to GiftShop");
        System.out.println("Choose action as below");

            System.out.println("1. View gift from the store");
            System.out.println("2. Add new gift to the store");
            System.out.println("3. Delete gift from the store");
        do {
            System.out.print("Please enter the action you want to do: (4 to come back to main menu, 5 to exit the program");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("1. View gift from the store");
                    System.out.println("2. Add new gift to the store");
                    System.out.println("3. Delete gift from the store");
                    System.out.print("Please enter the action you want to do: (4 to come back to main menu, 5 to exit the program");
                    choice = Integer.parseInt(sc.nextLine());
                    break;
                default:
                    System.out.println("Wrong input!!!");
            }
        } while (choice == 5);

    }
}
