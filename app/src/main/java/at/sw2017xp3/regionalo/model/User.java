package at.sw2017xp3.regionalo.model;

/**
 * Created by jo on 26.04.17.
 */

public class User {

    int id_;
    String firstName_;
    String lastName_;
    String address_;
    String city_;
    String postalCode_;
    String email_;
    String phone_;
    boolean isBio_;
    double longitude_;
    double latitude_;
    boolean delivery_;
    boolean yardSale_;
    boolean selfHarvest_;
    boolean commercial_;




    public User(int id, String firstName, String lastName, String address, String city,
                String postalCode, String email, String phone_number,
                boolean isBio, double longitude, double latitude, boolean delivery,
                boolean yardSale, boolean selfHarvest, boolean commercial) {
        id_ = id;
        firstName_ = firstName;
        lastName_ = lastName;
        address_ = address;
        city_ = city;
        postalCode_ = postalCode;
        email_ = email;
        phone_ = phone_number;
        isBio_ = isBio;
        latitude_ = latitude;
        longitude_ = longitude;
        delivery_ = delivery;
        yardSale_ = yardSale;
        selfHarvest_ = selfHarvest;
        commercial_ = commercial;
    }

    public int getId() {
        return id_;
    }

    public String getFullName() {
        return firstName_ + " " + lastName_;
    }

    public String getAddress() {
        return address_;
    }

    public String getCity() {
        return city_;
    }

    public String getPostalCode() {
        return postalCode_;
    }

    public String getEmail() {
        return email_;
    }

    public String getPhoneNumber() {
        return phone_;
    }

    public Boolean isBio() {
        return isBio_;
    }

    public boolean isDelivery() {
        return delivery_;
    }

    public boolean isYardSale() {
        return yardSale_;
    }

    public boolean isSelfHarvest() {
        return selfHarvest_;
    }

    public boolean isCommercial() {
        return commercial_;
    }

}
