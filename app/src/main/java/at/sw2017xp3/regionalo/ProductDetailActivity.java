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

        new GetProductTask().execute(id);
    }

    private class GetProductTask extends AsyncTask<Integer, Void, Product> {

        @Override
        protected Product doInBackground(Integer... params) {
            try {
                return Core.getInstance().getProducts().getProduct(params[0]);
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Product result) {
            try {

                Product p = result;

                ((TextView)findViewById(R.id.textViewProductName)).setText(p.getName());
                ((TextView)findViewById(R.id.textViewPrice)).setText("€" + Double.toString(p.getPrice()) + "/" + p.getUnit());
                ((TextView)findViewById(R.id.textViewQuality)).setText("Biologisch: "  + isBio(p.isBio()));
                ((TextView)findViewById(R.id.textViewCategroy)).setText("Kategorie: " + productCategorieName(p.getType()));

                User u = p.getUser();

                ((TextView)findViewById(R.id.textViewName)).setText(u.getFullName());
                ((TextView)findViewById(R.id.textViewAdress)).setText(u.getPostalCode() + " " + u.getCity() + "\n" + u.getAddress());
                ((TextView)findViewById(R.id.textViewNumber)).setText(u.getPhoneNumber());
                ((TextView)findViewById(R.id.textViewLikeCount)).setText(Integer.toString(u.getLikes()));


            } catch(Exception e){
                System.out.println("Halt Stop");
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
              like_button_counter_ =  Integer.valueOf((String)(((TextView)findViewById(R.id.textViewLikeCount)).getText()));
              like_button_counter_++;
              ((TextView)findViewById(R.id.textViewLikeCount)).setText(Integer.toString(like_button_counter_));
              break;

            case R.id.ButtonContact:
                //onClick moveTo Website or show contact data
                break;
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
