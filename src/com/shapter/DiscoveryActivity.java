package com.shapter;

import ueBDD.*;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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

		// On affiche la base de donnees
		Cursor listeCours = cDAO.recupererTable();
		String[] colonnes = new String[] {DatabaseHandler.getColumn1(),DatabaseHandler.getColumn2()};

		int[] textViewAModifier = new int[] {
				R.id.parcours,
				R.id.titreUE,
		};

		SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(
				this, R.layout.activity_liste_ue,
				listeCours,
				colonnes,
				textViewAModifier,
				0);
		dataAdapter.notifyDataSetChanged ();

		// On assigne l'adapteur a la ListView
		ListView listView = (ListView) findViewById(R.id.listViewCours);
		listView.setAdapter(dataAdapter);

		// On ouvre la vue de description au clic sur l'UE
		OnItemClickListener ouvrirDescription = new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent descriptionUE = new Intent(DiscoveryActivity.this,DescriptionUEActivity.class);
				String idToString = String.valueOf(id);
				descriptionUE.putExtra("nomUE", idToString);
				startActivity(descriptionUE);
				finish();
			}
		};
		listView.setOnItemClickListener(ouvrirDescription);

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
