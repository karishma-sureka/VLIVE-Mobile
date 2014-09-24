package com.example.volvolive;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class LocationWebService extends AsyncTask<String, String, Boolean> {

public LocationWebService() {
    // TODO Auto-generated constructor stub
}

@Override
protected Boolean doInBackground(String... arg) {

    HttpClient httpclient = new DefaultHttpClient();
    HttpGet httpget = new HttpGet("http://volvolive.jelastic.servint.net/svcProject/rest/volvolive/updateCoords"+"?userid="+arg[0]+"&coord="+arg[1]);
    httpclient = new DefaultHttpClient();

    try {

        HttpResponse response;
        response = httpclient.execute(httpget);
        StatusLine statusLine = response.getStatusLine();
        if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
        	String responseString = new BasicResponseHandler().handleResponse(response);
            Log.e("Google", "Server Responded OK");
            Log.e("Google",responseString);
           
        } else {

            response.getEntity().getContent().close();
            throw new IOException(statusLine.getReasonPhrase());
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
}
