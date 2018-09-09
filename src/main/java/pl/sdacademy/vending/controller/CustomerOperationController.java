package pl.sdacademy.vending.controller;

import pl.sdacademy.vending.model.Product;
import pl.sdacademy.vending.model.Tray;
import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.util.StringUtils;

import java.util.Optional;

public class CustomerOperationController {
    private static final String UPPER_BOUNDARY = "+" + StringUtils.multiplyText("-", 12) + "+";
    private static final String LOWER_BOUNDARY = "+" + StringUtils.multiplyText("-", 12) + "+";
    private static final String WALL_BOUNDARY = "|";
    private static final String EMPTY_CELL = "--";

    private final VendingMachine machine;

    public CustomerOperationController(VendingMachine machine) {
        this.machine = machine;
    }

    public void printMachine() {
        for (int row = 0; row < machine.rowsSize(); row++) {
            for (int col = 0; col < machine.colsSize(); col++) {
                printUpperBoundaryForCell(row, col);
            }
            System.out.println();
            for (int col = 0; col < machine.colsSize(); col++) {
                printSymbolForCell(row, col);
            }
            System.out.println();
            for (int col = 0; col < machine.colsSize(); col++) {
                printProductNameForCell(row, col);
            }
            System.out.println();
            for (int col = 0; col < machine.colsSize(); col++) {
                printTrayPriceForCell(row, col);
            }
            System.out.println();
            for (int col = 0; col < machine.colsSize(); col++) {
                printLowerBoundaryForCell(row, col);
            }
            System.out.println();
        }
    }

    private void printUpperBoundaryForCell(int row, int col) {
        System.out.print(UPPER_BOUNDARY);
    }

    private void printSymbolForCell(int row, int col) {
        Optional<Tray> tray = machine.trayDetailsAtPosition(row, col);
        String traySymbol = tray.map(Tray::getTraySymbol).orElse(EMPTY_CELL);
        System.out.print(WALL_BOUNDARY + StringUtils.adjustText(traySymbol, 12) + WALL_BOUNDARY);
    }

    private void printProductNameForCell(int row, int col) {
        Optional<String> productName = machine.productNameAtPosition(row, col);
        System.out.print(WALL_BOUNDARY + StringUtils.adjustText(productName.orElse(EMPTY_CELL), 12) + WALL_BOUNDARY);
    }

    private void printTrayPriceForCell(int row, int col) {
        Optional<Tray> tray = machine.trayDetailsAtPosition(row, col);
        Long price = tray.map(Tray::getPrice).orElse(0L);
        System.out.print(WALL_BOUNDARY + StringUtils.adjustText(StringUtils.formatMoney(price), 12) + WALL_BOUNDARY);
    }

    private void printLowerBoundaryForCell(int row, int col) {
        System.out.print(LOWER_BOUNDARY);
    }

    public Optional<Product> buyProduct(String traySymbol) {
        return machine.buyProductWithSymbol(traySymbol);
    }
}
