package at.sw2017xp3.regionalo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
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
import at.sw2017xp3.regionalo.model.CurrentUser;
import at.sw2017xp3.regionalo.util.Installation;
import at.sw2017xp3.regionalo.model.Product;
import at.sw2017xp3.regionalo.util.HttpUtils;
import at.sw2017xp3.regionalo.util.JsonObjectMapper;

import at.sw2017xp3.regionalo.model.enums.Categories;
import at.sw2017xp3.regionalo.util.CommonUi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

import static at.sw2017xp3.regionalo.R.string.space;
import static java.io.File.separator;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<View> list_of_elements = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //get User Identification
        CurrentUser.Init(this);

        list_of_elements.addAll(Arrays.asList(
                findViewById(R.id.buttonMeat),
                findViewById(R.id.buttonVegetables),
                findViewById(R.id.buttonFruit),
                findViewById(R.id.buttonOthers),
                findViewById(R.id.buttonMilk),
                findViewById(R.id.buttonCereals),
                findViewById(R.id.buttonMilk)));

        SearchView sv = (SearchView) findViewById(R.id.searchViewHome);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (!query.isEmpty()) {
                    Intent myIntent = new Intent(HomeActivity.this, SearchResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(getString(R.string.query), query);
                    myIntent.putExtras(bundle);

                    startActivity(myIntent);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        new GetProductTask().execute();

        for (int i = 0; i < list_of_elements.size(); i++) {
            list_of_elements.get(i).setOnClickListener(this);
        }
    }

    private class GetProductTask extends AsyncTask<String, Void, ArrayList<Product>> implements View.OnClickListener {

        @Override
        protected ArrayList<Product> doInBackground(String... params) {
            try {
                return Core.getInstance().getProducts().getFeaturedProducts();
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Product> result) {

            try {
                LinearLayout linearLayoutHome = (LinearLayout) findViewById(R.id.linearLayout_Home_Activity);
                for (Product p : result) {
                    LayoutInflater inflater = getLayoutInflater();
                    LinearLayout inflatedView = (LinearLayout) inflater.inflate(R.layout.product, linearLayoutHome);
                    CommonUi.fillProductPresentation(p, inflatedView, this);
                }
            } catch (Exception ex) {
                System.out.println(getString(R.string.productTaskException));
                ex.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {
            ImageButton imageButton = (ImageButton) v;

            LinearLayout productLayout = (LinearLayout) imageButton.getParent();
            int productId = productLayout.getId();

            Intent myIntent = new Intent(HomeActivity.this, ProductDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt(getString(R.string.id), productId);
            myIntent.putExtras(bundle);
            startActivity(myIntent);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return true;
    }


    public boolean onPrepareOptionsMenu(Menu menu)
    {
        if(LoginActivity.loggedIn_) {
            menu.findItem(R.id.buttonProducerRegistration).setVisible(false);
            menu.findItem(R.id.buttonMenuLogin).setVisible(false);
            menu.findItem(R.id.buttonMenuLogout).setVisible(true);
            menu.findItem(R.id.buttonAddProduct).setVisible(true);}
        else {
            menu.findItem(R.id.buttonProducerRegistration).setVisible(true);
            menu.findItem(R.id.buttonMenuLogin).setVisible(true);
            menu.findItem(R.id.buttonMenuLogout).setVisible(false);
            menu.findItem(R.id.buttonAddProduct).setVisible(false);}

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
            startActivity(myIntent);}
        if (id == R.id.buttonMenuLogout) {
            LoginActivity.loggedIn_ = false;
            Intent myIntent = new Intent(this, HomeActivity.class);
            startActivity(myIntent);}
        if (id == R.id.logo) {
            Intent myIntent = new Intent(this, HomeActivity.class);
            startActivity(myIntent);}
        if (id == R.id.buttonProducerRegistration) {
            Intent myIntent = new Intent(this, RegisterActivity.class);
            startActivity(myIntent);}
        if (id == R.id.buttonAddProduct) {
            Intent intent = new Intent(this, ReleaseAdActivity.class);
            intent.putExtra(getString(R.string.loggedUserId), LoginActivity.getLoggedUserId());
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        Categories categorie = Categories.FRUIT;
        boolean hit = true;

        switch (v.getId()) {
            case R.id.buttonMeat:
                categorie = Categories.MEAT;
                break;
            case R.id.buttonVegetables:
                categorie = Categories.VEGETABLE;
                break;
            case R.id.buttonFruit:
                categorie = Categories.FRUIT;
                break;
            case R.id.buttonCereals:
                categorie = Categories.CEREALS;
                break;
            case R.id.buttonMilk:
                categorie = Categories.MILKPRODUCTS;
                break;
            case R.id.buttonOthers:
                categorie = Categories.OTHERS;
                break;
            default:
                hit = false;
                break;
        }
        if (hit) {
            Intent myIntent = new Intent(HomeActivity.this, SearchResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt(getString(R.string.category_), categorie.GetInt());
            myIntent.putExtras(bundle);

            startActivity(myIntent);
        }
    }
}