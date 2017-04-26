package at.sw2017xp3.regionalo;


import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import at.sw2017xp3.regionalo.LoginActivity;
import at.sw2017xp3.regionalo.R;

public class ReleaseAdActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<View> list_of_buttons = new ArrayList<>();

    private int year_, month_, day_;
    static final int DIALOG_ID = 0;

    private DatePickerDialog.OnDateSetListener datepickListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_ = year;
            month_ = month;
            day_ = dayOfMonth;
            if (view.getId() == findViewById(R.id.textView_ID_DateVon).)
            ((TextView) findViewById(R.id.textView_ID_DateVon)).setText(year + "/" + month + "/" + dayOfMonth);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_ad);

        addButtonListeners();

        final Calendar calendar = Calendar.getInstance();
        year_ = calendar.get(Calendar.YEAR);
        month_ = calendar.get(Calendar.MONTH);
        day_ = calendar.get(Calendar.DAY_OF_MONTH);


        findViewById(R.id.textView_ID_DateVon).setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_ID);
                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID)
        {
            return new DatePickerDialog(this, datepickListener, year_, month_, day_ );
        }
        return null;
    }

    private void addButtonListeners() {
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