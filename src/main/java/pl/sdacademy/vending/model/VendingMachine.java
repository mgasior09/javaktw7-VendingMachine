package pl.sdacademy.vending.model;

import pl.sdacademy.vending.util.Configuration;

import java.io.Serializable;
import java.util.Optional;
import java.util.Random;

public class VendingMachine implements Serializable {
    public static final long serialVersionUID = 1L;
    private final Tray[][] trays;
    private final Long maxRowsSize;
    private final Long maxColsSize;

    public VendingMachine(Configuration configuration) {
        maxRowsSize = configuration.getProperty("machine.size.rows", 6L);
        maxColsSize = configuration.getProperty("machine.size.cols", 4L);
        if (maxRowsSize > 26L || maxRowsSize < 1L || maxColsSize > 9L || maxColsSize < 1L) {
            throw new IllegalArgumentException();
        }
        trays = new Tray[maxRowsSize.intValue()][maxColsSize.intValue()];
    }

    public void init() {
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
        return maxRowsSize;
    }

    public Long colsSize() {
        return maxColsSize;
    }

    public Optional<Tray> trayDetailsAtPosition(int rowNumber, int colNumber) {
        Tray obtainedTray = trays[rowNumber][colNumber];
        Optional<Tray> tray = Optional.ofNullable(obtainedTray);
        return tray;
    }

    public Optional<String> productNameAtPosition(int rowNumber, int colNumber) {
        Tray tray = trays[rowNumber][colNumber];
        if (tray != null) {
            return tray.firstProductName();
        } else {
            return Optional.empty();
        }
    }

    public Optional<Product> buyProductWithSymbol(String traySymbol) {
        Optional<Tray> trayForSymbol = getTrayForSymbol(traySymbol);
        if (trayForSymbol.isPresent()) {
            Tray tray = trayForSymbol.get();
            return tray.getFirstProduct();
        } else {
            return Optional.empty();
        }
    }

    private Optional<Tray> getTrayForSymbol(String traySymbol) {
        return trayDetailsAtPosition(getRowNumberForSymbol(traySymbol), getColNumberForSymbol(traySymbol));
    }

    private int getRowNumberForSymbol(String traySymbol) {
        char rowSymbol = traySymbol.toUpperCase().charAt(0);
        int rowNumber = rowSymbol - 'A';
        return rowNumber;
    }

    private int getColNumberForSymbol(String traySymbol) {
        char colSymbol = traySymbol.charAt(1);
        int colNumber = colSymbol - '1';
        return colNumber;
    }

    public boolean placeTray(Tray tray) {
        try {
            String traySymbol = tray.getTraySymbol();
            int rowNumber = getRowNumberForSymbol(traySymbol);
            int colNumber = getColNumberForSymbol(traySymbol);
            if (trays[rowNumber][colNumber] == null) {
                trays[rowNumber][colNumber] = tray;
                return true;
            }
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return false;
    }

    public Optional<Tray> removeTrayWithSymbol(String symbol) {
        int colNumber = getColNumberForSymbol(symbol);
        int rowNumber = getRowNumberForSymbol(symbol);
        Optional<Tray> optionalTray = getTrayForSymbol(symbol);
        trays[rowNumber][colNumber] = null;
        return optionalTray;
    }
}



