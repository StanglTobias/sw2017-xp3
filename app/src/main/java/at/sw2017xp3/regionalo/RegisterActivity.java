package at.sw2017xp3.regionalo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button = (Button) findViewById(R.id.Button_ID_ConfirmRegistration);
        button.setOnClickListener(this);
    }

    Button button;

    @Override
    public void onClick(View v) {

        Intent myIntent = new Intent(this, LoginActivity.class);
        startActivity(myIntent);

    }
}
