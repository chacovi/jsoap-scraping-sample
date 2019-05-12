package me.cordova;


import me.cordova.model.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    private static Document document;
    private static Elements elements;
    private static Element element;

    public static void main(String[] args) throws IOException {

        //document = Jsoup.connect("https://www.metro.pe/abarrotes").get();
        document = Jsoup.connect("https://www.metro.pe/bebidas").get();

        //elements = getElementByClass("main");

        List<Item> items;

        elements = document.select(".product-item__info");

/*      System.out.println("itemNodes ===================================================");
        System.out.println(itemNodes.outerHtml());

        itemNodes.forEach(
                element -> {
                    System.out.println("itemNodes element ===================================================");
                    //System.out.println(element.attributes());
                    //System.out.println(element.baseUri());
                    //System.out.println(element.child(0));
                    //System.out.println(element.children());
                    System.out.println(element.getElementsByAttribute("href"));
                }
        );*/

        items = elements.stream()
                .map(element ->
                        new Item(
                                element.getElementsByAttribute("href").attr("title"),
                                element.getElementsByAttribute("href").attr("href")))
                .collect(Collectors.toList());

        System.out.println("===================================================");
        System.out.println("========== #1 Found " + items.size() + " items =========================");
        System.out.println("===================================================");
        System.out.println(items);
        System.out.println("===================================================");


        System.out.println("===================================================");
        System.out.println("========== #2 Found Details for each items ========");
        System.out.println("===================================================");

        Integer i = 0;

        for (Item item: items){

            String url = item.getUrl();

            document = Jsoup.connect(url).get();

            System.out.println("===================================================");
            System.out.println( "# " + i++ + " " + document.title());
            System.out.println("===================================================");

            //final Elements productContent = document.getAllElements();

            //Elements productInfo = document.getElementsByClass("product-info");

/*            System.out.println("product-info ===================================================");
            elements = getElementByClass("product-info");
            System.out.println(elements);*/

            //System.out.println("info-wrapper ===================================================");
            elements = getElementByClass("info-wrapper");
            //System.out.println(elements.outerHtml());

            System.out.println("Product name ====================================================");
            System.out.println(elements.select(".name").first().text());
            System.out.println("Product brand ===================================================");
            System.out.println(elements.select(".brand").first().text());
            System.out.println("Product description =============================================");
            System.out.println(elements.select(".productDescription").first().text());
            System.out.println("Product price ===================================================");
            System.out.println(elements.select(".skuBestPrice").first().text());
            System.out.println("Product image ===================================================");
            //elements = getElementByClass("image");
            //System.out.println(elements.select("#image").first().outerHtml());
            System.out.println(getElementByClass("image").select("#image").first().getElementsByAttribute("href").attr("href"));

        }


    }

    private static Elements getElementByClass(String className) {

        return document.select(".".concat(className));
    }
}
