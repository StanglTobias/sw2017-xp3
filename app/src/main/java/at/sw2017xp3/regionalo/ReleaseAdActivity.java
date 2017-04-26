package at.sw2017xp3.regionalo;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import at.sw2017xp3.regionalo.LoginActivity;
import at.sw2017xp3.regionalo.R;

public class ReleaseAdActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<View> list_of_buttons = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_ad);

        addButtonListeners();
        findViewById(R.id.textView_ID_DateVon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  datePickerDialog.show();
            }
        });
    }

    private void addButtonListeners()
    {
        list_of_buttons.addAll(Arrays.asList(
                findViewById(R.id.button_ID_Bildauswaehlen),
                findViewById(R.id.button_ID_Bild2auswaehlen),
                findViewById(R.id.button_ID_ReleaseAd_Abbrechen),
                findViewById(R.id.button_ID_ReleaseAdSpeichern)));

        for (int i = 0; i < list_of_buttons.size(); i++) {
            list_of_buttons.get(i).setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;
        switch (clickedButton.getId()) {
            case R.id.button_ID_Bildauswaehlen:
                break;
            case R.id.button_ID_Bild2auswaehlen:
                break;
            case R.id.button_ID_ReleaseAd_Abbrechen:
                break;
            case R.id.button_ID_ReleaseAdSpeichern:
                break;
        }
    }
}