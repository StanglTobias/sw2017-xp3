package at.sw2017xp3.regionalo.model;

/**
 * Created by jo on 05.04.17.
 */

public class Product {
    public Product(int id, String name, boolean isBio, double price, int producerId, int typeID) {
        id_ = id;
        name_ = name;
        isBio_ = isBio;
        price_ = price;
        producerId_ = producerId;
        typeID_ = typeID;
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

    int id_;
    String name_;
    boolean isBio_;
    double price_;
    int producerId_;
    int typeID_;
}
