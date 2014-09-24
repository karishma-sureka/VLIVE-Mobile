package com.example.volvolive;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class UserInfoWebService extends AsyncTask<String, String, Boolean> {

public UserInfoWebService() {
    // TODO Auto-generated constructor stub
}

@Override
protected Boolean doInBackground(String... arg) {

    HttpClient httpclient = new DefaultHttpClient();
    String url = "http://volvolive.jelastic.servint.net/svcProject/rest/volvolive/firstUser"+"?userid="+arg[0]+"&boardpoint="+arg[1]+"&droppoint="+arg[2]+"&busnumb="+arg[3]+"&currentcoord="+arg[4];
    HttpGet httpget = new HttpGet(URLEncoder.encode(url));
    httpclient = new DefaultHttpClient();

    try {

        HttpResponse response;
        response = httpclient.execute(httpget);
        StatusLine statusLine = response.getStatusLine();
        if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
        	String responseString = new BasicResponseHandler().handleResponse(response);
            Log.e("Google", "Server Responded OK for User Details");
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
