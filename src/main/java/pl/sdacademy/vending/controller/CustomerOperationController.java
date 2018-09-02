package pl.sdacademy.vending.controller;

import pl.sdacademy.vending.model.VendingMachine;

public class CustomerOperationController {

    private VendingMachine vendingMachine;

    public CustomerOperationController() {
        vendingMachine = new VendingMachine();
    }

    public void printMachine() {
        for (int row = 0; row < vendingMachine.rowsSize(); row++) {
            for (int col = 0; col < vendingMachine.colsSize(); col++) {
                System.out.print("+--------+");
            }
            System.out.println();
            for (int col = 0; col < vendingMachine.colsSize(); col++) {
                char rowSymbol = (char) ('A' + row);
                int columnSymbol = col + 1;
                System.out.print("|   " + rowSymbol + columnSymbol + "   |");
            }
            System.out.println();
            for (int col = 0; col < vendingMachine.colsSize(); col++) {
                System.out.print("+--------+");
            }
            System.out.println();
        }

    }
}
