package at.sw2017xp3.regionalo;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import at.sw2017xp3.regionalo.model.Product;
import at.sw2017xp3.regionalo.util.HttpUtils;
import at.sw2017xp3.regionalo.util.JsonObjectMapper;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Arrays;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
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
        setContentView(R.layout.activity_home);

        expandableLayout
                = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout);
        Button expandButton
                = (Button) findViewById(R.id.expand);
        expandButton.setOnClickListener(this);

        fillArrayListWithImageButtons();
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





        Uri uri = Uri.parse("http://sw-ma-xp3.bplaced.net/MySQLadmin/featured.php");
               // .buildUpon()
               // .appendQueryParameter("id", "1").build();

        new GetProductTask().execute(uri.toString());
        for (int i = 0; i < list_of_elements.size(); i++) {
            list_of_elements.get(i).setOnClickListener(this);




        }
    }

    private class GetProductTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                return downloadContent(params[0]);
            } catch (IOException e) {
                return "Unable to retrieve data. URL may be invalid.";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(HomeActivity.this, result, Toast.LENGTH_LONG).show();
        }
    }

    private String downloadContent(String myurl) throws IOException {
        InputStream is = null;
        int length = 10000;

        try {
            HttpURLConnection conn = HttpUtils.httpGet(myurl);

            String contentAsString = HttpUtils.convertInputStreamToString(conn.getInputStream(), length);
            JSONArray arr = new JSONArray(contentAsString); //featured products
            JSONObject mJsonObject = arr.getJSONObject(0);//one product

            String allProductNames;

            Product p =  JsonObjectMapper.CreateProduct(mJsonObject);

            return p.getName();
        } catch (Exception ex) {
            return "";
        } finally {
            if (is != null) {
                is.close();
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
        //Intent myIntent = new Intent(this, ExtendedSearchActivity.class);
       // startActivity(myIntent);



        switch (v.getId()) {
            case R.id.expand:
                // toggle expand, collapse
                expandableLayout.toggle();
// expand
                expandableLayout.expand();
// collapse
                expandableLayout.collapse();

// move position of child view
                expandableLayout.moveChild(0);
// move optional position
                expandableLayout.move(500);

// set base position which is close position
                expandableLayout.setClosePosition(0);
                break;
            default:
                break;
        }

            if (v.getId() == R.id.Button_ID_ExtendedSearchStart)
            {
                //TODO implement search function (start new activity with parameters)
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

    public void fillArrayListWithImageButtons() {
        for (int i = 1; i <= 6; i++) {
            String rndBtn = "imgButtonRnd" + i;
            int idBtn = getResources().getIdentifier(rndBtn, "id", R.class.getPackage().getName());
            list_of_elements.add(findViewById(idBtn));
        }
    }



}