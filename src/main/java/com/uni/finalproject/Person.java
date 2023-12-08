package com.uni.finalproject;

import java.util.HashMap;

public class Person {
    private final String name;
    private HashMap<String, Person> friends = new HashMap<>();
    private String image = "noImage.png";
    private String status = "No status";

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Person> getFriends() {
        return friends;
    }

    public void setFriends(HashMap<String, Person> friends) {
        this.friends = friends;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
