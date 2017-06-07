package at.sw2017xp3.regionalo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<View> list_of_elements = new ArrayList<>();
    private String phpurl;
    private static String logged_user_id;

    public static Boolean loggedIn_ = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phpurl = getString(R.string.loginDataLink);

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

        final String email      = ((TextView)findViewById(R.id.textViewEmail)).getText().toString();
        final String password   = ((TextView)findViewById(R.id.textViewPassword)).getText().toString();

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
            try {
                url = new URL(phpurl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return getString(R.string.exception);
            }
            try {
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(10000); //10 sec
                conn.setConnectTimeout(5000); //5sec
                conn.setRequestMethod(getString(R.string.POST));

                conn.setDoInput(true);
                conn.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter(getString(R.string.username), params[0])
                        .appendQueryParameter(getString(R.string.password), params[1]);

                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, getString(R.string.UTF8)));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();
            } catch (IOException e1) {
                e1.printStackTrace();
                return getString(R.string.exception);
            }

            try {
                int response_code = conn.getResponseCode();

                if(response_code == HttpURLConnection.HTTP_OK) {
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    setLoggedUserId(result.toString());
                    return (result.toString());
                } else {
                    return getString(R.string.unsuccessful);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return getString(R.string.exception);
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            pdLoading.dismiss();

            if(logged_user_id.equals(getString(R.string.falseStatement))) {
                setWrongUsernamePasswordTextView();
                return;
            }

            if(result.equalsIgnoreCase(logged_user_id)) {
                loggedIn_ = true;
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

    public static String getLoggedUserId() {
        return logged_user_id;
    }


    public void setWrongUsernamePasswordTextView() {
        ((TextView) findViewById(R.id.textView_ID_LoginErrors)).setText(getString(R.string.missingPW_Email));
    }
}