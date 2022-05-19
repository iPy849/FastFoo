package com.ipy849.fastfoo.model;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ipy849.fastfoo.AppSession;
import com.ipy849.fastfoo.R;
import com.ipy849.fastfoo.dialogHandlers.DialogFragmentTextOk;
import com.ipy849.fastfoo.utils.SharedPreferencesFiles;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String name, address, state, payMethod, email;
    private ArrayList<Restaurant> favoriteRestaurant;
    private ArrayList<Product> cart;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Restaurant> getFavoriteRestaurant() {
        if(favoriteRestaurant == null)
            favoriteRestaurant = new ArrayList<Restaurant>();
        return favoriteRestaurant;
    }

    public void setFavoriteRestaurant(ArrayList<Restaurant> favoriteRestaurant) {
        this.favoriteRestaurant = favoriteRestaurant;
    }

    public void addFavoriteRestaurant(Restaurant restaurant){
        if(favoriteRestaurant == null)
            favoriteRestaurant = new ArrayList<Restaurant>();
        favoriteRestaurant.add(restaurant);
    }

    public Restaurant removeRestaurant(Restaurant restaurant){
        Restaurant removedRestaurant = null;
        if(favoriteRestaurant == null) return null;

        for (int i = 0; i < favoriteRestaurant.size(); i++) {
            if(favoriteRestaurant.get(i).getName().equals(restaurant.getName())) {
                removedRestaurant = favoriteRestaurant.remove(i);
                break;
            }
        }
        return removedRestaurant;
    }

    public boolean isFavorite(Restaurant restaurant){

        boolean hasRestaurant = false;
        if(favoriteRestaurant == null) return false;

        for (int i = 0; i < favoriteRestaurant.size(); i++) {
            if(favoriteRestaurant.get(i).getName().equals(restaurant.getName())){
                hasRestaurant = true;
                break;
            }
        }
        return hasRestaurant;
    }

    public ArrayList<Product> getCart() {
        if(cart == null)
            cart = new ArrayList<>();
        return cart;
    }

    public void setCart(ArrayList<Product> cart) {
        this.cart = cart;
    }

    public void addProduct2Cart(Product product){
        if(cart == null)
            cart = new ArrayList<Product>();
        cart.add(product);
    }

    public Product removeProduct2Cart(Product product){
        Product removedProduct = null;
        if(cart == null)
            cart = new ArrayList<Product>();

        for (int i = 0; i < cart.size(); i++) {
            if(cart.get(i).getName().equals(product.getName())) {
                removedProduct = cart.remove(i);
                break;
            }
        }
        return removedProduct;
    }

    public boolean isInCart(Product product){

        boolean hasProduct = false;
        if(cart == null)
            return false;

        for (int i = 0; i < cart.size(); i++) {
            if(cart.get(i).getName().equals(product.getName())){
                hasProduct = true;
                break;
            }
        }
        return hasProduct;
    }

    public HashMap<String, Object> generateHashMap(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("email", this.email);
        hashMap.put("name", this.name);
        hashMap.put("address", this.address);
        hashMap.put("state", this.state);
        hashMap.put("payMethod", this.payMethod);
        hashMap.put("favorite", this.favoriteRestaurant);
        hashMap.put("cart", this.cart);
        return  hashMap;
    }

    public void save(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferencesFiles.USERS_FILE.name(), Context.MODE_PRIVATE);
        DatabaseReference fbDatabaseRef = FirebaseDatabase.getInstance().getReference();
        fbDatabaseRef.child("users").child(sharedPreferences.getString("uid", "")).setValue(this);
    }

    public User(){
        AppSession.user = this;
    }

    public User (HashMap<String, Object> data){
        AppSession.user = this;
        email = (String) data.get("email");
        name = (String) data.get("name");
        address = (String) data.get("address");
        state = (String) data.get("state");
        payMethod = (String) data.get("payMethod");

        if(data.keySet().contains("favoriteRestaurant")) {

            ArrayList<Restaurant> favoriteRestaurant = new ArrayList<>();
            for (HashMap<String, Object> restaurantData : (ArrayList<HashMap>) data.get("favoriteRestaurant")) {
                Restaurant restaurant = new Restaurant(restaurantData);
                favoriteRestaurant.add(restaurant);
            }
            this.favoriteRestaurant = favoriteRestaurant;
        }

        if(data.keySet().contains("cart")) {
            ArrayList<Product> cart = new ArrayList<>();
            for ( HashMap<String, Object> productData : (ArrayList<HashMap>) data.get("cart")){
                Product product = new Product();
                product.setName((String) productData.get("name"));
                product.setImage((String) productData.get("image"));
                product.setPrice( ((Long) productData.get("price")).intValue() );
                cart.add(product);
            }
            this.cart = cart;
        }

    }

}
