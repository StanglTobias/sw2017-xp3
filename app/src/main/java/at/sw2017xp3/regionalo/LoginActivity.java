package at.sw2017xp3.regionalo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

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
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import at.sw2017xp3.regionalo.util.Security;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<View> list_of_elements = new ArrayList<>();
    private String phpurl = "http://sw-ma-xp3.bplaced.net/MySQLadmin/getLoginData.php";
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

            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                url = new URL(phpurl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "exception";
            }
            try {
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(10000); //10 sec
                conn.setConnectTimeout(5000); //5sec
                conn.setRequestMethod("POST");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("username", params[0])
                        .appendQueryParameter("password", Security.SHA1(params[1]));

                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();
            } catch (IOException e1) {
                e1.printStackTrace();
                return "exception";
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
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
                    return "unsuccessful";
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            pdLoading.dismiss();

            if(logged_user_id.equals("false")) {
                setWrongUsernamePasswordTextView();
                return;
            }

            if(result.equalsIgnoreCase(logged_user_id)) {
                Intent intent = new Intent(LoginActivity.this, ReleaseAdActivity.class);
                intent.putExtra("logged_user_id", logged_user_id);
                startActivity(intent);
                LoginActivity.this.finish();
            } else if (result.equalsIgnoreCase("false")) {
                Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_LONG).show();

            } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {

                Toast.makeText(LoginActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

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