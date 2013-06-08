package com.shapter;

import testBDD.*;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.MenuItem;
import android.widget.ListView;

public class DiscoveryActivity extends Activity {
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_discovery);
		// Show the Up button in the action bar.
		setupActionBar();

		// Test de la BDD
		CoursDAO cDAO = new CoursDAO(this);
		cDAO.open();
		cDAO.initialiserTest();
		
		Cursor listeCours = cDAO.recupererTable();
		String[] columns = new String[] {DatabaseHandler.getColumn1(),DatabaseHandler.getColumn2()};
		
		// On lie les donnes au XML
		int[] to = new int[] {
				R.id.parcours,
				R.id.titreUE,
		};

		// On cree l'adapteur
		SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(
				this, R.layout.activity_ue,
				listeCours,
				columns,
				to,
				0);
		dataAdapter.notifyDataSetChanged ();
		
		// On assigne l'adapteur a la ListView
		ListView listView = (ListView) findViewById(R.id.listViewCours);
		listView.setAdapter(dataAdapter);
		
		cDAO.close();
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. 
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
