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
    public static final  String PRODUCT_URI = "http://sw-ma-xp3.bplaced.net/MySQLadmin/product.php";
    public static final String PRODUCT_IMAGE_URI="http://sw-ma-xp3.bplaced.net/MySQLadmin/image.php";

    public ProductManager() {
    }

    public void addProduct(Product p) {
        if (!cache_.containsKey(p.getId())) {
            cache_.put(p.getId(), p);
        }
    }

    public String getProductUri(int id){
        Uri uri = Uri.parse(PRODUCT_URI)
                .buildUpon()
                .appendQueryParameter("id", Integer.toString(id)).build();
        return uri.toString();
    }

    public String getImageUri(int id){
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
            String content =  HttpUtils.downloadContent(getProductUri(id));
            JSONArray arr = new JSONArray(content); //featured products
            JSONObject mJsonObject = arr.getJSONObject(0);//one product

            p = JsonObjectMapper.CreateProduct(mJsonObject);
            p.getUser();

            addProduct(p);
        } catch (Exception ex) {

            //Log.e("MYAPP", "exception", ex);
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

}
