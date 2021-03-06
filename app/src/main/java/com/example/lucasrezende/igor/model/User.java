package com.example.lucasrezende.igor.model;

/**
 * Created by vzaffalon on 09/09/17.
 */

public class User {

    private int id;
    private String email;
    private String name;
    private String nickname;
    private String image;

    public User(int id,String email, String name, String nickname, String image) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.image = image;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
