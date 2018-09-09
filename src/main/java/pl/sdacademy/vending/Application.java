package pl.sdacademy.vending;

import pl.sdacademy.vending.controller.CustomerOperationController;
import pl.sdacademy.vending.model.Product;
import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.util.Configuration;
import pl.sdacademy.vending.util.PropertiesFileConfiguration;

import java.util.Optional;
import java.util.Scanner;

public class Application {

    private final CustomerOperationController customerOperationController;
    private final VendingMachine machine;

    public Application() {
        Configuration configuration = PropertiesFileConfiguration.getInstance();
        machine = new VendingMachine(configuration);
        customerOperationController = new CustomerOperationController(machine);
    }

    public void start() {
        int userSelection;
        do {
            customerOperationController.printMachine();
            printMenu();
            userSelection = getUserInput();
            switch (userSelection) {
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
        System.out.println("1. Buy product");
        System.out.println("9. Exit");
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
        Optional<Product> boughtProduct = machine.buyProductWithSymbol(userSelection);
        customerOperationController.buyProduct(userSelection);
        System.out.println(boughtProduct.map(Product::getName).orElse("Sold out"));
    }
}
