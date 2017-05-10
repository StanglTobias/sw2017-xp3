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
import android.widget.ImageButton;
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
    private Product product_;
    private int like_button_counter_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_detailes);

        Bundle b = getIntent().getExtras();
        int id = 1; // or other values
        if(b != null)
            id = b.getInt(getString(R.string.id));

        findViewById(R.id.buttonLike).setEnabled(false);


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

                Product p = product_= result;

                ((TextView)findViewById(R.id.textViewProductName)).setText(p.getName());
                ((TextView)findViewById(R.id.textViewPrice)).setText(getString(R.string.euro) + Double.toString(p.getPrice()) + getString(R.string.slash) + p.getUnit());
                ((TextView)findViewById(R.id.textViewQuality)).setText(getString(R.string.biologisch)  + isBio(p.isBio()));
                ((TextView)findViewById(R.id.textViewCategroy)).setText(getString(R.string.kategorie) + productCategorieName(p.getType()));

                User u = p.getUser();

                ((TextView)findViewById(R.id.textViewName)).setText(u.getFullName());
                ((TextView)findViewById(R.id.textViewAdress)).setText(u.getPostalCode() + " " + u.getCity() + "\n" + u.getAddress());
                ((TextView)findViewById(R.id.textViewNumber)).setText(u.getPhoneNumber());
                ((TextView)findViewById(R.id.textViewLikeCount)).setText(Integer.toString(p.getLikes()));


                findViewById(R.id.buttonLike).setEnabled(!p.CurrentUserHasLiked());


            } catch(Exception e){
                System.out.println(getString(R.string.stop));
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
                if(product_ != null && !product_.CurrentUserHasLiked())
                    new LikeTask().execute(product_.getId());

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
                    if(product_!= null) {

                        ((TextView)findViewById(R.id.textViewLikeCount)).setText(Integer.toString(product_.getLikes()));
                        ((Button) findViewById(R.id.buttonLike)).setEnabled(false);
                    }
                }
            } catch(Exception e){
                System.out.println("Halt Stop");
            }
        }
    }

    public String isBio(boolean yes_or_no) {
        if(yes_or_no == true)
            return getString(R.string.yes);
        else
            return getString(R.string.no);
    }

    public String productCategorieName (int type_id) {
        switch (type_id) {
            case 1:
                return getString(R.string.meat);
            case 2:
                return getString(R.string.fruits);
            case 3:
                return getString(R.string.vegetables);
            case 4:
                return getString(R.string.dairy);
            case 5:
                return getString(R.string.wheat);
            case 6:
                return getString(R.string.other);
        }
        return "";
    }
}
