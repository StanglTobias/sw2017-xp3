package at.sw2017xp3.regionalo.model;

import java.util.ArrayList;
import java.util.List;

import at.sw2017xp3.regionalo.model.enums.Categories;

public class Filter {

    private int distance_;
    private String query_;
    private boolean isBio_;

    private List<Categories> categories_ = new ArrayList<>();
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

    public void setQuery(String text)
    {
        query_ = text;
    }

    public String getQuery(){return query_;}

    public Filter(int distance)
    {
        distance_ = distance;
        isBio_ = false;
    }
}
