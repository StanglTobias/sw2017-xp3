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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

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

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<View> list_of_elements = new ArrayList<>();
    private Product product_;
    private int like_button_counter_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_detailes);

        Bundle b = getIntent().getExtras();
        int id = 1; // or other values
        if (b != null)
            id = b.getInt(getString(R.string.id));

        findViewById(R.id.buttonLike).setEnabled(false);


        list_of_elements.addAll(Arrays.asList(
                findViewById(R.id.buttonLike),
                findViewById(R.id.ButtonContact)));

        for (int i = 0; i < list_of_elements.size(); i++) {
            list_of_elements.get(i).setOnClickListener(this);
        }

        ImageView product_image = (ImageView) findViewById(R.id.iv_product);
        Glide.with(getApplicationContext()).load(Core.getInstance().getProducts().getImageUri(id)).into(product_image);

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
            Product p = product_ = result;

            ((TextView) findViewById(R.id.textViewProductName)).setText(p.getName());
            ((TextView) findViewById(R.id.textViewPrice)).setText("€" + Double.toString(p.getPrice()) + "/" + p.getUnit());
            ((TextView) findViewById(R.id.textViewQuality)).setText("Biologisch: " + isBio(p.isBio()));
            ((TextView) findViewById(R.id.textViewCategroy)).setText("Kategorie: " + productCategorieName(p.getType()));

            User user = p.getUser();
            ((TextView) findViewById(R.id.textViewName)).setText(user.getFullName());
            ((TextView) findViewById(R.id.textViewAdress)).setText(user.getPostalCode() + " " + user.getCity() + "\n" + user.getAddress());
            ((TextView) findViewById(R.id.textViewNumber)).setText(user.getPhoneNumber());
            ((TextView) findViewById(R.id.textViewEmail)).setText(user.getEmail());
            ((TextView) findViewById(R.id.textViewLikeCount)).setText(Integer.toString(p.getLikes()));

            findViewById(R.id.buttonLike).setEnabled(!p.CurrentUserHasLiked());
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
        if (id == R.id.buttonMenuLogin) {
            Intent myIntent = new Intent(this, LoginActivity.class);
            startActivity(myIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;

        switch (clickedButton.getId()) {
            case R.id.buttonLike:
                if(product_ != null && !product_.CurrentUserHasLiked())
                    new LikeTask().execute(product_.getId());
                break;

            case R.id.ButtonContact:
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + ((TextView) findViewById(R.id.textViewEmail)).getText().toString())); // only email apps should handle this
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
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
                if (result) {
                    if (product_ != null) {

                        ((TextView) findViewById(R.id.textViewLikeCount)).setText(Integer.toString(product_.getLikes()));
                        ((Button) findViewById(R.id.buttonLike)).setEnabled(false);
                    }
                }
            } catch (Exception e) {
                System.out.println("Halt Stop");
            }
        }
    }

    public String isBio(boolean yes_or_no) {
        if (yes_or_no == true)
            return getString(R.string.yes);
        else
            return getString(R.string.no);
    }

    public String productCategorieName(int type_id) {
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
