package pl.sdacademy.vending.model;

public class Product {

    private final String name;


    public Product(Builder builder) {
        this.name = builder.name;
    }

    public static Builder builder(String name) {
        return new Builder(name);
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private final String name;

        private Builder(String name) {
            if (name == null) {
                throw new IllegalArgumentException("Product name cannot be null");
            }
            this.name = name;
        }

        public Product build() {
            return new Product(this);
        }

    }
}
