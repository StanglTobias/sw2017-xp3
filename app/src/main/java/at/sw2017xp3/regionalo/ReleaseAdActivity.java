package at.sw2017xp3.regionalo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by lukas on 24.05.2017.
 */

public class ReleaseAdActivity extends Activity {

    private String logged_user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_ad);

        //Get the user id which we get from previouse activity
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        logged_user_id = bundle.getString(getString(R.string.logged_user_id));

        Spinner spinner = (Spinner) findViewById(R.id.spinner_ID_ReleaseAdKategorie);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }


}
