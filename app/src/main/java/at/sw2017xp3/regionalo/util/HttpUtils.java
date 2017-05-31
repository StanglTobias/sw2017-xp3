package at.sw2017xp3.regionalo.util;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by jo on 05.04.17.
 */

public class HttpUtils {

    private static String CHARSET = "UTF-8";

    public static HttpURLConnection httpGet(String myurl) throws IOException {
        URL url = new URL(myurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();

        return conn;
    }

    private static HttpURLConnection httpPost(String myurl) throws IOException {


        URL url = new URL(myurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept-Charset", CHARSET);
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);

        conn.connect();
        return conn;
    }

    public static JSONObject postContent(String myurl, HashMap<String, String> params) {

        DataOutputStream wr = null;
        StringBuilder sbParams = makeParameters(params);
        try {
            HttpURLConnection conn = httpPost(myurl);
            wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(sbParams.toString());
            wr.flush();
            wr.close();

            return receiveResponse(conn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JSONObject receiveResponse(HttpURLConnection conn) {
        StringBuilder result = null;
        try {
            //Receive the response from the server
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            Log.d("JSON Parser", "result: " + result.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        conn.disconnect();

        JSONObject jObj = null;
        try {
            if (result != null && result.length() == 1)
                jObj = new JSONObject("{\"result\":\"" + result.toString() + "\"}");
            else if (result != null)
                jObj = new JSONObject(result.toString());
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return jObj;
    }

    private static StringBuilder makeParameters(HashMap<String, String> params) {
        StringBuilder sbParams = new StringBuilder();
        int i = 0;
        for (String key : params.keySet()) {
            try {
                if (i != 0)
                    sbParams.append("&");

                sbParams.append(key).append("=")
                        .append(URLEncoder.encode(params.get(key), CHARSET));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            i++;
        }
        return sbParams;
    }

    public static String convertInputStreamToString(InputStream stream, int length) throws IOException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[length];

        reader.read(buffer);
        return new String(buffer);
    }

    public static String downloadContent(String myurl) throws IOException
    {
        return downloadContent(myurl, 3000000);
    }

    public static String downloadContent(String myurl, int length) throws IOException {
        HttpURLConnection conn = null;
        try {
            conn = HttpUtils.httpGet(myurl);

            return HttpUtils.convertInputStreamToString(conn.getInputStream(), length);
        } catch (Exception ex) {
            if (conn != null)
                conn.disconnect();
            return "";
        }
    }
}
