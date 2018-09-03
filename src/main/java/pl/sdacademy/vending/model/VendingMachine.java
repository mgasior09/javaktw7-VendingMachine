package pl.sdacademy.vending.model;

import pl.sdacademy.vending.util.Configuration;

public class VendingMachine {

    private final Configuration configuration;

    public VendingMachine(Configuration configuration) throws IllegalArgumentException {
        Long propertyRows = configuration.getProperty("machine.size.rows", 6L);
        Long propertyCols = configuration.getProperty("machine.size.cols", 4L);
        if (propertyRows > 26L || propertyRows < 1 || propertyCols > 9 || propertyCols < 1) {
            throw new IllegalArgumentException();
        }
        this.configuration = configuration;

    }

    public Long rowsSize() {
        Long property = configuration.getProperty("machine.size.rows", 6L);
        return property;

    }

    public Long colsSize() {

        Long property = configuration.getProperty("machine.size.cols", 4L);

        return property;
    }
}



