package com.shapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void discover(View view) {
	    // Do something in response to button
		Intent intentDiscovery = new Intent(this, UEActivity.class);
		startActivity(intentDiscovery);
	}
	
	public void login(View view) {
	    // Do something in response to button
		Intent intentIdentify = new Intent(this, LoginActivity.class);
		startActivity(intentIdentify);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.action_bar, menu);
        System.out.println("I'm here !");
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_ue:
			Intent intentUE = new Intent(this, UEActivity.class);
			startActivity(intentUE);
			return true;
		case R.id.menu_ecole:
			// Comportement du bouton "Aide"
			return true;
		case R.id.menu_eleves:
			// Comportement du bouton "Rafraichir"
			return true;
		case R.id.menu_login:
			Intent intentLogin = new Intent(this, LoginActivity.class);
			startActivity(intentLogin);
			return true;
		case R.id.menu_plus:
			// Comportement du bouton "Paramètres"
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
