package me.cordova.model;

public class Item {

    private String title;
    private String url;

    public Item(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
