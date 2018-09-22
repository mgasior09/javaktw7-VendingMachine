package pl.sdacademy.vending.repository;

import pl.sdacademy.vending.model.VendingMachine;
import pl.sdacademy.vending.service.repositories.VendingMachineRepository;
import pl.sdacademy.vending.util.Configuration;

import java.io.*;
import java.util.Optional;

public class HardDriveVendingMachineRepository implements VendingMachineRepository {
    private final String fileLocalisation;

    public HardDriveVendingMachineRepository(Configuration config) {
        fileLocalisation = config.getProperty("repository.location.vendingMachine", "venMach.ser");
    }

    @Override
    public VendingMachine save(VendingMachine machine) {
        try {
            ObjectOutputStream outputVendingMachine = new ObjectOutputStream(new FileOutputStream(fileLocalisation));
            outputVendingMachine.writeObject(machine);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return machine;
    }

    @Override
    public Optional<VendingMachine> load() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileLocalisation));
            VendingMachine readVendingMachine = (VendingMachine) objectInputStream.readObject();
            return Optional.ofNullable(readVendingMachine);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
