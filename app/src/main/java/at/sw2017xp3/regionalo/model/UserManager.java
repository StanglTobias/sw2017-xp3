package at.sw2017xp3.regionalo.model;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ConcurrentHashMap;;import at.sw2017xp3.regionalo.util.HttpUtils;
import at.sw2017xp3.regionalo.util.JsonObjectMapper;

/**
 * Created by jo on 26.04.17.
 */

public class UserManager {
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
        if(!cache_.isEmpty())
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
        }
        return p;
    }
}
