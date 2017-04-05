package at.sw2017xp3.regionalo;

import org.junit.Test;
import org.json.JSONObject;

import at.sw2017xp3.regionalo.model.Product;
import at.sw2017xp3.regionalo.util.JsonObjectMapper;

import static org.junit.Assert.*;

/**
 * Created by jo on 05.04.17.
 */

public class JsonObjectMapperTest {
    @Test
    public void checkCreateProductFromJson() throws Exception {

        JSONObject obj = new JSONObject();

        int id = 1;
        String name = "Vulkanland Speck";
        int isBio = 1;
        double price = 11.1;
        int producerId = 4;
        int typeID = 1;

        obj.put("id", id);
        obj.put("name", name);
        obj.put("is_bio", isBio);
        obj.put("price", price);
        obj.put("user_id", producerId);
        obj.put("type_id", typeID);

        Product p = JsonObjectMapper.CreateProduct(obj);

        assertTrue(id == p.getId());
        assertTrue(name == p.getName());
        assertTrue((isBio == 1) == p.isBio());
        assertTrue(price == p.getPrice());
        assertTrue(typeID == p.getType());
        assertTrue(producerId == p.getProducerId());

    }
}
