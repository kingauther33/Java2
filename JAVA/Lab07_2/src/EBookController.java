import java.util.Scanner;

public class EBookController {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Login users = new Login();
        BooksManagement books = new BooksManagement();
        CustomersManagement customers = new CustomersManagement();
        EBookController eBookController = new EBookController();
        int menuChoice;

        System.out.println("Welcome to E-Book Online ^.^");
        do {
            menuChoice = eBookController.menu();
            switch (menuChoice) {
                case 1:
                    do {
                        menuChoice = eBookController.menuUsers();
                        switch (menuChoice) {
                            case 1:
                                users.register();
                                break;
                            case 2:
                                users.login();
                                break;
                            case 8:
                                menuChoice = 8;
                                break;
                            case 9:
                                menuChoice = 9;
                                break;
                            default:
                                System.out.println("Invalid input... Please try again");
                        }
                    } while (menuChoice != 8 && menuChoice != 9);
                    break;
                case 2:
                    do {
                        menuChoice = eBookController.menuBooks();
                        switch (menuChoice) {
                            case 1:
                                books.add();
                                break;
                            case 2:
                                books.edit();
                                break;
                            case 3:
                                books.del();
                                break;
                            case 4:
                                books.showTop10();
                                break;
                            case 8:
                                menuChoice = 8;
                                break;
                            case 9:
                                menuChoice = 9;
                                break;
                            default:
                                System.out.println("Invalid input... Please try again");
                        }
                    } while (menuChoice != 8 && menuChoice != 9);
                    break;
                case 3:
                    do {
                        menuChoice = eBookController.menuCustomers();
                        switch (menuChoice) {
                            case 1:
                                customers.add();
                                break;
                            case 2:
                                customers.edit();
                                break;
                            case 3:
                                customers.del();
                                break;
                            case 4:
                                customers.showTop10();
                                break;
                            case 8:
                                menuChoice = 8;
                                break;
                            case 9:
                                menuChoice = 9;
                                break;
                            default:
                                System.out.println("Invalid input... Please try again");
                        }
                    } while (menuChoice != 9 && menuChoice != 8);
                    break;
                case 9:
                    menuChoice = 9;
                    break;
                default:
                    System.out.println("Invalid input... Please try again");
            }
        } while (menuChoice != 9);

    }

    public int menu() {
        int choice;
        System.out.println("=======================================");
        System.out.println("Welcome to Main Menu: ");
        System.out.println("1. Open Users Menu");
        System.out.println("2. Open Books Menu");
        System.out.println("3. Open Customers Menu");
        System.out.println("9. Exit Program");
        System.out.print("Please select your action: ");
        choice = Integer.parseInt(sc.nextLine());
        System.out.println();
        return choice;
    }

    public int menuUsers() {
        int choice;
        System.out.println("==========================");
        System.out.println("Welcome to User Menu: ");
        System.out.println("1. Register new user");
        System.out.println("2. Login");
        System.out.println("8. Comeback to main menu");
        System.out.println("9. Exit Program");
        System.out.print("Please select action: ");
        choice = Integer.parseInt(sc.nextLine());
        System.out.println();
        return choice;
    }

    public int menuBooks() {
        int choice;
        System.out.println("==========================");
        System.out.println("Welcome to Books Menu: ");
        System.out.println("1. Add book to the library");
        System.out.println("2. Edit book in the library");
        System.out.println("3. Delete book in the library");
        System.out.println("4. Show top 10 books");
        System.out.println("8. Comeback to main menu");
        System.out.println("9. Exit Program");
        System.out.print("Please select action: ");
        choice = Integer.parseInt(sc.nextLine());
        System.out.println();
        return choice;
    }

    public int menuCustomers() {
        int choice;
        System.out.println("===========================");
        System.out.println("Welcome to Customer Menu: ");
        System.out.println("1. Add customer to the library");
        System.out.println("2. Edit customer in the library");
        System.out.println("3. Delete customer in the library");
        System.out.println("4. Show top 10 customers");
        System.out.println("8. Comeback to main menu");
        System.out.println("9. Exit Program");
        System.out.print("Please select action: ");
        choice = Integer.parseInt(sc.nextLine());
        System.out.println();
        return choice;
    }
}
