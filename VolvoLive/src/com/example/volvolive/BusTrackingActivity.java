package com.example.volvolive;

import java.sql.Array;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

@TargetApi(Build.VERSION_CODES.KITKAT) public class BusTrackingActivity extends Activity implements AsyncResponse {

	// Google Map
	private static GoogleMap googleMap;
	MapUpdationService asyncTask = new MapUpdationService(); 

	public void runOnPostExecute()
	{
		
	}
//	private class MyTask extends AsyncTask<String, String, String> { 
//		@Override
//		protected
//        String doInBackground(String ...arg){
//        	String responseString = null;
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpGet httpget = new HttpGet("http://volvolive.jelastic.servint.net/svcProject/rest/volvolive/nearestBus"+"?busnum="+arg[0]+"&currentStop="+arg[1]+"&destinationStop="+arg[2]);
//            httpclient = new DefaultHttpClient();
//
//            try {
//
//                HttpResponse response;
//                response = httpclient.execute(httpget);
//                StatusLine statusLine = response.getStatusLine();
//                if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
//                	responseString = new BasicResponseHandler().handleResponse(response);
//                	
//                    Log.e("Google1", "Server Responded OK");
//                    Log.e("Google1",responseString);
//                   
//                } else {
//
//                    response.getEntity().getContent().close();
//                    throw new IOException(statusLine.getReasonPhrase());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return responseString;
//        }
//
//        protected void onPostExecute(String response){
//        	try {
//    			JSONObject ob=new JSONObject(response);
//    			
//    			JSONArray aryJSONStrings = new JSONArray();
//    			aryJSONStrings=(JSONArray) ob.get("responseList");
//  
//    			for (int i = 0; i < aryJSONStrings.length(); i++) {
//    				try {
//    					
//    					initilizeMap();
//    					
//    					Log.e("Data",
//    							aryJSONStrings.getJSONObject(i).getString(
//    									"latitude"));
//    					String latitude=aryJSONStrings.getJSONObject(i).getString(
//    							"latitude");
//
//    					Log.e("Data",
//    							aryJSONStrings.getJSONObject(i).getString(
//    									"longitude"));
//    					String longitude=aryJSONStrings.getJSONObject(i).getString(
//    							"longitude");
//    					CameraPosition cameraPosition = new CameraPosition.Builder()
//    							.target(new LatLng(Double.parseDouble(latitude),Double.parseDouble( longitude))).zoom(15).build();
//    					
//    					// Changing map type
//    					googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//    					// googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//    					// googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//    					// googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
//    					// googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
//
//    					// Showing / hiding your current location
//    					googleMap.setMyLocationEnabled(true);
//
//    					// Enable / Disable zooming controls
//    					googleMap.getUiSettings().setZoomControlsEnabled(false);
//
//    					// Enable / Disable my location button
//    					googleMap.getUiSettings().setMyLocationButtonEnabled(true);
//
//    					// Enable / Disable Compass icon
//    					googleMap.getUiSettings().setCompassEnabled(true);
//
//    					// Enable / Disable Rotate gesture
//    					googleMap.getUiSettings().setRotateGesturesEnabled(true);
//
//    					// Enable / Disable zooming functionality
//    					googleMap.getUiSettings().setZoomGesturesEnabled(true);
//
//    					googleMap.animateCamera(CameraUpdateFactory
//    							.newCameraPosition(cameraPosition));
//
//    					Bundle extras1 = getIntent().getExtras();
//    					String[] arg1 = null;
//    					if (extras1 != null) {
//    						arg1 = extras1.getStringArray("userDetails");
//    					}
//    					new MapUpdationService().execute(arg1);
//
//    					final LatLng MELBOURNE = new LatLng(Double.parseDouble(latitude),Double.parseDouble( longitude));
//    					Marker melbourne = googleMap.addMarker(new MarkerOptions()
//    							.position(MELBOURNE)
//    							.title("Melbourne")
//    							.snippet("Population: 4,137,400")
//    							.icon(BitmapDescriptorFactory
//    									.fromResource(R.drawable.bussmall)));
//
//    				
//    				} catch (JSONException e) {
//    					// TODO Auto-generated catch block
//    					e.printStackTrace();
//    				}
//    			}
//    		} catch (JSONException e1) {
//    			// TODO Auto-generated catch block
//    			e1.printStackTrace();
//    		}
//
//    	}
//
//            runOnPostExecute();
//        }
//
//    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bus_tracking);
		Bundle extras = getIntent().getExtras();
		String[] arg = null;
		if (extras != null) {
			arg = extras.getStringArray("userDetails");
		}
		Log.e("Data", arg[0] + " " + arg[1] + " " + arg[2]);

		try {
			// googleMap.clear();
			// Loading map
			initilizeMap();

			// Changing map type
			googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			// googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);

			// Showing / hiding your current location
			googleMap.setMyLocationEnabled(true);

			// Enable / Disable zooming controls
			googleMap.getUiSettings().setZoomControlsEnabled(false);

			// Enable / Disable my location button
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);

			// Enable / Disable Compass icon
			googleMap.getUiSettings().setCompassEnabled(true);

			// Enable / Disable Rotate gesture
			googleMap.getUiSettings().setRotateGesturesEnabled(true);

			// Enable / Disable zooming functionality
			googleMap.getUiSettings().setZoomGesturesEnabled(true);

			double latitude = 17.385044;
			double longitude = 78.486671;
			GPSTracker gps = new GPSTracker(this);

			// check if GPS enabled
			if (gps.canGetLocation()) {

				latitude = gps.getLatitude();
				longitude = gps.getLongitude();
			}

			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(new LatLng(latitude, longitude)).zoom(15).build();

			googleMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));

			final LatLng CURRENT = new LatLng(latitude, longitude);
			Marker currentLocation = googleMap.addMarker(new MarkerOptions()
					.position(CURRENT)
					.title("Currently you are here!")
					.snippet(" "));
			
			new MapUpdationService().execute(arg);

			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}

	/**
	 * function to load map If map is not created it will create it for you
	 * */
	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	@Override
	public void processFinish(String response) {

		try {
			JSONObject ob=new JSONObject(response);
			
			JSONArray aryJSONStrings = new JSONArray();
			aryJSONStrings=(JSONArray) ob.get("responseList");
			

			for (int i = 0; i < aryJSONStrings.length(); i++) {
				try {
					
					Log.e("Data",
							aryJSONStrings.getJSONObject(i).getString(
									"latitude"));
					String latitude=aryJSONStrings.getJSONObject(i).getString(
							"latitude");

					Log.e("Data",
							aryJSONStrings.getJSONObject(i).getString(
									"longitude"));
					String longitude=aryJSONStrings.getJSONObject(i).getString(
							"longitude");
					
					CameraPosition cameraPosition = new CameraPosition.Builder()
							.target(new LatLng(Double.parseDouble(latitude),Double.parseDouble( longitude))).zoom(15).build();
					googleMap.animateCamera(CameraUpdateFactory
							.newCameraPosition(cameraPosition));
					
					String distanceTime = aryJSONStrings.getJSONObject(i).getString("distanceParams");
					Log.e("Data1",distanceTime);

					
					String[] distTime = distanceTime.split("\\|");
					distTime[0] = distTime[0].replaceAll("\\+", ",");
					distTime[1] = distTime[1].replaceAll("\\+", ",");
					String[] total= distTime[0].split(",");
					String[] waiting = distTime[1].split(",");
					final LatLng NEIGHBOR = new LatLng(Double.parseDouble(latitude),Double.parseDouble( longitude));
					Marker neighbor = googleMap.addMarker(new MarkerOptions()
					.position(NEIGHBOR)
					.title("I'm on my way!")
					.snippet("Waiting time:"+waiting[1]+" Total travel time:"+total[1]+" Total travel distance:"+total[0])
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.bussmall)));
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(aryJSONStrings.length()==0)
			{
				new AlertDialog.Builder(this).setTitle("Oh snap!").setMessage("We currently do not have any information of buses from your route").setNeutralButton("Close", null).show();
				Toast toast = Toast.makeText(getApplicationContext(), "We currently do not have any information of buses from your route", Toast.LENGTH_LONG);
				toast.show();
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
