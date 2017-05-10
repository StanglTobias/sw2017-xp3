package at.sw2017xp3.regionalo;

import org.junit.Test;
import org.json.JSONObject;

import at.sw2017xp3.regionalo.model.Product;
import at.sw2017xp3.regionalo.model.User;
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
        String unit = "Kg";
        int likes = 51;

        obj.put("id", id);
        obj.put("name", name);
        obj.put("is_bio", isBio);
        obj.put("price", price);
        obj.put("user_id", producerId);
        obj.put("type_id", typeID);
        obj.put("unit_type", unit);
        obj.put("likes", likes);

        Product p = JsonObjectMapper.CreateProduct(obj);

        assertTrue(id == p.getId());
        assertTrue(name == p.getName());
        assertTrue((isBio == 1) == p.isBio());
        assertTrue(price == p.getPrice());
        assertTrue(typeID == p.getType());
        assertTrue(producerId == p.getProducerId());
        assertTrue(unit == p.getUnit());
        assertTrue(likes == p.getLikes());
    }

    @Test
    public void checkCreateUserFromJson() throws Exception {

        JSONObject obj = new JSONObject();

        int id = 1;
        String firstName = "Recep";
        String lastName = "Erdogan";
        String address = "Herrengasse 44";
        String city = "Graz";
        String postalCode = "8010";
        String email = "Reci1@gmail.com";
        String phone = "066453245234";
        int isBio = 1;
        int likes = 51;

        obj.put("id", id);
        obj.put("first_name", firstName);
        obj.put("last_name", lastName);
        obj.put("address", address);
        obj.put("city", city);
        obj.put("postal_code", postalCode);
        obj.put("email", email);
        obj.put("phone_number", phone);
        obj.put("is_bio", isBio);
        obj.put("likes", likes);

        User u = JsonObjectMapper.CreateUser(obj);

        assertTrue(id == u.getId());
        assertTrue((firstName + " " + lastName).equals(u.getFullName()));
        assertTrue(address.equals(u.getAddress()));
        assertTrue(city.equals(u.getCity()));
        assertTrue(postalCode.equals(u.getPostalCode()));
        assertTrue(email.equals(u.getEmail()));
        assertTrue(phone.equals(u.getPhoneNumber()));
        assertTrue((isBio == 1) == u.isBio());

    }
}
