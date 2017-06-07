package at.sw2017xp3.regionalo.model;

import android.net.Uri;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;;import at.sw2017xp3.regionalo.R;
import at.sw2017xp3.regionalo.Regionalo;
import at.sw2017xp3.regionalo.util.GeoUtils;
import at.sw2017xp3.regionalo.util.HttpUtils;
import at.sw2017xp3.regionalo.util.JsonObjectMapper;

/**
 * Created by jo on 26.04.17.
 */

public class
UserManager {
    ConcurrentHashMap<Integer, User> cache_ = new ConcurrentHashMap<Integer, User>();

    public UserManager() {
    }

    public void addUser(User u) {
        if (!cache_.containsKey(u.getId())) {
            cache_.put(u.getId(), u);
        }
    }

    public User getUser(int id) {
        User p = null;
        if (!cache_.isEmpty())
            p = cache_.get(id);

        if (p != null)
            return p;

        Uri uri = Uri.parse("http://sw-ma-xp3.bplaced.net/MySQLadmin/user.php")
                .buildUpon()
                .appendQueryParameter("id", Integer.toString(id)).build();

        System.out.println("id" + id);
        System.out.println("uri" + uri.toString());

        try {
            JSONArray arr = new JSONArray(HttpUtils.downloadContent(uri.toString()));
            JSONObject mJsonObject = arr.getJSONObject(0);
            p = JsonObjectMapper.CreateUser(mJsonObject);

            addUser(p);
        } catch (Exception ex) {
            Log.e("Databas", "exception", ex);
        }
        return p;
    }

    public void WriteLongLat(User u) {
        try {
        LatLng latLng = GeoUtils.getLocationFromAddress(Regionalo.getContext(), u.getAddress());
        u.setLatitude(latLng.latitude);
        u.setLongitude_(latLng.latitude);

        Uri uri = Uri.parse("http://sw-ma-xp3.bplaced.net/MySQLadmin/userlonlat.php")
                .buildUpon()
                .appendQueryParameter("id", Integer.toString(u.getId()))
                .appendQueryParameter("lon", Double.toString(u.getLongitude()))
                .appendQueryParameter("lat", Double.toString(u.getLatitude())).build();


            HttpUtils.downloadContent(uri.toString());
        } catch (Exception ex) {
            Log.e("Databas", "exception", ex);
        }

    }

   public String LogInUser(String userName, String password) {
        try {

            Uri uri = Uri.parse("http://sw-ma-xp3.bplaced.net/MySQLadmin/getLoginData.php")
                    .buildUpon()
                    .appendQueryParameter("username", userName)
                    .appendQueryParameter("password", password).build();


            String result = HttpUtils.downloadContent(uri.toString());

            return result;
        } catch (Exception ex) {
            return "false";
        }

    }
}
