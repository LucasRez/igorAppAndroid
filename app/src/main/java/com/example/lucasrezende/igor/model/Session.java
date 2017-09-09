package com.example.lucasrezende.igor.model;

import java.util.Date;

/**
 * Created by vzaffalon on 09/09/17.
 */

public class Session {

    private Date date;
    private String title;
    private String description;
    private int adventure_id;


    public Session(Date date, String title, String description, int adventure_id) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.adventure_id = adventure_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAdventure_id() {
        return adventure_id;
    }

    public void setAdventure_id(int adventure_id) {
        this.adventure_id = adventure_id;
    }
}
