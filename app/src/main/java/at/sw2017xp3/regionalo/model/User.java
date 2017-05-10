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
    double lat_;
    double lon_;
    String email_;
    String phone_;
    boolean isBio_;
    int likes_;

    public User(int id, String firstName, String lastName, String address,  String city,  String postalCode, String email, String phone_number, boolean isBio, int likes) {
        id_ = id;
        firstName_ = firstName;
        lastName_ = lastName;
        address_ = address;
        city_ = city;
        //lat_ = lat;
        //lon_ = lon;
        postalCode_ = postalCode;
        email_ = email;
        phone_ = phone_number;
        isBio_ = isBio;
        likes_ = likes;
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



    public String getPostalCode() {return postalCode_;}

    public double getLon() {return lon_;}

    public double getLat() {return lat_;}

    public String getEmail() {
        return email_;
    }

    public String getPhoneNumber() {
        return phone_;
    }

    public Boolean isBio() {
        return isBio_;
    }

    public int getLikes() {
        return likes_;
    }
}
