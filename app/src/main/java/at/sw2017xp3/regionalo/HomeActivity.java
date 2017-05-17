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
import at.sw2017xp3.regionalo.model.Product;

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


                Intent myIntent = new Intent(HomeActivity.this, SearchResultActivity.class);

                if (!query.isEmpty()) {
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
                for (Product p : result
                        ) {
                    System.out.println(getString(R.string.nameofProduct) + p.getName());

                    LayoutInflater inflater = getLayoutInflater();
                    LinearLayout inflatedView = (LinearLayout) inflater.inflate(R.layout.product, linearLayoutHome);

                    int productLayoutId = p.getId();
                    LinearLayout productLayout = (LinearLayout) inflatedView.findViewById(R.id.linearLayout_product);
                    (inflatedView.findViewById(R.id.linearLayout_product)).setId(productLayoutId);

                    ImageButton image_load = (ImageButton) productLayout.findViewById(R.id.imageButtonProduct);
                    image_load.setOnClickListener(this);
                    Glide.with(getApplicationContext()).load(Core.getInstance().getProducts().getImageUri(p.getId())).into(image_load);
                    //Glide.with(this).load("http://goo.gl/gEgYUd").into(image_load);
                    ((TextView) productLayout.findViewById(R.id.textViewRndProduct1)).setText(p.getName());
                    ((TextView) productLayout.findViewById(R.id.textViewRndProduct2)).setText(getString(R.string.productID) + String.valueOf(p.getId()));
                    ((TextView) productLayout.findViewById(R.id.textViewRndProduct3)).setText(getString(R.string.producerID) + String.valueOf(p.getProducerId()));
                    ((TextView) productLayout.findViewById(R.id.textViewRndProduct4)).setText(getString(R.string.productPrice) + String.valueOf(p.getPrice()));
                    ((TextView) productLayout.findViewById(R.id.textViewRndProduct5)).setText(getString(R.string.productType) + String.valueOf(p.getType()));
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
        if(v == findViewById(R.id.buttonMeat)){
            v.setSelected(!v.isSelected());

            if(v.isSelected()){
                SearchView view = (SearchView)findViewById(R.id.searchViewHome);
                view.setQuery(getString((R.string.meat)), true);
            }
        }
        if(v == findViewById(R.id.buttonVegetables)){
            v.setSelected(!v.isSelected());

            if(v.isSelected()){
                SearchView view = (SearchView)findViewById(R.id.searchViewHome);
                view.setQuery(getString((R.string.vegetables)), true);
            }
        }
        if(v == findViewById(R.id.buttonFruit)){
            v.setSelected(!v.isSelected());

            if(v.isSelected()){
                SearchView view = (SearchView)findViewById(R.id.searchViewHome);
                view.setQuery(getString((R.string.fruits)), true);
            }
        }
        if(v == findViewById(R.id.buttonCereals)){
            v.setSelected(!v.isSelected());

            if(v.isSelected()){
                SearchView view = (SearchView)findViewById(R.id.searchViewHome);
                view.setQuery(getString((R.string.wheat)), true);
            }
        }
        if(v == findViewById(R.id.buttonMilk)){
            v.setSelected(!v.isSelected());

            if(v.isSelected()){
                SearchView view = (SearchView)findViewById(R.id.searchViewHome);
                view.setQuery(getString((R.string.dairy)), true);
            }
        }
        if(v == findViewById(R.id.buttonOthers)){
            v.setSelected(!v.isSelected());

            if(v.isSelected()){
                SearchView view = (SearchView)findViewById(R.id.searchViewHome);
                view.setQuery(getString((R.string.other)), true);
            }
        }

        Intent myIntent = new Intent(getBaseContext(), ProductDetailActivity.class);
    }
}