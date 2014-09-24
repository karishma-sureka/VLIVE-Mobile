package com.example.volvolive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyWebRequestReceiver extends BroadcastReceiver{
	 
    public static final String PROCESS_RESPONSE = "com.as400samplecode.intent.action.PROCESS_RESPONSE";

    @Override
    public void onReceive(Context context, Intent intent) {
//        String responseString = intent.getStringExtra(AndroidLocationServices.RESPONSE_STRING);
//        String reponseMessage = intent.getStringExtra(AndroidLocationServices.RESPONSE_MESSAGE);
//
//        TextView myTextView = (TextView) findViewById(R.id.response);
//        myTextView.setText(responseString);
//
//        WebView myWebView = (WebView) findViewById(R.id.myWebView);
//        myWebView.getSettings().setJavaScriptEnabled(true);
//        try {
//            myWebView.loadData(URLEncoder.encode(reponseMessage,"utf-8").replaceAll("\\+"," "), "text/html", "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }


    }


}
