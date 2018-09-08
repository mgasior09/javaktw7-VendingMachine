package pl.sdacademy.vending.model;

import pl.sdacademy.vending.util.Configuration;

import java.util.Optional;

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
        char rowSymbol = (char) ('A' + row);
        int columnSymbol = col + 1;
        String symbol = "" + rowSymbol + columnSymbol;
        return new Tray(symbol, 0L);
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



