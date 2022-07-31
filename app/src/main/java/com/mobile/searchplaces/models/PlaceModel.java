package com.mobile.searchplaces.models;

import java.io.Serializable;

public class PlaceModel implements Serializable {
    int id;
    String name;
    String description;
    byte[] image;
    double rating;

    public PlaceModel() {
    }

    public PlaceModel(int id, String name, String description, byte[] image, double rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
