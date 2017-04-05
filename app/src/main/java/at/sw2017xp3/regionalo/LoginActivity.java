package at.sw2017xp3.regionalo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static at.sw2017xp3.regionalo.R.id.textViewEmail;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button;
    private Button buttonLogin;

    private TextView textViewEmail;
    private TextView textViewPassword;
    private TextView textViewLoginErrors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = (Button) findViewById(R.id.buttonRegister);
        button.setOnClickListener(this);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        textViewPassword = (TextView) findViewById(R.id.textViewPassword);
        textViewLoginErrors = (TextView) findViewById(R.id.textView_ID_LoginErrors);
    }



    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;

        switch (clickedButton.getId()) {
            case R.id.buttonLogin:
                if(textViewEmail.getText().toString().isEmpty() &&
                        textViewPassword.getText().toString().isEmpty()) {
                    textViewLoginErrors.setText("Bitte Email und Passwort eingeben!");
                }
                else  if(textViewEmail.getText().toString().isEmpty() &&
                        !textViewPassword.getText().toString().isEmpty()) {
                    textViewLoginErrors.setText("Bitte E-Mail eingeben!");
                }
                else  if(!textViewEmail.getText().toString().isEmpty() &&
                        textViewPassword.getText().toString().isEmpty()) {
                    textViewLoginErrors.setText("Bitte Passwort eingeben!");
                }
                else {
                    textViewLoginErrors.setText("LOGIN USER CONTENT ÖFFNEN");
                }
                break;

            case R.id.buttonRegister:
                Intent myIntent = new Intent(this, RegisterActivity.class);
                startActivity(myIntent);
        }
    }
}