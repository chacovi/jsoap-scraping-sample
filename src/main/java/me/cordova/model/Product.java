package me.cordova.model;

public class Product {

    private String name;
    private String description;
    private Double price;
    private Brand brand;
    private String image;
    private Integer stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", brand=" + brand.getName() +
                ", image='" + image + '\'' +
                ", stock=" + stock +
                '}';
    }

    public static ProductBuilder getBuilder() {
        return new ProductBuilder();
    }

    public static class ProductBuilder {

        private String name;
        private String description;
        private Double price;
        private Brand brand;
        private String image;
        private Integer stock;

        private Product product;

        public ProductBuilder withName(String name){
            this.name = name;
            return this;
        }

        public ProductBuilder withDescription(String description){
            this.description = description;
            return this;
        }

        public ProductBuilder withPrice(Double price){
            this.price = price;
            return this;
        }

        public ProductBuilder withBrand(Brand brand){
            this.brand = brand;
            return this;
        }

        public ProductBuilder withImage(String image){
            this.image = image;
            return this;
        }

        public ProductBuilder withStock(Integer stock){
            this.stock = stock;
            return this;
        }

        public Product build() {
            this.product = new Product();
            product.setName(this.name);
            product.setDescription(this.description);
            product.setPrice(this.price);
            product.setBrand(this.brand);
            product.setImage(this.image);
            product.setStock(this.stock);
            return this.product;
        }

        public Product getProduct() {
            return this.product;
        }

    }
}
