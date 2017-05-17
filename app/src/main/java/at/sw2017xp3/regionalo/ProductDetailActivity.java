package at.sw2017xp3.regionalo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.barcode.Barcode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.sw2017xp3.regionalo.model.Core;
import at.sw2017xp3.regionalo.model.Product;
import at.sw2017xp3.regionalo.model.ProductManager;
import at.sw2017xp3.regionalo.model.User;
import at.sw2017xp3.regionalo.util.GeoUtils;
import at.sw2017xp3.regionalo.util.HttpUtils;
import at.sw2017xp3.regionalo.util.JsonObjectMapper;
import at.sw2017xp3.regionalo.util.OnTaskCompleted;

/**
 * Created by Christof on 05.04.2017.
 */

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener, OnTaskCompleted, OnMapReadyCallback{
    private ArrayList<View> list_of_elements = new ArrayList<>();
    private Product product_;
    private int like_button_counter_;

    Product p;
    GoogleMap googleMap;
    MapFragment mapFragment;



    public boolean googlServicesAvailable(){
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();

        int isAvailable = api.isGooglePlayServicesAvailable(this) ;

        if(isAvailable == ConnectionResult.SUCCESS)
        {
            return true;
        }
        else if(api.isUserResolvableError(isAvailable))
        {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        }

        return false;
    }


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
        if(googlServicesAvailable())
        {
            Toast.makeText(this, "YAY", Toast.LENGTH_LONG).show();
            mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }



        new GetProductTask(this).execute(id);
    }

    @Override
    public void onTaskCompleted() {
        LatLng placeLocation = GeoUtils.getLocationFromAddress(this, p.getUser().getAddress());
        Marker placeMarker = googleMap.addMarker(new MarkerOptions().position(placeLocation)
                .title("test"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(placeLocation));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 1000, null);
    }


    private class GetProductTask extends AsyncTask<Integer, Void, Product> {
        private OnTaskCompleted listener;

        @Override
        protected Product doInBackground(Integer... params) {
            try {
                return Core.getInstance().getProducts().getProduct(params[0]);
            } catch (Exception e) {
                return null;
            }
        }

        public  GetProductTask(OnTaskCompleted listener){
            this.listener=listener;

        }

        @Override
        protected void onPostExecute(Product result) {
            try {

                 p = product_ = result;

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
                listener.onTaskCompleted();

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

    @Override
    public void onMapReady(GoogleMap gMap) {
        googleMap = gMap;
       googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        try {
            googleMap.setMyLocationEnabled(true);
        } catch (SecurityException se) {

        }

        //Edit the following as per you needs
        googleMap.setTrafficEnabled(false);
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        //


    }


}
