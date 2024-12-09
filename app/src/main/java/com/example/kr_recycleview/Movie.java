package com.example.kr_recycleview;
import java.io.Serializable;
public class Movie implements Serializable{
    private String title;
    private String genre;
    private int year;
    private double rating;
    private String imageName;
    private String streamingLink;
    private String description;

    public Movie(String title, String genre, int year, double rating, String imageName, String streamingLink,String description) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.imageName = imageName;
        this.streamingLink = streamingLink;
        this.description = description;
    }
    public Movie(String title, String genre, int year, double rating,String streamingLink,String description) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.streamingLink = streamingLink;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getStreamingLink() {
        return streamingLink;
    }

    public void setStreamingLink(String streamingLink) {
        this.streamingLink = streamingLink;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
