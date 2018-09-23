package pl.sdacademy.vending.service;

import pl.sdacademy.vending.controller.services.EmployeeService;
import pl.sdacademy.vending.model.Product;
import pl.sdacademy.vending.model.Tray;
import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.service.repositories.VendingMachineRepository;
import pl.sdacademy.vending.util.Configuration;

import java.util.Optional;

public class DefaultEmployeeService implements EmployeeService {

    private final VendingMachineRepository vendingMachineRepository;
    private final Configuration configuration;

    public DefaultEmployeeService(VendingMachineRepository vendingMachineRepository, Configuration configuration) {
        this.vendingMachineRepository = vendingMachineRepository;
        this.configuration = configuration;
    }

    @Override
    public String addTray(Tray tray) {
        VendingMachine machine = vendingMachineRepository.load().orElse(new VendingMachine(configuration));
        boolean trayWasAdded = machine.placeTray(tray);
        vendingMachineRepository.save(machine);
        if (trayWasAdded) {
            return null;
        } else {
            return "Could not add tray with symbol " + tray.getTraySymbol();
        }
    }

    @Override
    public String removeTray(String symbol) {
        Optional<VendingMachine> loadedMachine = vendingMachineRepository.load();
        if (loadedMachine.isPresent()) {
            VendingMachine machine = loadedMachine.get();
            Optional<Tray> trayToRemove = machine.removeTrayWithSymbol(symbol);
            if (trayToRemove.isPresent()) {
                vendingMachineRepository.save(machine);
                return null;
            } else {
                return "Could not remove tray with symbol " + symbol;
            }
        } else {
            return "No machine configured - try add tray first";
        }
    }

    @Override
    public String addProducts(String symbol, String productName, Integer amount) {
        Optional<VendingMachine> loadedMachine = vendingMachineRepository.load();
        if (loadedMachine.isPresent()) {
            VendingMachine machine = loadedMachine.get();
            for (int i = 0; i < amount; i++) {
                Product product = new Product(productName);
                machine.addProductsToTray(symbol, product);
            }
            vendingMachineRepository.save(machine);
            return null;
        } else {
            return "No machine configured";
        }
    }

}
