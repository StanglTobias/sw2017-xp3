package at.sw2017xp3.regionalo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import at.sw2017xp3.regionalo.model.Core;
import at.sw2017xp3.regionalo.model.Filter;
import at.sw2017xp3.regionalo.model.Product;
import at.sw2017xp3.regionalo.model.enums.Categories;
import at.sw2017xp3.regionalo.model.enums.ProductSorting;
import at.sw2017xp3.regionalo.util.CommonUi;

public class SearchResultActivity extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private ArrayList<View> list_of_elements = new ArrayList<>();
    ExpandableRelativeLayout expandableLayout;

    GoogleApiClient mGoogleApiClient;

    @Override
    public void onConnected(Bundle connectionHint) {

        try {
            Regionalo.getInstance().setLastKnownLocation(
                    LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient));

        } catch (SecurityException se) {
            ;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }


        Button expandButton
                = (Button) findViewById(R.id.expand);
        expandButton.setOnClickListener(this);

        expandButton.performClick();

        ArrayList<View> buttons = new ArrayList<>();
        buttons.addAll(Arrays.asList(
                findViewById(R.id.Button_ID_ExtendedSearchStart),
                findViewById(R.id.Button_ID_ExtendedSearchStart2),
                findViewById(R.id.Button_ID_ResetFilterExtendedSearch)));

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setOnClickListener(this);
        }

        SearchView sv = (SearchView) findViewById(R.id.searchViewResult);


        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ((ExpandableRelativeLayout) findViewById(R.id.expandableLayout)).collapse();
                new GetProductTask().execute(getFilter());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        ((SeekBar) findViewById(R.id.seekBar_ID_Entfernung))
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        ((EditText) findViewById(R.id.text_ID_Entfernung))
                                .setText(getString(R.string.distance) + String.valueOf(progress + 20) +
                                        getString(R.string.km));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });

        Bundle b = getIntent().getExtras();
        String query = getString(R.string.empty);
        if (b != null) {
            if (b.containsKey(getString(R.string.query))) {
                query = b.getString(getString(R.string.query));

            }
            if (b.containsKey(getString(R.string.category_))) {
                Categories c = Categories.fromInt(b.getInt("category"));

                switch (c) {
                    case CEREALS:
                        ((CheckBox) findViewById(R.id.cb_category_5)).setChecked(true);
                        break;
                    case FRUIT:
                        ((CheckBox) findViewById(R.id.cb_category_3)).setChecked(true);
                        break;
                    case MEAT:
                        ((CheckBox) findViewById(R.id.cb_category_1)).setChecked(true);
                        break;
                    case MILKPRODUCTS:
                        ((CheckBox) findViewById(R.id.cb_category_4)).setChecked(true);
                        break;
                    case OTHERS:
                        ((CheckBox) findViewById(R.id.cb_category_6)).setChecked(true);
                        break;
                    case VEGETABLE:
                        ((CheckBox) findViewById(R.id.cb_category_2)).setChecked(true);
                        break;
                }
            }
            sv.setQuery(query, false);
            new GetProductTask().execute(getFilter());
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    private class GetProductTask extends AsyncTask<Filter, Void, ArrayList<Product>>
            implements View.OnClickListener {

        @Override
        protected ArrayList<Product> doInBackground(Filter... params) {
            try {
                return Core.getInstance().getProducts().getSearchedProducts(params[0]);
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Product> result) {

            try {

                if (result == null || result.isEmpty()) {
                    CharSequence text = getString(R.string.nothingFound);
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    toast.show();
                }

                LinearLayout linearLayoutHome = (LinearLayout) findViewById(R.id.linearLayoutProductPresentation);
                linearLayoutHome.removeAllViews();

               int index = ( (Spinner) findViewById(R.id.Spinner_ID_ExtendedSearch)).getSelectedItemPosition();



              ProductComperator pc =  new ProductComperator(ProductSorting.fromInt(index));
                Collections.sort(result, pc);
                for (Product p :   result) {
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

            Intent myIntent = new Intent(SearchResultActivity.this, ProductDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt(getString(R.string.id), productId);
            myIntent.putExtras(bundle);
            startActivity(myIntent);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the overflow_menu; this adds items to the action bar if it is present.
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
            startActivity(intent);}

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Filter filter;
        switch (v.getId()) {
            case R.id.expand:
                ((ExpandableRelativeLayout) findViewById(R.id.expandableLayout)).toggle();
                break;
            case R.id.Button_ID_ExtendedSearchStart:
                filter = getFilter();
                ((ExpandableRelativeLayout) findViewById(R.id.expandableLayout)).collapse();
                new GetProductTask().execute(getFilter());
                break;
            case R.id.Button_ID_ExtendedSearchStart2:
                filter = getFilter();
                ((ExpandableRelativeLayout) findViewById(R.id.expandableLayout)).toggle();
                new GetProductTask().execute(getFilter());
                break;
            case R.id.Button_ID_ResetFilterExtendedSearch: {
              /*  LinearLayout searchResultLayout = ((LinearLayout) findViewById(R.id.linearLayoutSearchResult));
                for (int i = 0; i < searchResultLayout.getChildCount(); i++) {
                    if (searchResultLayout.getChildAt(i) instanceof CheckBox) {
                        ((CheckBox) searchResultLayout.getChildAt(i)).setChecked(false);
                    }
                }*/
                LinearLayout ll_bio = ((LinearLayout) findViewById(R.id.ll_bio));
                LinearLayout ll_category = ((LinearLayout) findViewById(R.id.ll_category));
                LinearLayout ll_seller = ((LinearLayout) findViewById(R.id.ll_seller));
                LinearLayout ll_transfer = ((LinearLayout) findViewById(R.id.ll_transfer));
                for (int i = 0; i < ll_category.getChildCount(); i++) {
                    if (ll_category.getChildAt(i) instanceof CheckBox) {
                        ((CheckBox) ll_category.getChildAt(i)).setChecked(false);
                    }
                }
                for (int i = 0; i < ll_seller.getChildCount(); i++) {
                    if (ll_seller.getChildAt(i) instanceof CheckBox) {
                        ((CheckBox) ll_seller.getChildAt(i)).setChecked(false);
                    }
                }
                for (int i = 0; i < ll_transfer.getChildCount(); i++) {
                    if (ll_transfer.getChildAt(i) instanceof CheckBox) {
                        ((CheckBox) ll_transfer.getChildAt(i)).setChecked(false);
                    }
                }

                for (int i = 0; i < ll_bio.getChildCount(); i++) {
                    if (ll_transfer.getChildAt(i) instanceof CheckBox) {
                        ((CheckBox) ll_bio.getChildAt(i)).setChecked(false);
                    }
                }
            }
            break;
            default:
                break;
        }
    }

    private Filter getFilter() {

        Filter filter = new Filter(((SeekBar) findViewById(R.id.seekBar_ID_Entfernung)).getProgress() + 20);
        filter.setBio(((CheckBox) findViewById(R.id.checkBox_ID_BiologischerAnbau)).isChecked());
        filter.setQuery(((SearchView) findViewById(R.id.searchViewResult)).getQuery().toString());

        List<Categories> categories = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            int resID = getResources().getIdentifier(getString(R.string.cb_category_) + i, getString(R.string.id), getPackageName());
            if (((CheckBox) findViewById(resID)).isChecked()) {
                categories.add(Categories.fromInt(i));
            }
        }

        for (int i = 0; i < 2; i++) {
            int resID = getResources().getIdentifier(getString(R.string.cb_seller_) + i, getString(R.string.id), getPackageName());
            if (((CheckBox) findViewById(resID)).isChecked()) {
                categories.add(Categories.fromInt(i));
            }
        }

        for (int i = 0; i < 3; i++) {
            int resID = getResources().getIdentifier(getString(R.string.cb_transfer_) + i, getString(R.string.id), getPackageName());
            if (((CheckBox) findViewById(resID)).isChecked()) {
                categories.add(Categories.fromInt(i));
            }
        }

        filter.setCategories(categories);
        return filter;
    }


    public class ProductComperator implements Comparator<Product> {

        ProductSorting sorting_;

        ProductComperator(ProductSorting sorting) {
            sorting_ = sorting;
        }

        @Override
        public int compare(Product o1, Product o2) {

            switch (sorting_) {
                case POPULARITY:
                    return o1.getLikes() - o2.getLikes();
                case PRICE_ASC:
                    return (int) (o1.getPrice() - o2.getPrice());
                case PRICE_DESC:
                    return (int) (o2.getPrice() - o1.getPrice());
                case ALPHATEICAL_DESC:
                    return o2.getName().compareTo(o1.getName());
                default:
                    return o1.getName().compareTo(o2.getName());
            }
        }
    }
}