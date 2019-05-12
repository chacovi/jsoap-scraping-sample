package me.cordova.model;

public class Product {

    String name;
    Brand brand;
    String image;
    Integer stock;

    public Product(String name, Brand brand, String image, Integer stock) {
        this.name = name;
        this.brand = brand;
        this.image = image;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", brand=" + brand.getName() +
                ", image='" + image + '\'' +
                ", stock=" + stock +
                '}';
    }
}
