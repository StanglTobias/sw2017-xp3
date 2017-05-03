package at.sw2017xp3.regionalo.model;

import android.graphics.Bitmap;
import android.net.Uri;

public class Product {

    private int id_;
    private String unitType_;
    private String name_;
    private boolean isBio_;
    private double price_;
    private int userID_;
    private int typeID_;

    public Product(int id, String name, boolean isBio, double price, int userID, int typeID, String unitType) {
        id_ = id;
        name_ = name;
        isBio_ = isBio;
        price_ = price;
        userID_ = userID;
        typeID_ = typeID;
        unitType_ = unitType;
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
        return userID_;
    }

    public double getPrice() {
        return price_;
    }

    public int getType() {
        return typeID_;
    }

    public String getUnit() { return unitType_;}

    public User getUser()
    {
        return Core.getInstance().getUsers().getUser(userID_);
    }

}
