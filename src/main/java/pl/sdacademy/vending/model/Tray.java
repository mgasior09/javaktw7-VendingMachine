package pl.sdacademy.vending.model;

import java.util.LinkedList;
import java.util.Queue;

public class Tray {

    private String traySymbol;
    private Long price;
    private Queue<Product> products;

    private Tray(Builder builder) {
        this.traySymbol = builder.traySymbol;
        this.price = builder.price;
        this.products = builder.products;
    }

    public String getTraySymbol() {
        return traySymbol;
    }
    public Long getPrice() {
        return price;
    }

    public static Builder builder(String traySymbol) {
        return new Builder(traySymbol);
    }

    public static class Builder {
        private String traySymbol;
        private Long price;
        private Queue<Product> products;

        private Builder(String traySymbol) {
            if (traySymbol == null) {
                throw new IllegalArgumentException("Tray symbol cannot be null");
            }
            this.traySymbol = traySymbol;
            this.products = new LinkedList<>();
        }

        public Builder setTraySymbol(String traySymbol) {
            this.traySymbol = traySymbol;
            return this;
        }

        public Builder setPrice(Long price) {
            this.price = price;
            return this;
        }

        public Builder setProducts(Product product) {
            this.products.add(product);
            return this;
        }

        public Tray build() {
            if (price == null) {
                price = 990L;
            }
            return new Tray(this);
        }
    }
}
