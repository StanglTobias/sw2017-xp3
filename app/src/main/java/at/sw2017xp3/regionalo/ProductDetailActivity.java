package at.sw2017xp3.regionalo;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;

import at.sw2017xp3.regionalo.model.Core;
import at.sw2017xp3.regionalo.model.Product;
import at.sw2017xp3.regionalo.model.ProductManager;
import at.sw2017xp3.regionalo.model.User;
import at.sw2017xp3.regionalo.util.HttpUtils;
import at.sw2017xp3.regionalo.util.JsonObjectMapper;

/**
 * Created by Christof on 05.04.2017.
 */

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<View> list_of_elements = new ArrayList<>();
    private int like_button_counter_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getIntent().getExtras();
        int id = 1; // or other values
        if(b != null)
            id = b.getInt("id");

        setContentView(R.layout.activity_product_detailes);

        list_of_elements.addAll(Arrays.asList(
                findViewById(R.id.buttonLike),
                findViewById(R.id.ButtonContact)));

        for (int i = 0; i < list_of_elements.size(); i++) {
            list_of_elements.get(i).setOnClickListener(this);
        }

        Uri uri = Uri.parse("http://sw-ma-xp3.bplaced.net/MySQLadmin/product.php")
                .buildUpon()
                .appendQueryParameter("id", Integer.toString(id)).build();

        new GetProductTask().execute(uri.toString());
    }

    private class GetProductTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                return downloadContent(params[0]);
            } catch (IOException e) {
                return "Unable to retrieve data. URL may be invalid.";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONArray arr = new JSONArray(result); //featured products
                JSONObject mJsonObject = arr.getJSONObject(0);//one product

                String allProductNames;

                Product p = JsonObjectMapper.CreateProduct(mJsonObject);

                ((TextView)findViewById(R.id.textViewProductName)).setText(p.getName());
                ((TextView)findViewById(R.id.textViewPrice)).setText("€" + Double.toString(p.getPrice()) + "/" + p.getUnit());
                ((TextView)findViewById(R.id.textViewQuality)).setText("Biologisch: "  + isBio(p.isBio()));
                ((TextView)findViewById(R.id.textViewCategroy)).setText("Kategorie: " + productCategorieName(p.getType()));

                Uri uri = Uri.parse("http://sw-ma-xp3.bplaced.net/MySQLadmin/user.php")
                        .buildUpon()
                        .appendQueryParameter("id", Integer.toString(p.getProducerId())).build();

                new GetUserTask().execute(uri.toString());
            } catch(Exception e){
                System.out.println("Halt Stop");
            }
        }
    }

    private class GetUserTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                return downloadContent(params[0]);
            } catch (IOException e) {
                return "Unable to retrieve data. URL may be invalid.";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONArray arr = new JSONArray(result); //featured products
                JSONObject mJsonObject = arr.getJSONObject(0);//one product

                String allProductNames;

                User p = JsonObjectMapper.CreateUser(mJsonObject);

                ((TextView)findViewById(R.id.textViewName)).setText(p.getFullName());
                ((TextView)findViewById(R.id.textViewAdress)).setText(p.getPostalCode() + " " + p.getCity() + "\n" + p.getAddress());
                ((TextView)findViewById(R.id.textViewNumber)).setText(p.getPhoneNumber());
              //  ((TextView)findViewById(R.id.textViewLikeCount)).setText(Integer.toString(p.getLikes()));
            } catch(Exception e){
                System.out.println("Halt Stop");
            }
        }
    }

    private String downloadContent(String myurl) throws IOException {
        InputStream is = null;
        int length = 10000;

        try {
            HttpURLConnection conn = HttpUtils.httpGet(myurl);

            String contentAsString = HttpUtils.convertInputStreamToString(conn.getInputStream(), length);

            return contentAsString;
        } catch (Exception ex) {
            return "";
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.loginbutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.buttonLogin) {
            Intent myIntent = new Intent(this, LoginActivity.class);
            startActivity(myIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Button clickedButton = (Button)v;

        switch (clickedButton.getId()){
            case R.id.buttonLike:
                new LikeTask().execute(1);
                /*
              like_button_counter_ =  Integer.valueOf((String)(((TextView)findViewById(R.id.textViewLikeCount)).getText()));
              like_button_counter_++;
              ((TextView)findViewById(R.id.textViewLikeCount)).setText(Integer.toString(like_button_counter_));*/
              break;

            case R.id.ButtonContact:
                //onClick moveTo Website or show contact data
                break;
        }
    }

    private class LikeTask extends AsyncTask<Integer, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Integer... params) {
            try {
                return Core.getInstance().getProducts().like(params[0]);
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            try {
                if(result)
                {
                    Toast.makeText(ProductDetailActivity.this, "GELIKED", Toast.LENGTH_LONG).show();
                }
            } catch(Exception e){
                System.out.println("Halt Stop");
            }
        }
    }

    public static String isBio(boolean yes_or_no) {
        if(yes_or_no == true)
            return "Ja";
        else
            return "Nein";
    }

    public static String productCategorieName (int type_id) {
        switch (type_id) {
            case 1:
                return "Fleisch";
            case 2:
                return "Obst";
            case 3:
                return"Gemüse";
            case 4:
                return "Milchprodukte";
            case 5:
                return "Getreide";
            case 6:
                return "Sonstiges";
        }
        return "";
    }
}
