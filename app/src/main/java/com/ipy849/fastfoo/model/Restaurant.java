package com.ipy849.fastfoo.model;

import android.widget.ArrayAdapter;

import com.ipy849.fastfoo.AppSession;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Restaurant {
    private boolean liked = false;
    private String ubication, name;
    private String imageUrl;
    private ArrayList<Product> productos;


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

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getImage() {
        return imageUrl;
    }

    public void setImage(String image) {
        this.imageUrl = image;
    }

    public ArrayList<Product> getProductos() {
        if(productos == null)
            productos = new ArrayList<>();
        return productos;
    }

    public void setProductos(ArrayList<Product> products) {
        this.productos = products;
    }

    public Restaurant(){};

    public Restaurant(HashMap<String, Object> data){
        setName((String) data.get("name"));
        setUbication((String) data.get("ubication"));
        setImage((String) data.get("image"));
        setLiked(AppSession.user.isFavorite(this));
        productos = new ArrayList<>();
        for (HashMap<String, Object> productData: ((ArrayList<HashMap>) data.get("productos"))) {
            Product product = new Product();
            product.setName((String) productData.get("name"));
            product.setImage((String) productData.get("image"));
            product.setPrice( ((Long) productData.get("price")).intValue() );
            productos.add(product);
        }
    }
}
