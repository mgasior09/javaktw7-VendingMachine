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
        if (errorMessage != null) {
            System.out.println(errorMessage);
        } else {
            System.out.println("Tray added.");
        }
    }

    public void removeTray() {
        System.out.print("Select tray to remove ");
        String providedSymbol = getStringUserInput();
        try {
            String errorMessage = employeeService.removeTray(providedSymbol);
            if (errorMessage != null) {
                System.out.println(errorMessage);
            } else {
                System.out.println("Tray removed");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid tray symbol");
        }
    }

    public void addProduct() {
        System.out.print("Choose tray to add products ");
        String providedSymbol = getStringUserInput();
        System.out.print("Provide product name  ");
        String providedProductName = getStringUserInput();
        System.out.print("Provide amount  ");
        String providedAmount = getStringUserInput();
        Integer amount = Integer.parseInt(providedAmount);
        String message = employeeService.addProducts(providedSymbol, providedProductName, amount);
        if (message == null) {
            System.out.println("Products added");
        } else {
            System.out.println(message);
        }
    }

    private String getStringUserInput() {
        return scanner.nextLine();
    }
}
