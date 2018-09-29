package pl.sdacademy.vending.controller;

import pl.sdacademy.vending.controller.services.EmployeeService;
import pl.sdacademy.vending.model.Tray;

import java.util.Scanner;

public class EmployeeController {
    private final EmployeeService employeeService;
    private Scanner scanner = new Scanner(System.in);

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void addTray() {
        System.out.print("New tray symbol: ");
        String providedSymbol = getStringUserInput();

        System.out.print("New tray price: ");
        String providedPrice = getStringUserInput();
        Long convertedPrice = (long) (Double.parseDouble(providedPrice) * 100);

        Tray tray = Tray.builder(providedSymbol).setPrice(convertedPrice).build();

        String errorMessage = employeeService.addTray(tray);
        printErrorMessage(errorMessage, "Tray added");
    }

    public void removeTray() {
        System.out.print("Removed tray symbol: ");
        String userProvidedSymbol = getStringUserInput();
        String message = employeeService.removeTray(userProvidedSymbol);
        printErrorMessage(message, "Tray removed");
    }

    public void addProducts() {
        printErrorMessage(
                employeeService.addProducts(
                        askUserForString("Tray symbol: "),
                        askUserForString("Product name: "),
                        askUserForInteger("Amount: ")),
                "Products were added");
    }

    private void printErrorMessage(
            String errorMessage, String confirmationMessage) {
        if (errorMessage != null) {
            System.out.println(errorMessage);
        } else {
            System.out.println(confirmationMessage);
        }
    }

    private String askUserForString(String question) {
        System.out.print(question);
        return getStringUserInput();
    }

    private String getStringUserInput() {
        return scanner.nextLine();
    }

    private Integer askUserForInteger(String question) {
        System.out.print(question);
        return getIntegerUserInput();
    }

    private Integer getIntegerUserInput() {
        String value = scanner.nextLine();
        return Integer.parseInt(value);
    }

    public void removeProducts() {
        String symbol = askUserForString("Which tray should be emptied: ");
        String message = employeeService.emptyTray(symbol);
        printErrorMessage(message, "Tray emptied");
    }
}
