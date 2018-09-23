package pl.sdacademy.vending.controller;

import pl.sdacademy.vending.model.Product;
import pl.sdacademy.vending.model.Tray;
import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.service.repositories.VendingMachineRepository;
import pl.sdacademy.vending.util.StringUtils;

import java.util.Optional;

public class CustomerOperationController {
    private static final String UPPER_BOUNDARY = "+" + StringUtils.multiplyText("-", 12) + "+";
    private static final String LOWER_BOUNDARY = "+" + StringUtils.multiplyText("-", 12) + "+";
    private static final String WALL_BOUNDARY = "|";
    private static final String EMPTY_CELL = "--";

    private VendingMachineRepository vendingMachineRepository;

    public CustomerOperationController(VendingMachineRepository vendingMachineRepository) {
        this.vendingMachineRepository = vendingMachineRepository;
    }

    public void printMachine() {
        Optional<VendingMachine> optionalMachine = vendingMachineRepository.load();
        if (!optionalMachine.isPresent()) {
            System.out.println("No machine configured");
            return;
        }
        VendingMachine machine = optionalMachine.get();
        for (int row = 0; row < machine.rowsSize(); row++) {
            for (int col = 0; col < machine.colsSize(); col++) {
                printUpperBoundaryForCell(machine, row, col);
            }
            System.out.println();
            for (int col = 0; col < machine.colsSize(); col++) {
                printSymbolForCell(machine, row, col);
            }
            System.out.println();
            for (int col = 0; col < machine.colsSize(); col++) {
                printProductNameForCell(machine, row, col);
            }
            System.out.println();
            for (int col = 0; col < machine.colsSize(); col++) {
                printTrayPriceForCell(machine, row, col);
            }
            System.out.println();
            for (int col = 0; col < machine.colsSize(); col++) {
                printLowerBoundaryForCell(machine, row, col);
            }
            System.out.println();
        }
    }

    private void printUpperBoundaryForCell(VendingMachine machine, int row, int col) {
        System.out.print(UPPER_BOUNDARY);
    }

    private void printSymbolForCell(VendingMachine machine, int row, int col) {
        Optional<Tray> tray = machine.trayDetailsAtPosition(row, col);
        String traySymbol = tray.map(Tray::getTraySymbol).orElse(EMPTY_CELL);
        System.out.print(WALL_BOUNDARY + StringUtils.adjustText(traySymbol, 12) + WALL_BOUNDARY);
    }

    private void printProductNameForCell(VendingMachine machine, int row, int col) {
        Optional<String> productName = machine.productNameAtPosition(row, col);
        System.out.print(WALL_BOUNDARY + StringUtils.adjustText(productName.orElse(EMPTY_CELL), 12) + WALL_BOUNDARY);
    }

    private void printTrayPriceForCell(VendingMachine machine, int row, int col) {
        Optional<Tray> tray = machine.trayDetailsAtPosition(row, col);
        Long price = tray.map(Tray::getPrice).orElse(0L);
        System.out.print(WALL_BOUNDARY + StringUtils.adjustText(StringUtils.formatMoney(price), 12) + WALL_BOUNDARY);
    }

    private void printLowerBoundaryForCell(VendingMachine machine, int row, int col) {
        System.out.print(LOWER_BOUNDARY);
    }

    public Optional<Product> buyProduct(String traySymbol) {
        Optional<VendingMachine> optionalMachine = vendingMachineRepository.load();
        if (optionalMachine.isPresent()) {
            VendingMachine machine = optionalMachine.get();
            Optional<Product> boughtProduct = machine.buyProductWithSymbol(traySymbol);
            vendingMachineRepository.save(machine);
            return boughtProduct;
        } else {
            return Optional.empty();
        }
        // return   optionalMachine.map(machine -> machine.buyProductWithSymbol(traySymbol)).orElseGet(Optional::empty);   <- na pamiątkę xD
    }
}
