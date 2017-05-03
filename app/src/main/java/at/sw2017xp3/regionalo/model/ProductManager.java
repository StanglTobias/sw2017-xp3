package at.sw2017xp3.regionalo.model;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
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

    ConcurrentHashMap<Integer, Integer> like_cache_ = new ConcurrentHashMap<Integer, Integer>();

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
            JSONArray arr = new JSONArray(HttpUtils.downloadContent(uri.toString())); //featured products
            JSONObject mJsonObject = arr.getJSONObject(0);//one product

            p = JsonObjectMapper.CreateProduct(mJsonObject);
            p.getUser();


            addProduct(p);
        } catch (Exception ex) {
        }

        return p;
    }

    public boolean hasLikes(int id) {

        Product p = getProduct(id);

        if (p != null) {

            Uri uri = Uri.parse("http://sw-ma-xp3.bplaced.net/MySQLadmin/userliked.php")
                    .buildUpon()
                    .appendQueryParameter("id", Integer.toString(p.getId()))
                    .appendQueryParameter("uuid", CurrentUser.getId()).build();

            try {
                String val = HttpUtils.downloadContent(uri.toString());
                return val.equals(1);

            } catch (Exception ex) {
                return false;
            }
        }
        return false;
    }

    public boolean like(int id) {

        Product p = getProduct(id);

        if (p != null) {

            Uri uri = Uri.parse("http://sw-ma-xp3.bplaced.net/MySQLadmin/like.php");

            try {
                HttpURLConnection urlConn = HttpUtils.httpPost(uri.toString());

                urlConn.connect();

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(urlConn.getOutputStream()));
                writer.write(GetLikePostJson(1, CurrentUser.getId()).toString());
                writer.flush();
                writer.close();

               if(urlConn.getResponseCode() == HttpURLConnection.HTTP_OK)
               {
                   p.incrementLikes();
                   return  true;
               }

            } catch (Exception ex) {
                return false;
            }
        }
        return false;
    }

    public JSONObject GetLikePostJson(int productId, String Uuid) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("pid", Integer.toString(productId));
            obj.put("uuid", Uuid);

        } catch (JSONException e) {
        }

        return obj;
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
