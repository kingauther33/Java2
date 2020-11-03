import java.util.Scanner;

public class TestEBookStore {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu m1 = new Menu();
        m1.displayMenu();
        int choice;
        do {
            System.out.print("Choose your action (13 to return to menu): ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    m1.menu1();
                    break;
                default:
                    break;
            }
            System.out.println();
        } while (choice != 14);
    }
}
