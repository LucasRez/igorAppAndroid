package com.example.lucasrezende.igor.model;

/**
 * Created by vzaffalon on 01/12/2017.
 */

public class Section {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Section(String name) {
        this.name = name;
    }

    private String name;
}
