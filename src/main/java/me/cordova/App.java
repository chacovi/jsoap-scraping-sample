package me.cordova;


import me.cordova.model.Brand;
import me.cordova.model.Item;
import me.cordova.model.Product;
import me.cordova.model.Product.ProductBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    private static Document document;
    private static Elements elements;
    private static Element element;

    public static void main(String[] args) throws IOException {

        //document = Jsoup.connect("https://www.metro.pe/abarrotes").get();
        document = Jsoup.connect("https://www.metro.pe/bebidas").get();

        List<Item> items;

        elements = document.select(".product-item__info");

        items = elements.stream()
                .map(element ->
                        new Item(
                                element.getElementsByAttribute("href").attr("title"),
                                element.getElementsByAttribute("href").attr("href")))
                .collect(Collectors.toList());

        System.out.println("===================================================");
        System.out.println("========== #1 Found " + items.size() + " items ====");
        System.out.println("===================================================");
        System.out.println(items);
        System.out.println("===================================================");
        System.out.println("========== #2 Found Details for each items ========");
        System.out.println("===================================================");

        List<Product> products = new ArrayList<>();

        Integer i = 0;

        for (Item item: items){

            String url = item.getUrl();

            document = Jsoup.connect(url).get();

            System.out.println("===================================================");
            System.out.println( "========== P " + i++ + " " + document.title());
            System.out.println("===================================================");

            products.add(createProduct());

        }
        System.out.println(products.toString());
        System.out.println("===================================================");

    }

    private static Product createProduct() {

        elements = document.select(".info-wrapper");

        String name = elements.select(".name").first().text();
        String description = elements.select(".productDescription").first().text();
        Double price = getProductPrice(elements.select(".skuBestPrice").first().text());
        Brand brand = getProductBrand(elements.select(".brand").first().text());
        String image = document.select(".image").select("#image").first().getElementsByAttribute("href").attr("href");
        Integer stock = 100;

        Product product = Product.getBuilder()
                .withName(name)
                .withDescription(description)
                .withPrice(price)
                .withBrand(brand)
                .withImage(image)
                .withStock(stock)
                .build();

        return product;
    }

    private static Brand getProductBrand(String brand) {
        return new Brand(brand,"");
    }

    private static Double getProductPrice(String price) {
        return 15.6;
    }

}
