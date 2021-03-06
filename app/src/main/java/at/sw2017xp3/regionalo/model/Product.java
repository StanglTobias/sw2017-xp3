package at.sw2017xp3.regionalo.model;

import android.graphics.Bitmap;
import android.net.Uri;

import static at.sw2017xp3.regionalo.model.ProductManager.PRODUCT_IMAGE_URI;
import static at.sw2017xp3.regionalo.model.ProductManager.PRODUCT_URI;

/**
 * Created by jo on 05.04.17.
 */

public class Product {
    public Product(int id, String name, boolean isBio, double price, int producerId, int typeID, String unitType, int likes,
                   String description)
    {
        id_ = id;
        name_ = name;
        isBio_ = isBio;
        price_ = price;
        producerId_ = producerId;
        typeID_ = typeID;
        unitType_ = unitType;
        likes_ = likes;
        description_ = description;
    }

    public String getName() {
        return name_;
    }

    public int getId() {
        return id_;
    }

    public boolean isBio() {
        return isBio_;
    }

    public int getProducerId() {
        return producerId_;
    }

    public double getPrice() {
        return price_;
    }

    public int getType() {
        return typeID_;
    }

    public String getUnit() {
        return unitType_;
    }

    public User getUser() {
        return Core.getInstance().getUsers().getUser(producerId_);
    }

    public boolean CurrentUserHasLiked() {
        return currentUserLiked_;
    }

    public void SetCurrentUserHasLiked(boolean val) {
        currentUserLiked_ = val;
    }

    public int getLikes() {
        return likes_;
    }

    public void incrementLikes() {
        likes_++;
        currentUserLiked_ = true;
    }

    public String getDescription(){
        return description_;
    }


    int id_;
    String unitType_;
    String name_;
    boolean isBio_;
    double price_;
    int producerId_;
    int typeID_;
    int likes_;
    boolean currentUserLiked_;
    String description_;
}
