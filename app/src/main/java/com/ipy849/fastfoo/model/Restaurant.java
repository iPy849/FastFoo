package com.ipy849.fastfoo.model;

import java.net.URI;
import java.net.URL;

public class Restaurant {
    private boolean liked;
    private String ubication, title;
    private URI image;

    public Restaurant(boolean liked, String ubication, String title, URI image) {
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

    public URI getImage() {
        return image;
    }

    public void setImage(URI image) {
        this.image = image;
    }
}
