package com.example.lucasrezende.igor.model;

/**
 * Created by vzaffalon on 01/12/2017.
 */

public class DiceResult {

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DiceResult(String description) {
        this.description = description;
    }

    private String description;
}
