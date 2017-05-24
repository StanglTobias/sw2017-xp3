package at.sw2017xp3.regionalo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
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

        COLOR_WRONG = ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null);
        //   ContextCompat.getColor(this, R.color.colorPrimary);


    }

    Button button;
    Toast mToast;
    int COLOR_WRONG;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Button_ID_ConfirmRegistration:

                if (areRequiredFieldsNotEmpty() && arePasswordsIdentical()) {
                    Intent myIntent = new Intent(this, LoginActivity.class);
                    startActivity(myIntent);
                }
                break;
        }
    }

    private boolean areRequiredFieldsNotEmpty() {

        boolean fieldsNotEmpty = false;
        for (int i = 0; i <= 9; i++) {
            int resID = getResources().getIdentifier("et_register_ID_" + i, "id", getPackageName());
            EditText textField = ((EditText) findViewById(resID));
            if (textField.getText().toString().isEmpty()) {

                textField.setBackgroundResource(R.drawable.border_edit_text_empty);

                fieldsNotEmpty = true;
            } else {
                textField.setBackgroundResource(R.drawable.border_edit_text);


            }
        }

        if (fieldsNotEmpty) {
            if (mToast != null) {
                mToast.cancel();
            }
            mToast = Toast.makeText(this, "Pflichtfelder bitte ausfüllen!", Toast.LENGTH_SHORT);
            mToast.show();
            return false;
        }

        return true;
    }

    private boolean arePasswordsIdentical() {
        EditText pass = (EditText) findViewById(R.id.et_register_ID_8);
        EditText pass2 = (EditText) findViewById(R.id.et_register_ID_9);

        if (!pass.getText().toString().isEmpty() && !pass2.getText().toString().isEmpty() &&
                (pass.getText().toString().equals(pass2.getText().toString()))) {
            pass.setBackgroundResource(R.drawable.border_edit_text);
            pass2.setBackgroundResource(R.drawable.border_edit_text);

            return true;
        }

        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, "Passwörter stimmen nicht überein", Toast.LENGTH_SHORT);
        mToast.show();
        pass.setBackgroundResource(R.drawable.border_edit_text_empty);
        pass2.setBackgroundResource(R.drawable.border_edit_text_empty);



        return false;
    }
}
