package me.cordova;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.cordova.model.Brand;
import me.cordova.model.Item;
import me.cordova.model.Product;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    private static Document document;

    public static void main(String[] args) throws IOException {

        //document = Jsoup.connect("https://www.metro.pe/frutas-y-verduras").get();
        //document = Jsoup.connect("https://www.metro.pe/lacteos-y-huevos").get();
        //document = Jsoup.connect("https://www.metro.pe/abarrotes").get();
        //document = Jsoup.connect("https://www.metro.pe/bebidas").get();
        document = Jsoup.connect("https://www.metro.pe/vinos-y-licores").get();

        List<Item> items;

        items = document.select(".product-item__info").stream()
                .map(element ->
                        new Item(
                                element.getElementsByAttribute("href").attr("title"),
                                element.getElementsByAttribute("href").attr("href")))
                .collect(Collectors.toList());

        System.out.println("===================================================");
        System.out.println("Scraping to >>> " + document.baseUri());
        System.out.println("===================================================");
        System.out.println("========== #1 Found " + items.size() + " items ====");
        System.out.println("===================================================");
        System.out.println("========== #2 Found details for each items ========");
        System.out.println("===================================================");

        List<Product> products = new ArrayList<>();

        Integer i = 1;

        for (Item item : items) {

            try {
                document = Jsoup.connect(item.getUrl()).get();
            }catch (IOException ex){
                System.out.println(ex.getMessage());
            }

            System.out.println("========== P " + i++ + " " + document.title());
            System.out.println("===================================================");

            System.out.println("Product name ======================================");
            System.out.println(getProductName());
            System.out.println("Product brand =====================================");
            System.out.println(getProductBrand().getName());
            System.out.println("Product description ===============================");
            System.out.println(getProductDescription());
            System.out.println("Product price =====================================");
            System.out.println(getProductPrice());
            System.out.println("Product imageUrl ==================================");
            System.out.println(getProductImageUrl());
            System.out.println("Product Stock =====================================");
            System.out.println(getProductStock());
            System.out.println("===================================================");

            products.add(createProduct());

        }

        ObjectMapper objectMapper = new ObjectMapper();
        String productsJson = objectMapper.writeValueAsString(products);


        System.out.println("JSON OUTPUT============================================");
        System.out.println(productsJson);
        System.out.println("=======================================================");

    }

    private static Product createProduct() {

        String name = getProductName();
        String description = getProductDescription();
        Double price = getProductPrice();
        Brand brand = getProductBrand();
        String imageUrl = getProductImageUrl();
        Integer stock = getProductStock();

        Product product = Product.getBuilder()
                .withName(name)
                .withDescription(description)
                .withPrice(price)
                .withBrand(brand)
                .withImageUrl(imageUrl)
                .withStock(stock)
                .build();

        return product;
    }

    private static String getProductName() {
        return document.select(".info-wrapper").select(".name").first().text();
    }

    private static String getProductDescription() {
        return document.select(".info-wrapper").select(".productDescription").first().text();
    }

    private static Double getProductPrice() {
        String price = StringUtils.substringAfter(document.select(".info-wrapper").select(".skuBestPrice").first().text(), "S/. ");
        return NumberUtils.isParsable(price) ? Double.parseDouble(price) : 0.0;
    }

    private static Brand getProductBrand() {
        String brandName = document.select(".info-wrapper").select(".brand").first().text();
        return new Brand(brandName, "");
    }

    private static String getProductImageUrl() {
        return document.select(".image").select("#image").first().getElementsByAttribute("href").attr("href");
    }

    private static Integer getProductStock() {
        return 100;
    }


}
