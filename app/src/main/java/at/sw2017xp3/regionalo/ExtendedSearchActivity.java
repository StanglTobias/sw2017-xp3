package at.sw2017xp3.regionalo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class ExtendedSearchActivity extends AppCompatActivity implements View.OnClickListener {


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
        setContentView(R.layout.activity_extended_search);
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



    }


    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        if (button.getId() == R.id.Button_ID_ExtendedSearchStart ||
                button.getId() == R.id.Button_ID_ExtendedSearchStart2 ) {
            //TODO implement search function (start new activity with parameters)
        }
        if (button.getId() == R.id.Button_ID_ResetFilterExtendedSearch) {
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

