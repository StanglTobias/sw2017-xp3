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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResultActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<View> list_of_elements = new ArrayList<>();
    ExpandableRelativeLayout expandableLayout;

 /*   Button button_reset_filter;

    private CheckBox checkBox_ID_BiologischerAnbau_;

    private SeekBar seekBar_ID_Entfernung_;
    private EditText text_ID_Entfernung_;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

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

                if (!query.isEmpty()) {

                    new GetProductTask().execute(getFilter());
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        ((SeekBar) findViewById(R.id.seekBar_ID_Entfernung)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ((EditText) findViewById(R.id.text_ID_Entfernung))
                        .setText("Entfernung: " + String.valueOf(progress) + " km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
                ((ExpandableRelativeLayout) findViewById(R.id.expandableLayout)).toggle();
                break;
            case R.id.Button_ID_ExtendedSearchStart:
            case R.id.Button_ID_ExtendedSearchStart2:
                Filter filter = getFilter();
                break;
            case R.id.Button_ID_ResetFilterExtendedSearch: {
                LinearLayout searchResultLayout = ((LinearLayout) findViewById(R.id.linearLayoutSearchResult));
                for (int i = 0; i < searchResultLayout.getChildCount(); i++) {
                    if (searchResultLayout.getChildAt(i) instanceof CheckBox) {
                        ((CheckBox) searchResultLayout.getChildAt(i)).setChecked(false);
                    }
                }
            }
            break;
            default:
                break;
        }
    }

    private Filter getFilter() {
        Filter filter = new Filter(((SeekBar) findViewById(R.id.seekBar_ID_Entfernung)).getProgress());
        filter.setBio(((CheckBox) findViewById(R.id.checkBox_ID_BiologischerAnbau)).isChecked());

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

        filter.setCategories(categories);
        filter.setSeller(seller);
        filter.setTransfer_(transfer);
        return filter;
    }


}