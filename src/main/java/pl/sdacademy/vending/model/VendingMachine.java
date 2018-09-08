package pl.sdacademy.vending.model;

import pl.sdacademy.vending.util.Configuration;

import java.util.Optional;
import java.util.Random;

public class VendingMachine {

    private final Configuration configuration;
    private final Tray[][] trays;
    private final Long maxRowsSize;
    private final Long maxColsSize;

    public VendingMachine(Configuration configuration) {
        maxRowsSize = configuration.getProperty("machine.size.rows", 6L);
        maxColsSize = configuration.getProperty("machine.size.cols", 4L);
        if (maxRowsSize > 26L || maxRowsSize < 1L || maxColsSize > 9L || maxColsSize < 1L) {
            throw new IllegalArgumentException();
        }
        this.configuration = configuration;
        trays = new Tray[maxRowsSize.intValue()][maxColsSize.intValue()];
        for (int rowNumber = 0; rowNumber < maxRowsSize; rowNumber++) {
            for (int colNumber = 0; colNumber < maxColsSize; colNumber++) {
                trays[rowNumber][colNumber] = createTrayForPosition(rowNumber, colNumber);
            }
        }
    }

    private Tray createTrayForPosition(int row, int col) {
        if (!shouldGenerateTray()) {
            return null;
        }
        char rowSymbol = (char) ('A' + row);
        int columnSymbol = col + 1;
        String symbol = "" + rowSymbol + columnSymbol;
        Random random = new Random();
        int generatedPrice = random.nextInt(401);
        int calculatedPrice = generatedPrice + 100;
        double productProbability = Math.random();
        if (productProbability < 0.1) {
            Product product1 = Product.builder("Product " + symbol).build();
            Product product2 = Product.builder("Product " + symbol).build();
            return Tray.builder(symbol).setPrice((long) calculatedPrice).setProducts(product1).setProducts(product2).build();
        } else if (productProbability < 0.5) {
            Product product1 = Product.builder("Product " + symbol).build();
            return Tray.builder(symbol).setPrice((long) calculatedPrice).setProducts(product1).build();
        }
        return Tray.builder(symbol).setPrice((long) calculatedPrice).build();
    }

    private boolean shouldGenerateTray() {
        return Math.random() < 0.8;
    }

    public Long rowsSize() {
        Long property = configuration.getProperty("machine.size.rows", 6L);
        return property;
    }

    public Long colsSize() {
        Long property = configuration.getProperty("machine.size.cols", 4L);
        return property;
    }

    public Optional<Tray> trayDetailsAtPosition(int rowNumber, int colNumber) {
        Tray obtainedTray = trays[rowNumber][colNumber];
        Optional<Tray> tray = Optional.ofNullable(obtainedTray);
        return tray;
    }
}



