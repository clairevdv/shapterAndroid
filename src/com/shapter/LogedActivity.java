package com.shapter;

import login.ProfilActivity;
import ue.UEActivity;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import ecoles.SchoolActivity;

public class LogedActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loged);
		// Show the Up button in the action bar.
		setupActionBar();
		
		TextView bienvenue = (TextView) findViewById(R.id.loged);
		Intent intent = getIntent();
		if (intent != null) {
			String username = intent.getStringExtra("username");
			bienvenue.setText("Bienvenue " + username + " !");
		}
		else
			System.out.println("Les donn�es ne sont pas pass�es entre les deux activit�s au moment du login");
	}
	
	public void ue(View view) {
		// Do something in response to button
		Intent intentUE = new Intent(this, UEActivity.class);
		startActivity(intentUE);
	}

	public void ecoles(View view) {
		// Do something in response to button
		Intent intentEcoles = new Intent(this, SchoolActivity.class);
		startActivity(intentEcoles);
	}
	
	public void profil(View view) {
		// Do something in response to button
		Intent intentProfil = new Intent(this, ProfilActivity.class);
		startActivity(intentProfil);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.action_bar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. 
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.menu_ue:
			Intent intentUE = new Intent(this, UEActivity.class);
			startActivity(intentUE);
			return true;
		case R.id.menu_ecole:
			Intent intentEcole = new Intent(this, SchoolActivity.class);
			startActivity(intentEcole);
			return true;
		case R.id.menu_eleves:
			// Comportement du bouton "Rafraichir"
			return true;
		case R.id.menu_login:
			Intent intentProfil = new Intent(this, ProfilActivity.class);
			startActivity(intentProfil);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
