package at.sw2017xp3.regionalo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

/**
 * Created by lukas on 24.05.2017.
 */

public class ReleaseAdActivity extends Activity {

    private String logged_user_id;

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radioButton_ID_ReleaseAd_BereitsGeerntet:
                if (checked)
                    break;
            case R.id.radioButton_ID_ReleaseAd_SelbstErnten:
                if (checked)
                    break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_ad);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        logged_user_id = bundle.getString(getString(R.string.logged_user_id));

        Spinner spinner = (Spinner) findViewById(R.id.spinner_ID_ReleaseAdKategorie);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        spinner.setAdapter(adapter);
    }


}
