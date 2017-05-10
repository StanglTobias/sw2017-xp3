package at.sw2017xp3.regionalo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<View> list_of_elements = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        list_of_elements.addAll(Arrays.asList(
                findViewById(R.id.buttonRegister),
                findViewById(R.id.buttonLogin)));

        for (int i = 0; i < list_of_elements.size(); i++) {
            list_of_elements.get(i).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;

        switch (clickedButton.getId()) {
            case R.id.buttonLogin:
                if (((TextView) findViewById(R.id.textViewEmail)).getText().toString().isEmpty() &&
                        ((TextView) findViewById(R.id.textViewPassword)).getText().toString().isEmpty()) {
                    ((TextView) findViewById(R.id.textView_ID_LoginErrors)).setText(getString(R.string.missingPW_Email));
                } else if (((TextView) findViewById(R.id.textViewEmail)).getText().toString().isEmpty() &&
                        !((TextView) findViewById(R.id.textViewPassword)).getText().toString().isEmpty()) {
                    ((TextView) findViewById(R.id.textView_ID_LoginErrors)).setText(getString(R.string.missingEmail));
                } else if (!((TextView) findViewById(R.id.textViewEmail)).getText().toString().isEmpty() &&
                        ((TextView) findViewById(R.id.textViewPassword)).getText().toString().isEmpty()) {
                    ((TextView) findViewById(R.id.textView_ID_LoginErrors)).setText(getString(R.string.missingPW));
                } else {
                    ((TextView) findViewById(R.id.textView_ID_LoginErrors)).setText(getString(R.string.openUserContent));
                }
                break;

            case R.id.buttonRegister:
                Intent myIntent = new Intent(this, RegisterActivity.class);
                startActivity(myIntent);
                break;
        }
    }
}