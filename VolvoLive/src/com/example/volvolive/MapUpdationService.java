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

public class MapUpdationService extends AsyncTask<String, String, String> {
	
public BusTrackingActivity delegate=null;

public MapUpdationService() {
    // TODO Auto-generated constructor stub
}

@Override
protected String doInBackground(String... arg) {

	String responseString = null;
    HttpClient httpclient = new DefaultHttpClient();
    String url = "http://volvolive.jelastic.servint.net/svcProject/rest/volvolive/nearestBus"+"?busnum="+arg[0]+"&currentStop="+arg[1]+"&destinationStop="+arg[2];
    
    HttpGet httpget = new HttpGet(url);
    httpclient = new DefaultHttpClient();

    try {

        HttpResponse response;
        response = httpclient.execute(httpget);
        StatusLine statusLine = response.getStatusLine();
        if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
        	responseString = new BasicResponseHandler().handleResponse(response);
        	
            Log.e("Google1", "Server Responded OK");
            Log.e("Google1",responseString);
           
        } else {

            response.getEntity().getContent().close();
            throw new IOException(statusLine.getReasonPhrase());
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return responseString;
}
protected void onPostExecute(String result) {
	delegate = new BusTrackingActivity();
	if(result!=null)
		delegate.processFinish(result);
	else{
		result = "{'responseList':[{'latitude':12.9826381,'longitude':77.7585297,'distance':23}],'responseStatus':200}";
		delegate.processFinish(result);
	}
}

}
