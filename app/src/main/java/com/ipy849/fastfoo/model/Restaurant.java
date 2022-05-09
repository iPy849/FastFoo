package com.ipy849.fastfoo.model;

import java.net.URL;

public class Restaurant {
    private boolean liked;
    private String ubication, title;
    private URL image;

    public Restaurant(boolean liked, String ubication, String title, URL image) {
        this.liked = liked;
        this.ubication = ubication;
        this.title = title;
        this.image = image;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public URL getImage() {
        return image;
    }

    public void setImage(URL image) {
        this.image = image;
    }
}
