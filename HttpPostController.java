package com.leaktd.airlinecompare.Controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpPostController{
    private static final String TAG = HttpPostController.class.getName();


    private String query,urls;
    private Context context;
    private Controller controller;

    private String reply;
    private boolean saveUser=false,getUser=false;

    public HttpPostController() {
       // Controller.initialize();
        controller=Controller.getInstance();
    }
    public String fillUserUri(HashMap<String,String> values){
        Uri.Builder builder = new Uri.Builder();
        for(Map.Entry<String, String> entry : values.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            builder.appendQueryParameter(key, value);
        }

        return builder.build().getEncodedQuery();
    }

    public void sendHttpPostRequest(String url) {
        this.urls=url;
        this.query=url;
        Thread HttpThread = new Thread(loadRunnable);
        HttpThread.start();
    }


    private Runnable loadRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            URL url = null;
            Controller controller=Controller.getInstance();
            try {
                url = new URL(urls);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Key", "Value");


                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

                conn.connect();


                InputStream in = conn.getInputStream();
                StringBuffer sb = new StringBuffer();
                try {
                    int chr;
                    while ((chr = in.read()) != -1) {
                        sb.append((char) chr);
                    }
                    reply = sb.toString();
                    Log.d(TAG,"Result:"+reply);
                    controller.onHttpResult(reply);
                } finally {
                    in.close();
                }

            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };



}



