package view;

import controller.FileFactory;
import model.Customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TestCustomerManagement {
    static ArrayList<Customer>cusList = new ArrayList<Customer>();

    //function menu
    public static void menu() {
        System.out.println("*******************************************");
        System.out.println("1. Add customer");
        System.out.println("2. Edit customer");
        System.out.println("3. Export customer list");
        System.out.println("4. Find customer");
        System.out.println("5. Sort customer");
        System.out.println("6. Save customers list");
        System.out.println("7. Read customer list");
        System.out.println("8. List customers by network provider");
        System.out.println("9. Delete customer");
        System.out.println("10. Exit");
        System.out.println("*******************************************");

        int select = 0;
        System.out.println("Please choose one function:");
        Scanner sc = new Scanner(System.in);
        select = sc.nextInt();
        switch (select){
            case 1:
                processAddCustomer();
                break;
            case 2:
                processEditCustomer();
                break;
            case 3:
                processExportCustomer();
                break;
            case 4:
                processFindCustomer();
                break;
            case 5:
                processSortCustomer();
                break;
            case 6:
                processSaveCusList();
                break;
            case 7:
                processReadCusList();
                break;
            case 8:
                processCountCustomerByNetwork();
                break;
            case 9:
                processDeleteCustomer();
                break;
            case 10:
                processExit();
                break;
            default:
                System.out.println("Please enter one valid number from 1 to 10!");
                break;
        }
    }

    //Add new customer
    private static void processAddCustomer() {
        Customer cus = new Customer();
        Scanner scanner = new Scanner(System.in);

        //enter customer's id
        int id;
        do {
            System.out.println("Enter customer's id:");
            String input = scanner.nextLine();
            try {
                id = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid ID (numeric).");
            }
        } while (true);

        //enter customer's name
        String name;
        do {
            System.out.println("Enter customer's name:");
            name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("Name cannot be empty. Please enter a valid name.");
            } else if (!name.matches("[a-zA-Z]+")) {
                System.out.println("Invalid input. Please enter letters only for the name.");
            } else {
                break;
            }
        } while (true);


        //enter customer's phone
        String phoneNum;
        do {
            System.out.println("Enter customer's phone number:");
            phoneNum = scanner.next();
            if (!phoneNum.matches("\\d{10}")) {
                System.out.println("Invalid phone number. Please enter a 10-digit numeric phone number.");
            } else {
                break;
            }
        } while (true);

        cus.setId(id);
        cus.setName(name);
        cus.setPhoneNumber(phoneNum);
        cusList.add(cus);
        System.out.println("Added customer successfully!");
    }

    //Edit customer by id
    private static void processEditCustomer() {
        System.out.println("Please enter the ID of the customer to edit:");
        int idToEdit = (new Scanner(System.in)).nextInt();

        boolean found = false;

        for (Customer cus : cusList) {
            if (cus.getId() == idToEdit) {
                System.out.println("Editing customer with ID: " + idToEdit);
                System.out.print("Enter new name: ");
                String newName = (new Scanner(System.in)).nextLine();
                System.out.print("Enter new phone number: ");
                String newPhoneNumber = (new Scanner(System.in)).nextLine();

                cus.setName(newName);
                cus.setPhoneNumber(newPhoneNumber);
                System.out.println("Customer information updated successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Customer with ID " + idToEdit + " not found.");
        }
    }

    //Export customer list to screen
    private static void processExportCustomer() {
        System.out.println("====================================");
        System.out.println("ID\tName\tPhone Number");
        for(Customer customer: cusList){
            System.out.println(customer);
        }
        System.out.println("====================================");
    }

    //Find customer by id
    private static void processFindCustomer() {
        System.out.println("Please enter the first 3 digits of phone number");
        String numFind = (new Scanner(System.in)).next();
        System.out.println("====================================");
        System.out.println("Here are the customers having phone number start with "+ numFind);
        System.out.println("ID\tName\tPhone Number");
        boolean found = false;
        for (Customer cus : cusList){
            if (cus.getPhoneNumber().startsWith(numFind)){
                System.out.println(cus);
                found = true;
            }
        }
        if(!found){
            System.out.println("No customers found.");
        }
        System.out.println("====================================");
    }

    //Sort customer by phone number
    private static void processSortCustomer() {
        Collections.sort(cusList);
        System.out.println("Sorted successfully!\nPlease choose function 2 to see it.");
    }

    //Save customer list
    private static void processSaveCusList() {
        boolean result = FileFactory.saveFile(cusList, "E:\\SELF_LEARNING\\customer_management\\src\\file\\CustomerList.txt");
        if (result == true){
            System.out.println("Saved file successfully!");
        }else {
            System.out.println("Failed to save file!");
        }
    }

    //Read customer list from file saved in hardware
    private static void processReadCusList() {
        cusList = FileFactory.readFile("E:\\SELF_LEARNING\\customer_management\\src\\file\\CustomerList.txt");
        System.out.println("Read file successfully!");
    }

    //Count number of customers by a network provider
    private static void processCountCustomerByNetwork() {
        System.out.println("Please enter the first 3 digits of one network provider:");
        String numFind = (new Scanner(System.in)).next();
        int count = 0;
        for (Customer cus : cusList){
            if (cus.getPhoneNumber().startsWith(numFind)){
                count++;
            }
        }
        System.out.println("Have " + count + "use this network provider!");
    }

    //Delete customer by id
    private static void processDeleteCustomer() {
        System.out.println("Please enter the ID of the customer to delete:");
        int idToDelete = (new Scanner(System.in)).nextInt();

        Customer customerToDelete = null;

        for (Customer cusDelete : cusList) {
            if (cusDelete.getId() == idToDelete) {
                customerToDelete = cusDelete;
                break;
            }
        }

        if (customerToDelete != null) {
            cusList.remove(customerToDelete);
            System.out.println("Customer with ID " + idToDelete + " has been deleted.");
        } else {
            System.out.println("Customer with ID " + idToDelete + " not found.");
        }
    }

    //Exit program
    private static void processExit() {
        System.out.println("Thank you! \nBye Bye!");
        System.exit(0);
    }


    //process the menu
    public static void main(String[] args) {
        while (true){
            menu();
        }
    }
}
