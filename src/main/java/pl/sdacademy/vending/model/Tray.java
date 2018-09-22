package pl.sdacademy.vending.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class Tray implements Serializable {
    public static final long serialVersionUID = 1L;
    private String traySymbol;
    private Long price;
    private Queue<Product> products;

    private Tray(Builder builder) {
        this.traySymbol = builder.traySymbol;
        this.price = builder.price;
        this.products = builder.products;
    }

    public static Builder builder(String traySymbol) {
        return new Builder(traySymbol);
    }

    public String getTraySymbol() {
        return traySymbol;
    }

    public Long getPrice() {
        return price;
    }

    public Optional<String> firstProductName() {
        Product firstProduct = products.peek();
        Optional<String> optionalFirstProductName = Optional.ofNullable(firstProduct).map(Product::getName);
        return optionalFirstProductName;
    }

    public Optional<Product> getFirstProduct() {
        return Optional.ofNullable(products.poll());
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
