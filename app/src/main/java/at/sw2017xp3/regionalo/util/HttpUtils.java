package at.sw2017xp3.regionalo.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jo on 05.04.17.
 */

public class HttpUtils {

    public static HttpURLConnection httpGet(String myurl)  throws IOException {
        URL url = new URL(myurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();

        return conn;
    }
/*
    public static HttpURLConnection httpPost(String myurl)  throws IOException {
        URL url = new URL(myurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        conn.setDoInput(true);

        return conn;
    }*/

    public static String convertInputStreamToString(InputStream stream, int length) throws IOException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[length];
        reader.read(buffer);
        return new String(buffer);
    }

    public static String downloadContent(String myurl) throws IOException
    {
        return downloadContent(myurl, 10000);
    }
    public static String downloadContent(String myurl, int length) throws IOException {
        InputStream is = null;
        HttpURLConnection conn = null;
        try {
            conn = HttpUtils.httpGet(myurl);

            return  HttpUtils.convertInputStreamToString(conn.getInputStream(), length);
        } catch (Exception ex) {
            if (conn != null)
            conn.disconnect();
            return "";
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}
