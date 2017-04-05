package at.sw2017xp3.regionalo.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import at.sw2017xp3.regionalo.model.Product;

/**
 * Created by jo on 05.04.17.
 */

public class JsonObjectMapper {
    private JsonObjectMapper() {
    }

    public static Product CreateProduct(JSONObject object) throws JSONException {

        int id = object.getInt("id");
        String name = object.getString("name");
        boolean isBio = object.getInt("is_bio") == 1;
        double price = object.getDouble("price");
        int producerId = object.getInt("user_id");
        int typeID = object.getInt("type_id");

        return new Product(id, name, isBio, price, producerId, typeID);
    }
}
