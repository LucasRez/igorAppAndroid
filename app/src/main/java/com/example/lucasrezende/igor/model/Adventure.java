package com.example.lucasrezende.igor.model;

/**
 * Created by vzaffalon on 09/09/17.
 */

public class Adventure {

    private String name;
    private String backgroud_image;
    private String position;
    private String description;
    private int user_id;
    private String book_name;

    public Adventure(String name, String backgroud_image, String position, String description, int user_id, String book_name) {
        this.name = name;
        this.backgroud_image = backgroud_image;
        this.position = position;
        this.description = description;
        this.user_id = user_id;
        this.book_name = book_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackgroud_image() {
        return backgroud_image;
    }

    public void setBackgroud_image(String backgroud_image) {
        this.backgroud_image = backgroud_image;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
}
