package at.sw2017xp3.regionalo.model;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import at.sw2017xp3.regionalo.util.HttpUtils;
import at.sw2017xp3.regionalo.util.JsonObjectMapper;

/**
 * Created by jo on 26.04.17.
 */

public class ProductManager {

    ConcurrentHashMap<Integer, Product> cache_ = new ConcurrentHashMap<Integer, Product>();

    public ProductManager() {
    }

    public void addProduct(Product p) {
        if (!cache_.containsKey(p.getId())) {
            cache_.put(p.getId(), p);
        }
    }

    public Product getProduct(int id) {
        Product p = cache_.get(id);

        if (p != null)
            return p;


        Uri uri = Uri.parse("http://sw-ma-xp3.bplaced.net/MySQLadmin/product.php")
                .buildUpon()
                .appendQueryParameter("id", Integer.toString(id)).build();

        try {
            String content =  HttpUtils.downloadContent(uri.toString());
            System.out.println("content feature product " + content);
            JSONArray arr = new JSONArray(content); //featured products
            JSONObject mJsonObject = arr.getJSONObject(0);//one product

            p = JsonObjectMapper.CreateProduct(mJsonObject);
            p.getUser();

            addProduct(p);
        } catch (Exception ex) {

            Log.e("MYAPP", "exception", ex);
        }

        return p;
    }

    public ArrayList<Product> getFeaturedProducts() {

        ArrayList<Product> products = new ArrayList<Product>();


        for (int j = 1; j < 6; j++) {
            Product p = getProduct(j);

            products.add(p);
        }


        return products;
    }

    public ArrayList<Product> getSearchedProducts(String searchString) {

        System.out.println("searchstring " +  searchString);

        ArrayList<Product> products = new ArrayList<Product>();

        Uri uri = Uri.parse("http://sw-ma-xp3.bplaced.net/MySQLadmin/search.php")
                .buildUpon()
                .appendQueryParameter("q", searchString).build();

        try {
            String content =  HttpUtils.downloadContent(uri.toString());
            System.out.println("content search product " + content);
            JSONArray arr = new JSONArray(content); //featured products

            for (int i = 0; i < arr.length(); i++)
            {
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