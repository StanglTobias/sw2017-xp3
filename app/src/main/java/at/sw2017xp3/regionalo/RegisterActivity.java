package at.sw2017xp3.regionalo;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import at.sw2017xp3.regionalo.model.Core;
import at.sw2017xp3.regionalo.model.CurrentUser;
import at.sw2017xp3.regionalo.util.HttpUtils;
import at.sw2017xp3.regionalo.util.OnTaskCompleted;
import at.sw2017xp3.regionalo.util.Security;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    Button button;
    Toast mToast;
    private static final String LOGIN_URL = "http://sw-ma-xp3.bplaced.net/MySQLadmin/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button = (Button) findViewById(R.id.Button_ID_ConfirmRegistration);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Button_ID_ConfirmRegistration:
                if (areRequiredFieldsNotEmpty() && arePasswordsIdentical())
                    register();
                break;
        }
    }

    private void register() {
        HashMap<String, String> registerFields = new HashMap<>();
        registerFields.put("company_name", ((EditText) findViewById(R.id.et_register_ID_0)).getText().toString());
        registerFields.put("first_name", ((EditText) findViewById(R.id.et_register_ID_1)).getText().toString());
        registerFields.put("last_name", ((EditText) findViewById(R.id.et_register_ID_2)).getText().toString());
        registerFields.put("email", ((EditText) findViewById(R.id.et_register_ID_3)).getText().toString());
        registerFields.put("phone_number", ((EditText) findViewById(R.id.et_register_ID_4)).getText().toString());
        registerFields.put("city", ((EditText) findViewById(R.id.et_register_ID_5)).getText().toString());
        registerFields.put("postal_code", ((EditText) findViewById(R.id.et_register_ID_6)).getText().toString());
        registerFields.put("address", ((EditText) findViewById(R.id.et_register_ID_7)).getText().toString());

        String passHashed = null;
        try {
            passHashed = Security.SHA1(((EditText) findViewById(R.id.et_register_ID_9)).getText().toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        registerFields.put("password", passHashed);

        // TODO Fields are missing according to database - enter new fields in UI and here

        new RegisterUser().execute(registerFields);
    }



    private class RegisterUser extends AsyncTask<HashMap<String, String>, Void, JSONObject> {

        @SafeVarargs
        @Override
        protected final JSONObject doInBackground(HashMap<String, String>... params) {

            Uri uri = Uri.parse("http://sw-ma-xp3.bplaced.net/MySQLadmin/userAlreadyInDatabase.php")
                    .buildUpon()
                    .appendQueryParameter("email", params[0].get("email")).build();


            String val = null;
            try {
                val = HttpUtils.downloadContent(uri.toString(), 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if ("0".equals(val))//User is not in database (email)
                return HttpUtils.postContent(LOGIN_URL, params[0]);

            return null;
        }

        @Override
        protected void onPostExecute(JSONObject json) {

            if (json != null) {
                System.out.println("Ergebnis : " + json.toString());
                try {
                    if (json.getString("result").equals("1")) //Inserting into database was ok
                        Toast.makeText(RegisterActivity.this, getResources().getString(R.string.userSucessfullyRegistered),
                                Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(RegisterActivity.this, "Es ist ein Fehler bei der Registrierung aufgetreten!",
                                Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            else
                Toast.makeText(RegisterActivity.this, "Der User (EMAIL) ist bereits vorhanden - Registrierung fehlgeschlagen!",
                        Toast.LENGTH_LONG).show();
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
            mToast = Toast.makeText(this, getText(R.string.enterComulsoryFields), Toast.LENGTH_SHORT);
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
        mToast = Toast.makeText(this, getText(R.string.passwordNotMatching), Toast.LENGTH_SHORT);
        mToast.show();
        pass.setBackgroundResource(R.drawable.border_edit_text_empty);
        pass2.setBackgroundResource(R.drawable.border_edit_text_empty);
        return false;
    }
}
