package com.fast_ad.fast_ad.plugins.places;

/**
 * Created by Hp on 3/18/2016.
 */
public class PlaceModel {

    private int id;
    private String name,position;
    private int image;


    public PlaceModel(int id, String name , int image) {
        this.id = id;
        this.name = name;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
