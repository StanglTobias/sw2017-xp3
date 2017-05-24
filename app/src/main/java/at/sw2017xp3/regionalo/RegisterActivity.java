package at.sw2017xp3.regionalo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button = (Button) findViewById(R.id.Button_ID_ConfirmRegistration);
        button.setOnClickListener(this);
    }

    Button button;
    Toast mToast;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Button_ID_ConfirmRegistration:

                if (arePasswordsIdentical() && areRequiredFieldsNotEmpty()) {
                    Intent myIntent = new Intent(this, LoginActivity.class);
                    startActivity(myIntent);
                }
                break;
        }
    }

    private boolean areRequiredFieldsNotEmpty() {

        if (((EditText) findViewById(R.id.et_register_ID_Hofname)).getText().toString().isEmpty() ||
                ((EditText) findViewById(R.id.et_register_ID_vorname)).getText().toString().isEmpty() ||
                ((EditText) findViewById(R.id.et_register_ID_nachname)).getText().toString().isEmpty() ||
                ((EditText) findViewById(R.id.et_register_ID_email)).getText().toString().isEmpty() ||
                ((EditText) findViewById(R.id.et_register_ID_ort)).getText().toString().isEmpty() ||
                ((EditText) findViewById(R.id.et_register_ID_plz)).getText().toString().isEmpty() ||
                ((EditText) findViewById(R.id.et_register_ID_adresse)).getText().toString().isEmpty() ||
                ((EditText) findViewById(R.id.et_register_ID_passwort)).getText().toString().isEmpty() ||
                ((EditText) findViewById(R.id.et_register_ID_passwortwiederholen)).getText().toString().isEmpty()
                )
        {
            if(mToast != null) {
                mToast.cancel();
            }
            mToast = Toast.makeText(this, "Pflichtfelder bitte ausfüllen!", Toast.LENGTH_SHORT);
            mToast.show();
            return false;
        }

        return true;
    }

    private boolean arePasswordsIdentical() {
        EditText pass = (EditText) findViewById(R.id.et_register_ID_passwort);
        EditText pass2 = (EditText) findViewById(R.id.et_register_ID_passwortwiederholen);

        if (!pass.getText().toString().isEmpty() && !pass2.getText().toString().isEmpty() &&
                (pass.getText().toString().equals(pass2.getText().toString())))
            return true;

        if(mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, "Passwörter stimmen nicht überein", Toast.LENGTH_SHORT);
        mToast.show();
        return false;
    }
}
