package pl.sdacademy.vending;

import pl.sdacademy.vending.controller.CustomerOperationController;
import pl.sdacademy.vending.controller.EmployeeController;
import pl.sdacademy.vending.model.Product;
import pl.sdacademy.vending.repository.HardDriveVendingMachineRepository;
import pl.sdacademy.vending.service.DefaultEmployeeService;
import pl.sdacademy.vending.service.repositories.VendingMachineRepository;
import pl.sdacademy.vending.util.Configuration;
import pl.sdacademy.vending.util.PropertiesFileConfiguration;

import java.util.Optional;
import java.util.Scanner;

public class Application {

    private final CustomerOperationController customerOperationController;
    private final EmployeeController employeeController;

    public Application() {
        Configuration configuration = PropertiesFileConfiguration.getInstance();
        VendingMachineRepository vendingMachineRepository = new HardDriveVendingMachineRepository(configuration);
        customerOperationController = new CustomerOperationController(vendingMachineRepository);
        DefaultEmployeeService defaultEmployeeService = new DefaultEmployeeService(vendingMachineRepository, configuration);
        employeeController = new EmployeeController(defaultEmployeeService);
    }

    public void start() {
        int userSelection;
        do {
            customerOperationController.printMachine();
            printMenu();
            userSelection = getUserInput();
            switch (userSelection) {
                case 0:
                    startServiceMenu();
                    break;
                case 1:
                    buyProductSelection();
                    break;
                case 9:
                    exitMenu();
                    break;
                default:
                    System.out.println("Incorrect selection");
            }
        }
        while (userSelection != 9);
    }

    private void printMenu() {
        System.out.println("0. Service menu");
        System.out.println("1. Buy product");
        System.out.println("9. Exit");
    }

    private void startServiceMenu() {
        int userSelection;
        do {
            customerOperationController.printMachine();
            printServiceMenu();
            userSelection = getUserInput();
            switch (userSelection) {
                case 1:
                    employeeController.addTray();
                    break;
                case 2:
                    employeeController.removeTray();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 9:
                    exitMenu();
                    break;
                default:
                    System.out.println("Incorrect selection");
            }
        } while (userSelection != 9);
        printMenu();
    }

    private void printServiceMenu() {
        System.out.println("    1. Add empty tray");
        System.out.println("    2. Remove tray");
        System.out.println("    3. Add products for tray");
        System.out.println("    4. Remove product from tray");
        System.out.println("    5. Change Price");
        System.out.println("    9. Exit");
    }


    private int getUserInput() {
        System.out.print("Your selection: ");
        String userInput = new Scanner(System.in).nextLine();
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void exitMenu() {
        System.out.println("Thank you! Bye!");
    }

    private void buyProductSelection() {
        System.out.print("Choose product: ");
        Scanner userInput = new Scanner(System.in);
        String userSelection = userInput.nextLine();
        Optional<Product> boughtProduct = customerOperationController.buyProduct(userSelection);
        customerOperationController.buyProduct(userSelection);
        System.out.println(boughtProduct.map(Product::getName).orElse("Sold out"));
    }
}
