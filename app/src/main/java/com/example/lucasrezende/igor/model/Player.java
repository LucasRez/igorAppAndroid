package com.example.lucasrezende.igor.model;

/**
 * Created by vzaffalon on 09/09/17.
 */

public class Player {

    private int id;
    private String nickname;
    private String description;
    private String picture;
    private int user_id;
    private int adventure_id;

    public Player(int id,String nickname, String description, String picture, int user_id, int adventure_id) {
        this.id = id;
        this.nickname = nickname;
        this.description = description;
        this.picture = picture;
        this.user_id = user_id;
        this.adventure_id = adventure_id;
    }

    public Player(String nickname, String description, String picture, int user_id, int adventure_id) {
        this.nickname = nickname;
        this.description = description;
        this.picture = picture;
        this.user_id = user_id;
        this.adventure_id = adventure_id;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAdventure_id() {
        return adventure_id;
    }

    public void setAdventure_id(int adventure_id) {
        this.adventure_id = adventure_id;
    }

}
