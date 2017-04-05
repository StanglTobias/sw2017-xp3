package at.sw2017xp3.regionalo;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonMeat_;
    private Button buttonVegetables_;
    private Button buttonFruit_;
    private Button buttonOthers_;
    private Button buttonMilk_;
    private Button buttonCereals_;

    private ImageButton rndBtn1_;
    private ImageButton rndBtn2_;
    private ImageButton rndBtn3_;
    private ImageButton rndBtn4_;
    private ImageButton rndBtn5_;
    private ImageButton rndBtn6_;
    private ViewGroup searchField_;

    private TextView rndTxtProduct1_;
    private TextView rndTxtCategorie1_;
    private TextView rndTxtPrice1_;
    private TextView rndTxtPlace1_;
    private TextView rndTxtProducer1_;

    private TextView rndTxtProduct2_;
    private TextView rndTxtCategorie2_;
    private TextView rndTxtPrice2_;
    private TextView rndTxtPlace2_;
    private TextView rndTxtProducer2_;

    private TextView rndTxtProduct3_;
    private TextView rndTxtCategorie3_;
    private TextView rndTxtPrice3_;
    private TextView rndTxtPlace3_;
    private TextView rndTxtProducer3_;

    private TextView rndTxtProduct4_;
    private TextView rndTxtCategorie4_;
    private TextView rndTxtPrice4_;
    private TextView rndTxtPlace4_;
    private TextView rndTxtProducer4_;

    private TextView rndTxtProduct5_;
    private TextView rndTxtCategorie5_;
    private TextView rndTxtPrice5_;
    private TextView rndTxtPlace5_;
    private TextView rndTxtProducer5_;

    private TextView rndTxtProduct6_;
    private TextView rndTxtCategorie6_;
    private TextView rndTxtPrice6_;
    private TextView rndTxtPlace6_;
    private TextView rndTxtProducer6_;

    private Button btnLogin_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonMeat_ = (Button) findViewById(R.id.buttonMeat);
        buttonMeat_.setOnClickListener(this);
        buttonVegetables_ = (Button) findViewById(R.id.buttonVegetables);
        buttonVegetables_.setOnClickListener(this);
        buttonFruit_ = (Button) findViewById(R.id.buttonFruit);
        buttonFruit_.setOnClickListener(this);
        buttonCereals_ = (Button) findViewById(R.id.buttonCereals);
        buttonCereals_.setOnClickListener(this);
        buttonOthers_ = (Button) findViewById(R.id.buttonOthers);
        buttonOthers_.setOnClickListener(this);
        buttonMilk_ = (Button) findViewById(R.id.buttonMilk);
        buttonMilk_.setOnClickListener(this);

        rndBtn1_ = (ImageButton) findViewById(R.id.imgButtonRnd1);
        rndBtn1_.setOnClickListener(this);
        rndBtn2_ = (ImageButton) findViewById(R.id.imgButtonRnd2);
        rndBtn2_.setOnClickListener(this);
        rndBtn3_ = (ImageButton) findViewById(R.id.imgButtonRnd3);
        rndBtn3_.setOnClickListener(this);
        rndBtn4_ = (ImageButton) findViewById(R.id.imgButtonRnd4);
        rndBtn4_.setOnClickListener(this);
        rndBtn5_ = (ImageButton) findViewById(R.id.imgButtonRnd5);
        rndBtn5_.setOnClickListener(this);
        rndBtn6_ = (ImageButton) findViewById(R.id.imgButtonRnd6);
        rndBtn6_.setOnClickListener(this);
        //btnLogin_ = (Button) findViewById(R.id.buttonLogin);
        //btnLogin_.setOnClickListener(this);

        searchField_ = (ViewGroup) findViewById(R.id.searchView);
        searchField_.setOnClickListener(this);

        rndTxtProducer1_ = (TextView) findViewById(R.id.textViewRndProducer1);
        rndTxtProducer1_.setOnClickListener(this);
        rndTxtCategorie1_ = (TextView) findViewById(R.id.textViewRndCategory1);
        rndTxtCategorie1_.setOnClickListener(this);
        rndTxtPrice1_ = (TextView) findViewById(R.id.textViewRndPrice1);
        rndTxtPrice1_.setOnClickListener(this);
        rndTxtPlace1_ = (TextView) findViewById(R.id.textViewRndPlace1);
        rndTxtPlace1_.setOnClickListener(this);
        rndTxtProduct1_ = (TextView) findViewById(R.id.textViewRndProduct1);
        rndTxtProduct1_.setOnClickListener(this);

        rndTxtProducer2_ = (TextView) findViewById(R.id.textViewRndProducer2);
        rndTxtProducer2_.setOnClickListener(this);
        rndTxtCategorie2_ = (TextView) findViewById(R.id.textViewRndCategory2);
        rndTxtCategorie2_.setOnClickListener(this);
        rndTxtPrice2_ = (TextView) findViewById(R.id.textViewRndPrice2);
        rndTxtPrice2_.setOnClickListener(this);
        rndTxtPlace2_ = (TextView) findViewById(R.id.textViewRndPlace2);
        rndTxtPlace2_.setOnClickListener(this);
        rndTxtProduct2_ = (TextView) findViewById(R.id.textViewRndProduct2);
        rndTxtProduct2_.setOnClickListener(this);

        rndTxtProducer3_ = (TextView) findViewById(R.id.textViewRndProducer3);
        rndTxtProducer3_.setOnClickListener(this);
        rndTxtCategorie3_ = (TextView) findViewById(R.id.textViewRndCategory3);
        rndTxtCategorie3_.setOnClickListener(this);
        rndTxtPrice3_ = (TextView) findViewById(R.id.textViewRndPrice3);
        rndTxtPrice3_.setOnClickListener(this);
        rndTxtPlace3_ = (TextView) findViewById(R.id.textViewRndPlace3);
        rndTxtPlace3_.setOnClickListener(this);
        rndTxtProduct3_ = (TextView) findViewById(R.id.textViewRndProduct3);
        rndTxtProduct3_.setOnClickListener(this);

        rndTxtProducer4_ = (TextView) findViewById(R.id.textViewRndProducer4);
        rndTxtProducer4_.setOnClickListener(this);
        rndTxtCategorie4_ = (TextView) findViewById(R.id.textViewRndCategory4);
        rndTxtCategorie4_.setOnClickListener(this);
        rndTxtPrice4_ = (TextView) findViewById(R.id.textViewRndPrice4);
        rndTxtPrice4_.setOnClickListener(this);
        rndTxtPlace4_ = (TextView) findViewById(R.id.textViewRndPlace4);
        rndTxtPlace4_.setOnClickListener(this);
        rndTxtProduct4_ = (TextView) findViewById(R.id.textViewRndProduct4);
        rndTxtProduct4_.setOnClickListener(this);

        rndTxtProducer5_ = (TextView) findViewById(R.id.textViewRndProducer5);
        rndTxtProducer5_.setOnClickListener(this);
        rndTxtCategorie5_ = (TextView) findViewById(R.id.textViewRndCategory5);
        rndTxtCategorie5_.setOnClickListener(this);
        rndTxtPrice5_ = (TextView) findViewById(R.id.textViewRndPrice5);
        rndTxtPrice5_.setOnClickListener(this);
        rndTxtPlace5_ = (TextView) findViewById(R.id.textViewRndPlace5);
        rndTxtPlace5_.setOnClickListener(this);
        rndTxtProduct5_ = (TextView) findViewById(R.id.textViewRndProduct5);
        rndTxtProduct5_.setOnClickListener(this);

        rndTxtProducer6_ = (TextView) findViewById(R.id.textViewRndProducer6);
        rndTxtProducer6_.setOnClickListener(this);
        rndTxtCategorie6_ = (TextView) findViewById(R.id.textViewRndCategory6);
        rndTxtCategorie6_.setOnClickListener(this);
        rndTxtPrice6_ = (TextView) findViewById(R.id.textViewRndPrice6);
        rndTxtPrice6_.setOnClickListener(this);
        rndTxtPlace6_ = (TextView) findViewById(R.id.textViewRndPlace6);
        rndTxtPlace6_.setOnClickListener(this);
        rndTxtProduct6_ = (TextView) findViewById(R.id.textViewRndProduct6);
        rndTxtProduct6_.setOnClickListener(this);


        Uri uri = Uri.parse("http://sw-ma-xp3.bplaced.net/MySQLadmin/product.php")
                .buildUpon()
                .appendQueryParameter("id", "1").build();


        new GetProductTask().execute(uri.toString());
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
        int length = 500;

        try {
            HttpURLConnection conn = HttpUtils.httpGet(myurl);

            String contentAsString = HttpUtils.convertInputStreamToString(conn.getInputStream(), length);
            JSONArray arr = new JSONArray(contentAsString);
            JSONObject mJsonObject = arr.getJSONObject(0);
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
        Button myButton = (Button) v;
        if (myButton.getId() == R.id.buttonLogin) {
        }
    }
}
