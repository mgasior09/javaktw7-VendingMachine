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
        System.out.print("Set new tray symbol: ");
        String providedSymbol = getStringUserInput();
        System.out.print("Set new tray price: ");
        String providedPrice = getStringUserInput();
        Long price = (long) (Double.parseDouble(providedPrice) * 100);
        Tray tray = Tray.builder(providedSymbol).setPrice(price).build();
        String operationResult = employeeService.addTray(tray);
        if (operationResult != null) {
            System.out.println(operationResult);
        }
    }

    private String getStringUserInput() {
        return scanner.nextLine();
    }
}
