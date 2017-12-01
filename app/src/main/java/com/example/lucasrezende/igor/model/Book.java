package com.example.lucasrezende.igor.model;

/**
 * Created by vzaffalon on 30/11/2017.
 */

public class Book {


    private String imageUrl;
    private String name;
    private String subtitle;

    public Book(String name, String subtitle,String imageUrl) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.subtitle = subtitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
