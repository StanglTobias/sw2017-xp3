package at.sw2017xp3.regionalo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = (Button) findViewById(R.id.buttonRegister);
        button.setOnClickListener(this);
    }

    Button button;

    @Override
    public void onClick(View v) {

        Intent myIntent = new Intent(this, RegisterActivity.class);
        startActivity(myIntent);

    }


}
