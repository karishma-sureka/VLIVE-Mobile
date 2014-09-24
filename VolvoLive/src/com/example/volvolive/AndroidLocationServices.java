package com.example.volvolive;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class AndroidLocationServices extends IntentService {

public AndroidLocationServices() {
		super("AndroidLocationServices");
		// TODO Auto-generated constructor stub
	}

//WakeLock wakeLock;

private LocationManager locationManager;

final String TRACK_URL = "localhost:8080/svcProject/rest/volvolive/updateCoords";


@Override
public IBinder onBind(Intent arg0) {
    // TODO Auto-generated method stub
    return null;
}

@Override
public void onCreate() {
    // TODO Auto-generated method stub
    super.onCreate();
//
//    PowerManager pm = (PowerManager) getSystemService(this.POWER_SERVICE);
//
//    wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "DoNotSleep");

    // Toast.makeText(getApplicationContext(), "Service Created",
    // Toast.LENGTH_SHORT).show();

    Log.e("Google", "Service Created");

}


private LocationListener listener = new LocationListener() {

    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub

        Log.e("Google", "Location Changed");

        if (location == null)
            return;

        if (isConnectingToInternet(getApplicationContext())) {

            try {
                Log.e("latitude", location.getLatitude() + "");
                Log.e("longitude", location.getLongitude() + "");
                String userid = "deepak";
                String coord = location.getLatitude()+","+location.getLongitude();

                Log.e("request", userid + "," + coord);

                new LocationWebService().execute(new String[] {userid, coord});
                
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
};

@Override
public void onDestroy() {
    // TODO Auto-generated method stub
    super.onDestroy();

//    wakeLock.release();

}

public static boolean isConnectingToInternet(Context _context) {
    ConnectivityManager connectivity = (ConnectivityManager) _context
            .getSystemService(Context.CONNECTIVITY_SERVICE);
    if (connectivity != null) {
        NetworkInfo[] info = connectivity.getAllNetworkInfo();
        if (info != null)
            for (int i = 0; i < info.length; i++)
                if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }

    }
    return false;
}

@Override
protected void onHandleIntent(Intent intent) {
	Log.e("Google", "Service Started");

	locationManager = (LocationManager) getApplicationContext()
	        .getSystemService(Context.LOCATION_SERVICE);
	
	locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
	        50, 0, listener);
	    
	SystemClock.sleep(10000);
	
	//should be removed
    Log.e("Google", "Location Changed");
	Location location = new Location("");//provider name is unecessary
	GPSTracker gps = new GPSTracker(this);
	 
     // check if GPS enabled     
     if(gps.canGetLocation()){
          
         double latitude = gps.getLatitude();
         double longitude = gps.getLongitude();
         Log.e("GPSTracker", latitude + "," + longitude);
         
         if (isConnectingToInternet(getApplicationContext())) {

             try {
                 String userid = "deepak";
                 String coord =  latitude + "," + longitude;

                 Log.e("request", userid + "," + coord);

                 new LocationWebService().execute(new String[] {userid, coord});
                 
//                 Intent broadcastIntent = new Intent();
//                 broadcastIntent.setAction(MyWebRequestReceiver.PROCESS_RESPONSE);
//                 broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
//                 broadcastIntent.putExtra("LATITUDE", latitude);
//                 broadcastIntent.putExtra("LONGITUDE", longitude);
//                 sendBroadcast(broadcastIntent);

                 
             } catch (Exception e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
         }

         // \n is for new line
     }else{
         // can't get location
         // GPS or Network is not enabled
         // Ask user to enable GPS/network in settings
         gps.showSettingsAlert();
     }

    
    }//end of should be removed
    
}


