package at.sw2017xp3.regionalo.model;

import java.util.ArrayList;
import java.util.List;

import at.sw2017xp3.regionalo.model.enums.Categories;
import at.sw2017xp3.regionalo.model.enums.Seller;
import at.sw2017xp3.regionalo.model.enums.Transfer;

public class Filter {

    private int distance_;
    private String query_;
    private boolean isBio_;

    private List<Categories> categories_ = new ArrayList<>();
    private List<Seller> seller_ = new ArrayList<>();
    private List<Transfer> transfer_ = new ArrayList<>();
    private String sort_;

    public int getDistance_() {
        return distance_;
    }

    public void setDistance_(int distance_) {
        this.distance_ = distance_;
    }

    public boolean isBio() {
        return isBio_;
    }

    public void setBio(boolean bio_) {
        isBio_ = bio_;
    }

    public List<Categories> getCategories() {
        return categories_;
    }

    public void setCategories(List<Categories> categories_) {
        this.categories_ = categories_;
    }

    public List<Seller> getSeller() {
        return seller_;
    }

    public void setSeller(List<Seller> seller_) {
        this.seller_ = seller_;
    }

    public void SetQuery(String text)
    {
        query_ = text;
    }

    public String getQuery(){return query_;}

    public List<Transfer> getTransfer() {
        return transfer_;
    }

    public void setTransfer_(List<Transfer> transfer_) {
        this.transfer_ = transfer_;
    }

    public Filter(int distance)
    {
        distance_ = distance;
        isBio_ = false;
    }
}
