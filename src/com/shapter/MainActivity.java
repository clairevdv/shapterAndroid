package com.shapter;

import testConnection.ConnexionActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void discover(View view) {
	    // Do something in response to button
		/*Intent intentDiscovery = new Intent(this, DiscoveryActivity.class);
		startActivity(intentDiscovery);*/
		Intent intentConnexion = new Intent(this, ConnexionActivity.class);
		startActivity(intentConnexion);
	}
	
	public void login(View view) {
	    // Do something in response to button
		Intent intentIdentify = new Intent(this, LoginActivity.class);
		startActivity(intentIdentify);
	}
}
