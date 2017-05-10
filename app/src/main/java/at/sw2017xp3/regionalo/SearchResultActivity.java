package at.sw2017xp3.regionalo;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import at.sw2017xp3.regionalo.model.Core;
import at.sw2017xp3.regionalo.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchResultActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<View> list_of_elements = new ArrayList<>();
    ExpandableRelativeLayout expandableLayout;

    Button button_extended_search_start;
    Button button_extended_search_start2;
    Button button_reset_filter;

    private CheckBox checkBox_ID_BiologischerAnbau_;
    private CheckBox checkBox_ID_KategorieObst_;
    private CheckBox checkBox_ID_KategorieGemüse_;
    private CheckBox checkBox_ID_KategoriePilze_;
    private CheckBox checkBox_ID_KategoriePlanzenUndSamen_;
    private CheckBox checkBox_ID_KategorieHolz_;
    private CheckBox checkBox_ID_WeitereGartenprodukte_;
    private CheckBox checkBox_ID_Burgenland_;
    private CheckBox checkBox_ID_Kaernten_;
    private CheckBox checkBox_ID_Niederoesterreich_;
    private CheckBox checkBox_ID_Oberoesterreich_;
    private CheckBox checkBox_ID_Salzburg_;
    private CheckBox checkBox_ID_Steiermark_;
    private CheckBox checkBox_ID_Tirol_;
    private CheckBox checkBox_ID_Vorarlberg_;
    private CheckBox checkBox_ID_Wien_;
    private CheckBox checkBox_ID_Privat_;
    private CheckBox checkBox_ID_Firma_;
    private CheckBox checkBox_ID_Zustellung_;
    private CheckBox checkBox_ID_Selbstabholung_;
    private CheckBox checkBox_ID_NichtBenoetigt_;
    private CheckBox checkBox_ID_BereitsGeerntet_;
    private CheckBox checkBox_ID_SelbstErnten_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Bundle b = getIntent().getExtras();
        String query = null; // or other values
        if(b != null)
        {
            query = b.getString(getString(R.string.query));
            setFilter(b);
        }



        expandableLayout
                = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout);
        Button expandButton
                = (Button) findViewById(R.id.expand);
        expandButton.setOnClickListener(this);

        expandButton.performClick();

        button_extended_search_start = (Button) findViewById(R.id.Button_ID_ExtendedSearchStart);
        button_extended_search_start.setOnClickListener(this);

        button_extended_search_start2 = (Button) findViewById(R.id.Button_ID_ExtendedSearchStart2);
        button_extended_search_start2.setOnClickListener(this);

        button_reset_filter = (Button) findViewById(R.id.Button_ID_ResetFilterExtendedSearch);
        button_reset_filter.setOnClickListener(this);

        checkBox_ID_BiologischerAnbau_ = (CheckBox) findViewById(R.id.checkBox_ID_BiologischerAnbau);
        checkBox_ID_KategorieObst_ = (CheckBox) findViewById(R.id.checkBox_ID_KategorieObst);
        checkBox_ID_KategorieGemüse_ = (CheckBox) findViewById(R.id.checkBox_ID_KategorieGemüse);
        checkBox_ID_KategoriePilze_ = (CheckBox) findViewById(R.id.checkBox_ID_KategoriePilze);
        checkBox_ID_KategoriePlanzenUndSamen_ = (CheckBox) findViewById(R.id.checkBox_ID_KategoriePlanzenUndSamen);
        checkBox_ID_KategorieHolz_ = (CheckBox) findViewById(R.id.checkBox_ID_KategorieHolz);
        checkBox_ID_WeitereGartenprodukte_ = (CheckBox) findViewById(R.id.checkBox_ID_WeitereGartenprodukte);
        checkBox_ID_Burgenland_ = (CheckBox) findViewById(R.id.checkBox_ID_Burgenland);
        checkBox_ID_Kaernten_ = (CheckBox) findViewById(R.id.checkBox_ID_Kaernten);
        checkBox_ID_Niederoesterreich_ = (CheckBox) findViewById(R.id.checkBox_ID_Niederoesterreich);
        checkBox_ID_Oberoesterreich_ = (CheckBox) findViewById(R.id.checkBox_ID_Oberoesterreich);
        checkBox_ID_Salzburg_ = (CheckBox) findViewById(R.id.checkBox_ID_Salzburg);
        checkBox_ID_Steiermark_ = (CheckBox) findViewById(R.id.checkBox_ID_Steiermark);
        checkBox_ID_Tirol_ = (CheckBox) findViewById(R.id.checkBox_ID_Tirol);
        checkBox_ID_Vorarlberg_ = (CheckBox) findViewById(R.id.checkBox_ID_Vorarlberg);
        checkBox_ID_Wien_ = (CheckBox) findViewById(R.id.checkBox_ID_Wien);
        checkBox_ID_Privat_ = (CheckBox) findViewById(R.id.checkBox_ID_Privat);
        checkBox_ID_Firma_ = (CheckBox) findViewById(R.id.checkBox_ID_Firma);
        checkBox_ID_Zustellung_ = (CheckBox) findViewById(R.id.checkBox_ID_Zustellung);
        checkBox_ID_Selbstabholung_ = (CheckBox) findViewById(R.id.checkBox_ID_Selbstabholung);
        checkBox_ID_NichtBenoetigt_ = (CheckBox) findViewById(R.id.checkBox_ID_NichtBenoetigt);
        checkBox_ID_BereitsGeerntet_ = (CheckBox) findViewById(R.id.checkBox_ID_BereitsGeerntet);
        checkBox_ID_SelbstErnten_ = (CheckBox) findViewById(R.id.checkBox_ID_SelbstErnten);


        SearchView sv = (SearchView) findViewById(R.id.searchViewResult);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                Intent myIntent = new Intent(SearchResultActivity.this, SearchResultActivity.class);
                if (!query.isEmpty()) {

                    Bundle bundle = new Bundle();
                    saveFilterPreset(bundle);
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

        Uri uri = Uri.parse(getString(R.string.phpLink) + query);

        new GetProductTask().execute(query);

    }

    private void setFilter(Bundle bundle) {

        ((CheckBox) findViewById(R.id.checkBox_ID_BiologischerAnbau)).setChecked(
                bundle.getBoolean("checkBox_ID_BiologischerAnbau", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_KategorieObst)).setChecked(
                bundle.getBoolean("checkBox_ID_KategorieObst", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_KategorieGemüse)).setChecked(
                bundle.getBoolean("checkBox_ID_KategorieGemüse", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_KategoriePilze)).setChecked(
                bundle.getBoolean("checkBox_ID_KategoriePilze", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_KategoriePlanzenUndSamen)).setChecked(
                bundle.getBoolean("checkBox_ID_KategoriePlanzenUndSamen", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_WeitereGartenprodukte)).setChecked(
                bundle.getBoolean("checkBox_ID_WeitereGartenprodukte", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_KategorieHolz)).setChecked(
                bundle.getBoolean("checkBox_ID_KategorieHolz", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_Burgenland)).setChecked(
                bundle.getBoolean("checkBox_ID_Burgenland", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_Kaernten)).setChecked(
                bundle.getBoolean("checkBox_ID_Kaernten", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_Niederoesterreich)).setChecked(
                bundle.getBoolean("checkBox_ID_Niederoesterreich", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_Oberoesterreich)).setChecked(
                bundle.getBoolean("checkBox_ID_Oberoesterreich", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_Salzburg)).setChecked(
                bundle.getBoolean("checkBox_ID_Salzburg", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_Steiermark)).setChecked(
                bundle.getBoolean("checkBox_ID_Steiermark", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_Tirol)).setChecked(
                bundle.getBoolean("checkBox_ID_Tirol", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_Vorarlberg)).setChecked(
                bundle.getBoolean("checkBox_ID_Vorarlberg", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_Wien)).setChecked(
                bundle.getBoolean("checkBox_ID_Wien", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_Privat)).setChecked(
                bundle.getBoolean("checkBox_ID_Privat", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_Firma)).setChecked(
                bundle.getBoolean("checkBox_ID_Firma", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_Zustellung)).setChecked(
                bundle.getBoolean("checkBox_ID_Zustellung", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_Selbstabholung)).setChecked(
                bundle.getBoolean("checkBox_ID_Selbstabholung", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_NichtBenoetigt)).setChecked(
                bundle.getBoolean("checkBox_ID_NichtBenoetigt", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_BereitsGeerntet)).setChecked(
                bundle.getBoolean("checkBox_ID_BereitsGeerntet", false));
        ((CheckBox) findViewById(R.id.checkBox_ID_SelbstErnten)).setChecked(
                bundle.getBoolean("checkBox_ID_SelbstErnten", false));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sort_arrays, R.layout.activity_search_result);
        ((Spinner) findViewById(R.id.Spinner_ID_ExtendedSearch)).setSelection(adapter.getPosition(
                bundle.getString("Spinner_ID_ExtendedSearch", "")
        ));
    }

    private void saveFilterPreset(Bundle bundle) {

        bundle.putBoolean("checkBox_ID_BiologischerAnbau", ((CheckBox) findViewById(R.id.checkBox_ID_BiologischerAnbau)).isChecked());
        bundle.putBoolean("checkBox_ID_KategorieObst", ((CheckBox) findViewById(R.id.checkBox_ID_KategorieObst)).isChecked());
        bundle.putBoolean("checkBox_ID_KategorieGemüse", ((CheckBox) findViewById(R.id.checkBox_ID_KategorieGemüse)).isChecked());
        bundle.putBoolean("checkBox_ID_KategoriePilze", ((CheckBox) findViewById(R.id.checkBox_ID_KategoriePilze)).isChecked());
        bundle.putBoolean("checkBox_ID_KategoriePlanzenUndSamen", ((CheckBox) findViewById(R.id.checkBox_ID_KategoriePlanzenUndSamen)).isChecked());
        bundle.putBoolean("checkBox_ID_KategorieHolz", ((CheckBox) findViewById(R.id.checkBox_ID_KategorieHolz)).isChecked());
        bundle.putBoolean("checkBox_ID_WeitereGartenprodukte", ((CheckBox) findViewById(R.id.checkBox_ID_WeitereGartenprodukte)).isChecked());
        bundle.putBoolean("checkBox_ID_Burgenland", ((CheckBox) findViewById(R.id.checkBox_ID_Burgenland)).isChecked());
        bundle.putBoolean("checkBox_ID_Kaernten", ((CheckBox) findViewById(R.id.checkBox_ID_Kaernten)).isChecked());
        bundle.putBoolean("checkBox_ID_Niederoesterreich", ((CheckBox) findViewById(R.id.checkBox_ID_Niederoesterreich)).isChecked());
        bundle.putBoolean("checkBox_ID_Oberoesterreich", ((CheckBox) findViewById(R.id.checkBox_ID_Oberoesterreich)).isChecked());
        bundle.putBoolean("checkBox_ID_Salzburg", ((CheckBox) findViewById(R.id.checkBox_ID_Salzburg)).isChecked());
        bundle.putBoolean("checkBox_ID_Steiermark", ((CheckBox) findViewById(R.id.checkBox_ID_Steiermark)).isChecked());
        bundle.putBoolean("checkBox_ID_Tirol", ((CheckBox) findViewById(R.id.checkBox_ID_Tirol)).isChecked());
        bundle.putBoolean("checkBox_ID_Vorarlberg", ((CheckBox) findViewById(R.id.checkBox_ID_Vorarlberg)).isChecked());
        bundle.putBoolean("checkBox_ID_Wien", ((CheckBox) findViewById(R.id.checkBox_ID_Wien)).isChecked());
        bundle.putBoolean("checkBox_ID_Privat", ((CheckBox) findViewById(R.id.checkBox_ID_Privat)).isChecked());
        bundle.putBoolean("checkBox_ID_Firma", ((CheckBox) findViewById(R.id.checkBox_ID_Firma)).isChecked());
        bundle.putBoolean("checkBox_ID_Zustellung", ((CheckBox) findViewById(R.id.checkBox_ID_Zustellung)).isChecked());
        bundle.putBoolean("checkBox_ID_Selbstabholung", ((CheckBox) findViewById(R.id.checkBox_ID_Selbstabholung)).isChecked());
        bundle.putBoolean("checkBox_ID_NichtBenoetigt", ((CheckBox) findViewById(R.id.checkBox_ID_NichtBenoetigt)).isChecked());
        bundle.putBoolean("checkBox_ID_BereitsGeerntet", ((CheckBox) findViewById(R.id.checkBox_ID_BereitsGeerntet)).isChecked());
        bundle.putBoolean("checkBox_ID_SelbstErnten", ((CheckBox) findViewById(R.id.checkBox_ID_SelbstErnten)).isChecked());

        bundle.putString("Spinner_ID_ExtendedSearch", ((Spinner) findViewById(R.id.Spinner_ID_ExtendedSearch)).getSelectedItem().toString());
    }


    private class GetProductTask extends AsyncTask<String, Void, ArrayList<Product>> implements View.OnClickListener {

        @Override
        protected ArrayList<Product> doInBackground(String... params) {
            try {
                return Core.getInstance().getProducts().getSearchedProducts(params[0]);
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Product> result) {

            try {

                LinearLayout linearLayoutHome = (LinearLayout) findViewById(R.id.linearLayoutSearchResult);
                for (Product p : result
                        ) {
                    System.out.println(getString(R.string.postExeNameProduct) + p.getName());

                    LayoutInflater inflater = getLayoutInflater();
                    LinearLayout inflatedView = (LinearLayout) inflater.inflate(R.layout.product, linearLayoutHome);

                    int productLayoutId = p.getId();
                    LinearLayout productLayout = (LinearLayout) inflatedView.findViewById(R.id.linearLayout_product);
                    (inflatedView.findViewById(R.id.linearLayout_product)).setId(productLayoutId);

                    (productLayout.findViewById(R.id.imageButtonProduct)).setOnClickListener(this);
                    ((TextView) productLayout.findViewById(R.id.textViewRndProduct1)).setText(p.getName());
                    ((TextView) productLayout.findViewById(R.id.textViewRndProduct2)).setText(getString(R.string.producerID) + String.valueOf(p.getId()));
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

            Intent myIntent = new Intent(SearchResultActivity.this, ProductDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("id", productId);
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
        switch (v.getId()) {
            case R.id.expand:
                expandableLayout.toggle();
                break;
            default:
                break;
        }

        if (v.getId() == R.id.Button_ID_ResetFilterExtendedSearch) {
            checkBox_ID_BiologischerAnbau_.setChecked(false);
            checkBox_ID_KategorieObst_.setChecked(false);
            checkBox_ID_KategorieGemüse_.setChecked(false);
            checkBox_ID_KategoriePilze_.setChecked(false);
            checkBox_ID_KategoriePlanzenUndSamen_.setChecked(false);
            checkBox_ID_KategorieHolz_.setChecked(false);
            checkBox_ID_WeitereGartenprodukte_.setChecked(false);
            checkBox_ID_Burgenland_.setChecked(false);
            checkBox_ID_Kaernten_.setChecked(false);
            checkBox_ID_Niederoesterreich_.setChecked(false);
            checkBox_ID_Oberoesterreich_.setChecked(false);
            checkBox_ID_Salzburg_.setChecked(false);
            checkBox_ID_Steiermark_.setChecked(false);
            checkBox_ID_Tirol_.setChecked(false);
            checkBox_ID_Vorarlberg_.setChecked(false);
            checkBox_ID_Wien_.setChecked(false);
            checkBox_ID_Privat_.setChecked(false);
            checkBox_ID_Firma_.setChecked(false);
            checkBox_ID_Zustellung_.setChecked(false);
            checkBox_ID_Selbstabholung_.setChecked(false);
            checkBox_ID_NichtBenoetigt_.setChecked(false);
            checkBox_ID_BereitsGeerntet_.setChecked(false);
            checkBox_ID_SelbstErnten_.setChecked(false);
        }
    }


}