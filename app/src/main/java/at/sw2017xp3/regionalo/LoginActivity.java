package at.sw2017xp3.regionalo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import at.sw2017xp3.regionalo.model.Core;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<View> list_of_elements = new ArrayList<>();
    private String logged_user_id;

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
                checkLogin();
                break;

            case R.id.buttonRegister:
                Intent myIntent = new Intent(this, RegisterActivity.class);
                startActivity(myIntent);
                break;
        }
    }

    public void checkLogin() {


        if (((TextView) findViewById(R.id.textViewEmail)).getText().toString().isEmpty() ||
                ((TextView) findViewById(R.id.textViewPassword)).getText().toString().isEmpty()) {
            ((TextView) findViewById(R.id.textView_ID_LoginErrors)).setText(getString(R.string.missingPW_Email));
            return;
        }

        final String email = ((TextView) findViewById(R.id.textViewEmail)).getText().toString();
        final String password = ((TextView) findViewById(R.id.textViewPassword)).getText().toString();

        new AsyncLogin().execute(email, password);
    }

    private class AsyncLogin extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(LoginActivity.this);

        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdLoading.setMessage(getString(R.string.loading));
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String ret = "";
            try {
                ret = Core.getInstance().getUsers().LogInUser(params[0], params[1]);
                if (!ret.equals("false"))
                    setLoggedUserId(ret);
            } catch (Exception ex) {
                return "false";
            }
            return ret;
        }

        @Override
        protected void onPostExecute(String result) {
            pdLoading.dismiss();

            String some = getString(R.string.falseStatement);
            if (logged_user_id.equals(getString(R.string.falseStatement))) {
                setWrongUsernamePasswordTextView();
                return;
            }

            if (result.equalsIgnoreCase(logged_user_id)) {
                Intent intent = new Intent(LoginActivity.this, ReleaseAdActivity.class);
                intent.putExtra(getString(R.string.loggedUserId), logged_user_id);
                startActivity(intent);
                LoginActivity.this.finish();
            } else if (result.equalsIgnoreCase(getString(R.string.falseStatement))) {
                Toast.makeText(LoginActivity.this, getString(R.string.falsePwandEmail), Toast.LENGTH_LONG).show();

            } else if (result.equalsIgnoreCase(getString(R.string.exception)) || result.equalsIgnoreCase(getString(R.string.unsuccessful))) {

                Toast.makeText(LoginActivity.this, getString(R.string.oops), Toast.LENGTH_LONG).show();

            }
        }

    }

    public void setLoggedUserId(String value) {
        logged_user_id = value;
    }

    public void setWrongUsernamePasswordTextView() {
        ((TextView) findViewById(R.id.textView_ID_LoginErrors)).setText(getString(R.string.missingPW_Email));
    }
}