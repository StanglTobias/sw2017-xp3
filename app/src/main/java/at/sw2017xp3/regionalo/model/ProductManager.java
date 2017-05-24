package at.sw2017xp3.regionalo.model;

import android.net.Uri;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import at.sw2017xp3.regionalo.Regionalo;
import at.sw2017xp3.regionalo.model.enums.Categories;
import at.sw2017xp3.regionalo.util.GeoUtils;
import at.sw2017xp3.regionalo.util.HttpUtils;
import at.sw2017xp3.regionalo.util.JsonObjectMapper;

/**
 * Created by jo on 26.04.17.
 */

public class ProductManager {

    ConcurrentHashMap<Integer, Product> cache_ = new ConcurrentHashMap<Integer, Product>();
    public static final String PRODUCT_URI = "http://sw-ma-xp3.bplaced.net/MySQLadmin/product.php";
    public static final String PRODUCT_IMAGE_URI = "http://sw-ma-xp3.bplaced.net/MySQLadmin/image.php";

    public ProductManager() {
    }

    public void addProduct(Product p) {
        if (!cache_.containsKey(p.getId())) {
            cache_.put(p.getId(), p);
        }
    }

    public String getProductUri(int id) {
        Uri uri = Uri.parse(PRODUCT_URI)
                .buildUpon()
                .appendQueryParameter("id", Integer.toString(id)).build();
        return uri.toString();
    }

    public String getImageUri(int id) {
        Uri uri = Uri.parse(PRODUCT_IMAGE_URI)
                .buildUpon()
                .appendQueryParameter("id", Integer.toString(id)).build();
        return uri.toString();
    }

    public Product getProduct(int id) {
        Product p = cache_.get(id);

        if (p != null)
            return p;

        try {
            String content = HttpUtils.downloadContent(getProductUri(id));
            JSONArray arr = new JSONArray(content); //featured products
            JSONObject mJsonObject = arr.getJSONObject(0);//one product

            p = JsonObjectMapper.CreateProduct(mJsonObject);

            if (p.getUser().getLatitude() == 0d) {
                Core.getInstance().getUsers().WriteLongLat(p.getUser());
            }

            addProduct(p);

            p.SetCurrentUserHasLiked(Core.getInstance().getProducts().hasUserLiked(p.getId()));
        } catch (Exception ex) {

            Log.e("MYAPP", "exception", ex);
        }

        return p;
    }

    public boolean hasUserLiked(int pid) {

        Product p = getProduct(pid);

        if (p != null) {

            Uri uri = Uri.parse("http://sw-ma-xp3.bplaced.net/MySQLadmin/userliked.php")
                    .buildUpon()
                    .appendQueryParameter("pid", Integer.toString(p.getId()))
                    .appendQueryParameter("uuid", CurrentUser.getId()).build();

            try {
                String val = HttpUtils.downloadContent(uri.toString(), 1);
                return val.equals("1");

            } catch (Exception ex) {
                return false;
            }
        }
        return false;
    }

    public boolean like(int id) {

        Product p = getProduct(id);

        if (p != null) {

            Uri uri = Uri.parse("http://sw-ma-xp3.bplaced.net/MySQLadmin/like.php")
                    .buildUpon()
                    .appendQueryParameter("pid", Integer.toString(p.getId()))
                    .appendQueryParameter("uuid", CurrentUser.getId()).build();

            try {
                HttpURLConnection urlConn = HttpUtils.httpGet(uri.toString());
                urlConn.connect();

                if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    p.incrementLikes();

                    return true;
                }

            } catch (Exception ex) {
                return false;
            }
        }
        return false;
    }


    public ArrayList<Product> getFeaturedProducts() {

        ArrayList<Product> products = new ArrayList<Product>();

        for (int j = 1; j < 6; j++) {
            Product p = getProduct(j);

            products.add(p);
        }

        return products;
    }

    public ArrayList<Product> getSearchedProducts(Filter filter) {


        ArrayList<Product> products = new ArrayList<Product>();

        Uri.Builder builder = Uri.parse("http://sw-ma-xp3.bplaced.net/MySQLadmin/search.php")
                .buildUpon();

        builder = builder.appendQueryParameter("q", filter.getQuery());

        builder = builder.appendQueryParameter("isbio", filter.isBio() ? "1" : "0");

        builder = builder.appendQueryParameter("dist", Integer.toString(filter.getDistance_()));


        String value = "";
        for (int i = 0; i < 6; i++) {
            if (filter.getCategories().size() - 1 >= i)
                value = Integer.toString(filter.getCategories().get(i).GetInt());

            builder.appendQueryParameter("cat" + Integer.toString(i), value);
        }

        /*
        for (int i = 0; i < 2; i++) {
            if (filter.getTransfer().size() - 1 >= i)
                value = Integer.toString(filter.getTransfer().get(i).GetInt());

            builder.appendQueryParameter("transfer_" + Integer.toString(i), value);

        }
        for (int i = 0; i < 3; i++) {
            if (filter.getSeller().size() - 1 >= i)
                value = Integer.toString(filter.getSeller().get(i).GetInt());

            builder.appendQueryParameter("seller_" + Integer.toString(i), value);
        }*/


        Uri uri = builder.build();

        try {
            String content = HttpUtils.downloadContent(uri.toString());
            System.out.println("content search product " + content);
            JSONArray arr = new JSONArray(content); //featured products

            for (int i = 0; i < arr.length(); i++) {
                JSONObject mJsonObject = arr.getJSONObject(i);
                Product p = JsonObjectMapper.CreateProduct(mJsonObject);
                products.add(p);
            }

        } catch (Exception ex) {

            Log.e("MYAPP", "exception", ex);
        }

        return products;
    }

}