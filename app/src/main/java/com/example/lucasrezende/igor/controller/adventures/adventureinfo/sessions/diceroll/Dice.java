package com.example.lucasrezende.igor.controller.adventures.adventureinfo.sessions.diceroll;

/**
 * Created by vzaffalon on 09/11/2017.
 */

public class Dice {

    public Dice(String type, int result, int value, int numberOfDices, boolean positive, int modifier) {
        this.type = type;
        this.result = result;
        this.value = value;
        this.numberOfDices = numberOfDices;
        this.positive = positive;
        this.modifier = modifier;
    }

    private String type;
    private int result;
    private int value;
    private int numberOfDices;
    private boolean positive;
    private int modifier;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getNumberOfDices() {
        return numberOfDices;
    }

    public void setNumberOfDices(int numberOfDices) {
        this.numberOfDices = numberOfDices;
    }

    public boolean isPositive() {
        return positive;
    }

    public void setPositive(boolean positive) {
        this.positive = positive;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }
}
