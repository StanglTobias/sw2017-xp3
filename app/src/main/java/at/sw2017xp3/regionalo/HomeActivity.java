package at.sw2017xp3.regionalo;

import android.content.Intent;
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

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;

import at.sw2017xp3.regionalo.model.Core;
import at.sw2017xp3.regionalo.model.CurrentUser;
import at.sw2017xp3.regionalo.model.Filter;
import at.sw2017xp3.regionalo.model.Product;
import at.sw2017xp3.regionalo.model.enums.Categories;
import at.sw2017xp3.regionalo.util.CommonUi;

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
            bundle.putInt("category", categorie.GetInt());
            myIntent.putExtras(bundle);

            startActivity(myIntent);
        }
    }
}