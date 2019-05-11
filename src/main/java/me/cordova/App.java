package me.cordova;


import me.cordova.model.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect("https://www.metro.pe/abarrotes").get();

        Elements main = doc.select(".main");

        List<Item> items;

        Elements itemNodes = main.select(".product-item__info");

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

        items = itemNodes.stream()
                .map(element ->
                        new Item(
                                element.getElementsByAttribute("href").attr("title"),
                                element.getElementsByAttribute("href").attr("href")))
                .collect(Collectors.toList());

        System.out.println("===================================================");
        System.out.println("========== Found items ============================");
        System.out.println("===================================================");
        System.out.println(items);
        System.out.println("===================================================");

    }
}
