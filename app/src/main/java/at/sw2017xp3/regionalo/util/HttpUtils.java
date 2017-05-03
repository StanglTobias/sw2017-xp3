package at.sw2017xp3.regionalo.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    public static String convertInputStreamToString(InputStream stream, int length) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[length];
        reader.read(buffer);
        return new String(buffer);
    }

    public static String downloadContent(String myurl) throws IOException {
        InputStream is = null;
        int length = 10000;

        try {
            HttpURLConnection conn = HttpUtils.httpGet(myurl);

            return  HttpUtils.convertInputStreamToString(conn.getInputStream(), length);
        } catch (Exception ex) {
            return "";
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}
