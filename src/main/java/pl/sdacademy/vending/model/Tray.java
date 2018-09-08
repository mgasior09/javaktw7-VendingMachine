package pl.sdacademy.vending.model;

public class Tray {

    private String traySymbol;
    private Long price;

    public Tray(String traySymbol, Long price) {
        this.traySymbol = traySymbol;
        this.price = price;
    }

    public String getTraySymbol() {
        return traySymbol;
    }

    public void setTraySymbol(String traySymbol) {
        this.traySymbol = traySymbol;
    }
}
