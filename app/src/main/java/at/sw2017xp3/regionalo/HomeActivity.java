package at.sw2017xp3.regionalo;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fillArrayListWithImageButtons();
        list_of_elements.addAll(Arrays.asList(
                findViewById(R.id.buttonMeat),
                findViewById(R.id.buttonVegetables),
                findViewById(R.id.buttonFruit),
                findViewById(R.id.buttonOthers),
                findViewById(R.id.buttonMilk),
                findViewById(R.id.buttonCereals),
                findViewById(R.id.searchView),
                findViewById(R.id.buttonMilk)));


        Uri uri = Uri.parse("http://sw-ma-xp3.bplaced.net/MySQLadmin/featured.php");
               // .buildUpon()
               // .appendQueryParameter("id", "1").build();

        new GetProductTask().execute(uri.toString());
        for (int i = 0; i < list_of_elements.size(); i++) {
            list_of_elements.get(i).setOnClickListener(this);
        }

        LayoutInflater inflater = getLayoutInflater();
        LinearLayout inflatedView = (LinearLayout) inflater.inflate(R.layout.product, null);
       ((TextView) inflatedView.findViewById(R.id.textViewRndProduct1)).setText("Speck");
       ((TextView) inflatedView.findViewById(R.id.textViewRndProduct2)).setText("2");
       ((TextView) inflatedView.findViewById(R.id.textViewRndProduct3)).setText("3");
       ((TextView) inflatedView.findViewById(R.id.textViewRndProduct4)).setText("4");
       ((TextView) inflatedView.findViewById(R.id.textViewRndProduct5)).setText("5");



        LinearLayout linearLayoutHome = (LinearLayout) findViewById(R.id.linearLayout_Home_Activity);
        linearLayoutHome.addView(inflatedView);
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
        Intent myIntent = new Intent(this, ProductDetailActivity.class);
        startActivity(myIntent);

    }

    public void fillArrayListWithImageButtons() {
        for (int i = 1; i <= 1; i++) {
            String rndBtn = "imgButtonRnd" + i;
            int idBtn = getResources().getIdentifier(rndBtn, "id", R.class.getPackage().getName());
            list_of_elements.add(findViewById(idBtn));
        }
    }
}