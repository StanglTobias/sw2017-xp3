package at.sw2017xp3.regionalo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
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

import at.sw2017xp3.regionalo.model.CurrentUser;
import at.sw2017xp3.regionalo.util.Installation;
import at.sw2017xp3.regionalo.model.Product;
import at.sw2017xp3.regionalo.util.HttpUtils;
import at.sw2017xp3.regionalo.util.JsonObjectMapper;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Arrays;
import android.provider.Settings.Secure;

import static java.security.AccessController.getContext;

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
                findViewById(R.id.searchView),
                findViewById(R.id.buttonMilk)));


        Uri uri = Uri.parse("http://sw-ma-xp3.bplaced.net/MySQLadmin/featured.php");
        // .buildUpon()
        // .appendQueryParameter("id", "1").build();

        new GetProductTask().execute(uri.toString());

        for (int i = 0; i < list_of_elements.size(); i++) {
            list_of_elements.get(i).setOnClickListener(this);
        }
    }

    private class GetProductTask extends AsyncTask<String, Void, String>  implements View.OnClickListener{

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

            Toast.makeText(HomeActivity.this, "Daten geladen", Toast.LENGTH_LONG).show();


            try {
                JSONArray arr = new JSONArray(result); //featured products

                LinearLayout linearLayoutHome = (LinearLayout) findViewById(R.id.linearLayout_Home_Activity);
                for (int productCnt = 0; productCnt < arr.length(); productCnt++) {
                    System.out.println("GetProductTask.onPostExecute array laenge " + arr.length());

                    JSONObject mJsonObject = arr.getJSONObject(productCnt);
                    Product p = JsonObjectMapper.CreateProduct(mJsonObject);

                    System.out.println("GetProductTask.onPostExecute name of product: " + p.getName());

                    LayoutInflater inflater = getLayoutInflater();
                    LinearLayout inflatedView = (LinearLayout) inflater.inflate(R.layout.product, linearLayoutHome);

                    int productLayoutId = p.getId();
                    LinearLayout productLayout = (LinearLayout) inflatedView.findViewById(R.id.linearLayout_product);
                    (inflatedView.findViewById(R.id.linearLayout_product)).setId(productLayoutId);

                    (productLayout.findViewById(R.id.imageButtonProduct)).setOnClickListener(this);
                    ((TextView) productLayout.findViewById(R.id.textViewRndProduct1)).setText(p.getName());
                    ((TextView) productLayout.findViewById(R.id.textViewRndProduct2)).setText("Id: " + String.valueOf(p.getId()));
                    ((TextView) productLayout.findViewById(R.id.textViewRndProduct3)).setText("Erzeuger Id: " + String.valueOf(p.getProducerId()));
                    ((TextView) productLayout.findViewById(R.id.textViewRndProduct4)).setText("Preis: " + String.valueOf(p.getPrice()));
                    ((TextView) productLayout.findViewById(R.id.textViewRndProduct5)).setText("Typ: " + String.valueOf(p.getType()));
                }

            } catch (Exception ex) {
                System.out.println("GetProductTask.onPostExecute" + "exception");
                ex.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {

            ImageButton imageButton = (ImageButton) v;
            LinearLayout productLayout = (LinearLayout)imageButton.getParent();
            int productId = productLayout.getId();

            Intent myIntent = new Intent(HomeActivity.this, ProductDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("id", productId);
            myIntent.putExtras(bundle);
            startActivity(myIntent);
        }
    }

    private String downloadContent(String myurl) throws IOException {
        InputStream is = null;
        int length = 10000;

        try {
            HttpURLConnection conn = HttpUtils.httpGet(myurl);

            return HttpUtils.convertInputStreamToString(conn.getInputStream(), length);

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
        Intent myIntent = new Intent(getBaseContext(), ProductDetailActivity.class);
    }
}