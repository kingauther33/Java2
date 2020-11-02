package Ex1;

import java.util.Scanner;

public class TestContactList {
    private static Scanner sc = new Scanner(System.in);
    private static ContactList contactList = new ContactList("0921390129059");

    public static void main(String[] args) {
        boolean quit = false;
        startPhone();
        printActions();
        while (!quit) {
            System.out.println("\nEnter actions: (6 to show available actions)");
            int action = Integer.parseInt(sc.nextLine());
            switch (action) {
                case 0:
                    System.out.println("\nShutting down...");
                    quit = true;
                    break;
                case 1:
                    contactList.printContact();
                    break;
                case 2:
                    addNewContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    removeContact();
                    break;
                case 5:
                    queryContact();
                    break;
                case 6:
                    printActions();
                    break;
            }
        }
    }

    public static void addNewContact() {
        System.out.print("Enter new contact name: ");
        String name = sc.nextLine();
        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();
        Contact newContact = Contact.createContact(name, phone);
        if (contactList.addNewContact(newContact)) {
            System.out.println("New contact added: name = " + name + ", phone = " + phone);
        } else {
            System.out.println("Cannot add, " + name + " already on file");
        }
    }

    private static void updateContact() {
        System.out.println("Enter existing contact name: ");
        String name = sc.nextLine();
        Contact existingContactRecord = contactList.queryContact(name);
        if (existingContactRecord == null) {
            System.out.println("Contact not found.");
            return;
        }
        System.out.print("Enter new contact name: ");
        String newName = sc.nextLine();
        System.out.println("Enter new phone number: ");
        String newPhoneNumber = sc.nextLine();
        Contact newContact = Contact.createContact(newName, newPhoneNumber);
        if (contactList.updateContact(existingContactRecord, newContact)) {
            System.out.println("Successfully updated record");
        } else {
            System.out.println("Error updating record....");
        }
    }

    private static void removeContact() {
        System.out.println("Enter existing contact name: ");
        String name = sc.nextLine();
        Contact existingContactRecord = contactList.queryContact(name);
        if (existingContactRecord == null) {
            System.out.println("Contact not found.");
            return;
        }

        if (contactList.removeContact(existingContactRecord)) {
            System.out.println("Successfully removed record");
        } else {
            System.out.println("Error removing record....");
        }
    }

    public static void queryContact() {
        System.out.println("Enter existing contact name: ");
        String name = sc.nextLine();
        Contact existingContactRecord = contactList.queryContact(name);
        if (existingContactRecord == null) {
            System.out.println("Contact not found.");
            return;
        }
        System.out.println("Name: " + existingContactRecord.getName() + " phone number is " +
                existingContactRecord.getPhoneNumber());
    }

    private static void startPhone() {
        System.out.println("Starting Phone...");
    }

    private static void printActions() {
        System.out.println("\nAvailable actions:\npress");
        System.out.println("0 - to shutdown\n" +
                "1 - to print contacts\n" +
                "2 - to add a new contact\n" +
                "3 - to update existing an existing contact\n" +
                "4 - to remove an existing contact\n" +
                "5 - query if an existing contact exists\n" +
                "6 - to print a list of available actions.");
        System.out.println("Choose your action: ");
    }
}
