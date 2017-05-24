package at.sw2017xp3.regionalo;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.ArrayList;

import at.sw2017xp3.regionalo.model.Core;
import at.sw2017xp3.regionalo.model.Filter;
import at.sw2017xp3.regionalo.model.Product;
import at.sw2017xp3.regionalo.model.enums.Categories;
import at.sw2017xp3.regionalo.model.enums.Seller;
import at.sw2017xp3.regionalo.model.enums.Transfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResultActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<View> list_of_elements = new ArrayList<>();
    ExpandableRelativeLayout expandableLayout;

    Button button_extended_search_start;
    Button button_extended_search_start2;
    Button button_reset_filter;

    private CheckBox checkBox_ID_BiologischerAnbau_;

    private SeekBar seekBar_ID_Entfernung_;
    private EditText text_ID_Entfernung_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Bundle b = getIntent().getExtras();
        String query = null; // or other values
        if (b != null) {
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

        text_ID_Entfernung_ = (EditText) findViewById(R.id.text_ID_Entfernung);


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

        seekBar_ID_Entfernung_ = (SeekBar) findViewById(R.id.seekBar_ID_Entfernung);
        seekBar_ID_Entfernung_.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                text_ID_Entfernung_.setText("Entfernung: " + String.valueOf(progress) + " km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new GetProductTask().execute(query);

    }

    private void setFilter(Bundle bundle) {

        ((CheckBox) findViewById(R.id.checkBox_ID_BiologischerAnbau)).setChecked(
                bundle.getBoolean("checkBox_ID_BiologischerAnbau", false));
        ((CheckBox) findViewById(R.id.cb_category_0)).setChecked(
                bundle.getBoolean("cb_category_0", false));
        ((CheckBox) findViewById(R.id.cb_category_1)).setChecked(
                bundle.getBoolean("cb_category_1", false));
        ((CheckBox) findViewById(R.id.cb_category_2)).setChecked(
                bundle.getBoolean("cb_category_2", false));
        ((CheckBox) findViewById(R.id.cb_category_3)).setChecked(
                bundle.getBoolean("cb_category_3", false));
        ((CheckBox) findViewById(R.id.cb_category_5)).setChecked(
                bundle.getBoolean("cb_category_5", false));
        ((CheckBox) findViewById(R.id.cb_category_4)).setChecked(
                bundle.getBoolean("cb_category_4", false));
        ((CheckBox) findViewById(R.id.cb_seller_0)).setChecked(
                bundle.getBoolean("cb_seller_0", false));
        ((CheckBox) findViewById(R.id.cb_seller_1)).setChecked(
                bundle.getBoolean("cb_seller_1", false));
        ((CheckBox) findViewById(R.id.cb_transfer_0)).setChecked(
                bundle.getBoolean("cb_transfer_0", false));
        ((CheckBox) findViewById(R.id.cb_transfer_1)).setChecked(
                bundle.getBoolean("cb_transfer_1", false));
        ((CheckBox) findViewById(R.id.cb_transfer_2)).setChecked(
                bundle.getBoolean("cb_transfer_2", false));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sort_arrays, R.layout.activity_search_result);
        ((Spinner) findViewById(R.id.Spinner_ID_ExtendedSearch)).setSelection(adapter.getPosition(
                bundle.getString("Spinner_ID_ExtendedSearch", "")
        ));

        ((SeekBar) findViewById(R.id.seekBar_ID_Entfernung)).setProgress(
                bundle.getInt("seekBar_ID_Entfernung", 0)
        );

        ((EditText) findViewById(R.id.text_ID_Entfernung)).setText(
                bundle.getString("text_ID_Entfernung", "Entfernung: 0 km")
        );
    }

    private void saveFilterPreset(Bundle bundle) {



        bundle.putBoolean("checkBox_ID_BiologischerAnbau", ((CheckBox) findViewById(R.id.checkBox_ID_BiologischerAnbau)).isChecked());
        bundle.putBoolean("cb_category_0", ((CheckBox) findViewById(R.id.cb_category_0)).isChecked());
        bundle.putBoolean("cb_category_1", ((CheckBox) findViewById(R.id.cb_category_1)).isChecked());
        bundle.putBoolean("cb_category_2", ((CheckBox) findViewById(R.id.cb_category_2)).isChecked());
        bundle.putBoolean("cb_category_3", ((CheckBox) findViewById(R.id.cb_category_3)).isChecked());
        bundle.putBoolean("cb_category_4", ((CheckBox) findViewById(R.id.cb_category_4)).isChecked());
        bundle.putBoolean("cb_category_5", ((CheckBox) findViewById(R.id.cb_category_5)).isChecked());
        bundle.putBoolean("cb_seller_0", ((CheckBox) findViewById(R.id.cb_seller_0)).isChecked());
        bundle.putBoolean("cb_seller_1", ((CheckBox) findViewById(R.id.cb_seller_1)).isChecked());
        bundle.putBoolean("cb_transfer_0", ((CheckBox) findViewById(R.id.cb_transfer_0)).isChecked());
        bundle.putBoolean("cb_transfer_1", ((CheckBox) findViewById(R.id.cb_transfer_1)).isChecked());
        bundle.putBoolean("cb_transfer_2", ((CheckBox) findViewById(R.id.cb_transfer_2)).isChecked());

        bundle.putString("Spinner_ID_ExtendedSearch", ((Spinner) findViewById(R.id.Spinner_ID_ExtendedSearch)).getSelectedItem().toString());
        bundle.putInt("seekBar_ID_Entfernung", ((SeekBar) findViewById(R.id.seekBar_ID_Entfernung)).getProgress());
        bundle.putString("text_ID_Entfernung", ((EditText) findViewById(R.id.text_ID_Entfernung)).getText().toString());
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
                    ImageButton image_load = (ImageButton) productLayout.findViewById(R.id.imageButtonProduct);
                    image_load.setOnClickListener(this);
                    Glide.with(getApplicationContext()).load(Core.getInstance().getProducts().getImageUri(p.getId())).into(image_load);
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
            case R.id.Button_ID_ExtendedSearchStart:
            case R.id.Button_ID_ExtendedSearchStart2:
                Filter filter = getFilter();
                break;
            default:
                break;
        }

        if (v.getId() == R.id.Button_ID_ResetFilterExtendedSearch) {
            checkBox_ID_BiologischerAnbau_.setChecked(false);

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
        }
    }

    private Filter getFilter() {
        Filter filter = new Filter(seekBar_ID_Entfernung_.getProgress());
        filter.setBio_(checkBox_ID_BiologischerAnbau_.isChecked());

        List<Categories> categories = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int resID = getResources().getIdentifier("cb_category_" + i, "id", getPackageName());
            if (((CheckBox) findViewById(resID)).isChecked()) {
                categories.add(Categories.fromInt(i));
            }
        }

        List<Seller> seller = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            int resID = getResources().getIdentifier("cb_seller_" + i, "id", getPackageName());
            if (((CheckBox) findViewById(resID)).isChecked()) {
                categories.add(Categories.fromInt(i));
            }
        }

        List<Transfer> transfer = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int resID = getResources().getIdentifier("cb_transfer_" + i, "id", getPackageName());
            if (((CheckBox) findViewById(resID)).isChecked()) {
                categories.add(Categories.fromInt(i));
            }
        }

        filter.setCategories_(categories);
        filter.setSeller_(seller);
        filter.setTransfer_(transfer);
        return filter;
    }


}