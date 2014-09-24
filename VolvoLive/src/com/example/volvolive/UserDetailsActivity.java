package com.example.volvolive;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class UserDetailsActivity extends Activity implements OnItemSelectedListener{

	Spinner s1,s2,s3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_details);
		
		Intent service = new Intent(getApplicationContext(), AndroidLocationServices.class);
		startService(service);
		
		s1 = (Spinner)findViewById(R.id.bus_number_spinner);
		s2 = (Spinner)findViewById(R.id.bus_source_spinner);
		s3 = (Spinner)findViewById(R.id.bus_destination_spinner);
		s1.setOnItemSelectedListener(this);
		Button button= (Button) findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	String userid = "deepak";//Settings.Secure.ANDROID_ID
		    	String sp1= String.valueOf(s1.getSelectedItem());
		    	String sp2= String.valueOf(s2.getSelectedItem());
		    	String sp3= String.valueOf(s3.getSelectedItem());
		    	double latitude = 0;
		    	double longitude = 0;
		    	GPSTracker gps = new GPSTracker(getApplicationContext());
			     // check if GPS enabled     
			     if(gps.canGetLocation()){
			          
			         latitude = gps.getLatitude();
			         longitude = gps.getLongitude();
			     }
		    	
			     new UserInfoWebService().execute(new String[] {userid, sp2,sp3,sp1,latitude+","+longitude});
		    	 Intent intent = new Intent(getApplicationContext(), BusTrackingActivity.class);
		    	 intent.putExtra("userDetails", new String[]{sp1,sp2,sp3});
	             startActivity(intent);  
		    }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		String sp1= String.valueOf(s1.getSelectedItem());
		if(sp1.contentEquals("201R")) {
			List<String> list = new ArrayList<String>();
			list.add("C V Ramanagar");
			list.add("Bagmane Tech Park");
			list.add("BEML Tech Park");
			list.add("Jeevanbheemanagar");
			list.add("HAL 3rd Stage");
			list.add("Tippasandra");
			list.add("Chinmaya Mission Hospital");
			list.add("Indiranangar Police Station");
			ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
			dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			dataAdapter2.notifyDataSetChanged();
			s2.setAdapter(dataAdapter2);
			s3.setAdapter(dataAdapter2);
		}
		
		else if(sp1.contentEquals("335E")) {
			List<String> list = new ArrayList<String>();
			list.add("Majestic");
			list.add("Kaveri Bank/Mysore Bank");
			list.add("Corporation");
			list.add("Kanteerava Stadium");
			list.add("Mallya Hospital");
			list.add("Richmond Circle");
			list.add("Bangalore Club");
			list.add("Brigade Road Junction");
			list.add("Mayo Hall");
			list.add("Garuda Mall");
			list.add("HOSMAT Hospital");
			list.add("Military Accounts Office");
			list.add("Command Hospital");
			list.add("Domlur");
			list.add("Diamond District");
			list.add("Kodihalli (old airport road)");
			list.add("Manipal Hospital");
			list.add("Murugeshpalya");
			list.add("Konena Agrahara");
			list.add("Vimanapura");
			list.add("HAL main gate");
			list.add("HAL kalyana mantapa");
			list.add("Marathahalli Borewell");
			list.add("Marathahalli");
			list.add("Marathahalli Bridge");
			list.add("Munnekolalu cross");
			list.add("Kundalahalli Gate");
			list.add("BEML Layout");
			list.add("AECS Layout");
			list.add("Kundalahalli Colony");
			list.add("CMRIT College");
			list.add("Graphite India Ltd");
			list.add("Vydehi Hospital");
			list.add("Sathya Sai Hospital");
			list.add("ITPL");
			list.add("Hope Farm");
			list.add("Kadugodi");
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			dataAdapter.notifyDataSetChanged();
			s2.setAdapter(dataAdapter);
			s3.setAdapter(dataAdapter);
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
}
