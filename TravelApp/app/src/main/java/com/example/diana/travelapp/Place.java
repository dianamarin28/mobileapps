package com.example.diana.travelapp;

/**
 * Created by Diana on 12/10/2016.
 */

public class Place {
    private int id;
    private String country;
    private String city;
    private int rating;

    public Place(int id, String country, String city, int rating) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return id + " " + country + " " + city + " " + rating;
    }
}




