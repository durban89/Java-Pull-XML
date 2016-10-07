package org.pull.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by durban126 on 16/10/7.
 */
public class HttpUtils {
    public static InputStream getXML(String path){
        InputStream inputStream = null;
        try {
            URL url = new URL(path);
            if(url != null){
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(3000);
                connection.setDoInput(true);
                connection.setRequestMethod("GET");
                int code = connection.getResponseCode();
                if(code == 200){
                    inputStream = connection.getInputStream();
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }
}
