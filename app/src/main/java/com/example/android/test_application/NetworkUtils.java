package com.example.android.test_application;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    public static String getResponse(URL url) throws IOException {
        String res;
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        try {
            InputStream in = connection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            if (scanner.hasNext())
                res = scanner.next();
            else
                res = null;
        }finally {
            connection.disconnect();
        }
        return res;
    }
}
