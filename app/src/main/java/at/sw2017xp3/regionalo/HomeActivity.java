package at.sw2017xp3.regionalo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonMeat_;
    private Button buttonVegetables_;
    private Button buttonFruit_;
    private Button buttonOthers_;
    private Button buttonMilk_;
    private Button buttonCereals_;
    private ViewGroup searchField_;


    private ArrayList<ImageButton> numberImageButton;
    private ArrayList<TextView> numberTextviewProducer;
    private ArrayList<TextView> numberTextviewCategory;
    private ArrayList<TextView> numberTextviewPrice;
    private ArrayList<TextView> numberTextviewPlace;
    private ArrayList<TextView> numberTextviewProduct;

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

        searchField_ = (ViewGroup) findViewById(R.id.searchView);
        searchField_.setOnClickListener(this);

        setUpListeners();
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

    }

    public void setUpListeners () {
        for (int i = 1; i <= 6; i++)
        {
            String rndBtn = "imgButtonRnd" + i;
            String textViewProducer = "textViewRndProducer" + i;
            String textViewCategory = "textViewRndCategory" + i;
            String textViewPrice = "textViewRndPrice" + i;
            String textViewPlace = "textViewRndPlace" + i;
            String textViewProduct = "textViewRndProduct" + i;

            int idBtn = getResources (). getIdentifier ( rndBtn , "id" , R . class . getPackage (). getName ());
            int idProducer = getResources (). getIdentifier ( textViewProducer , "id" , R . class . getPackage (). getName ());
            int idCategory = getResources (). getIdentifier ( textViewCategory , "id" , R . class . getPackage (). getName ());
            int idPrice = getResources (). getIdentifier ( textViewPrice , "id" , R . class . getPackage (). getName ());
            int idPlace = getResources (). getIdentifier ( textViewProduct , "id" , R . class . getPackage (). getName ());
            int idProduct = getResources (). getIdentifier ( textViewPlace , "id" , R . class . getPackage (). getName ());

            ImageButton rndImgBtn = (ImageButton) findViewById(idBtn);
            rndImgBtn.setOnClickListener(this);

            TextView idProducerTxt = (TextView) findViewById(idProducer);
            idProducerTxt.setOnClickListener(this);

            TextView idCategoryTxt = (TextView) findViewById(idCategory);
            idCategoryTxt.setOnClickListener(this);

            TextView idPriceTxt = (TextView) findViewById(idPrice);
            idPriceTxt.setOnClickListener(this);

            TextView idPlaceTxt = (TextView) findViewById(idPlace);
            idPlaceTxt.setOnClickListener(this);

            TextView idProductTxt = (TextView) findViewById(idProduct);
            idProductTxt.setOnClickListener(this);

            numberImageButton = new ArrayList<>();
            numberTextviewProducer = new ArrayList<>();
            numberTextviewCategory = new ArrayList<>();
            numberTextviewPrice = new ArrayList<>();
            numberTextviewPlace = new ArrayList<>();
            numberTextviewProduct = new ArrayList<>();
        }
    }
}
