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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;

public class DiscoveryActivity extends Activity {
	@SuppressLint("NewApi")

	private CoursDAO cDAO;
	private SimpleCursorAdapter dataAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_discovery);
		// Show the Up button in the action bar.
		setupActionBar();

		// Test de la BDD
		cDAO = new CoursDAO(this);
		cDAO.open();
		cDAO.initialiserTest();

		afficherCours();
	}

	private void afficherCours() {
		// On cree un adapteur de la bdd a une listView
		Cursor listeCours = cDAO.recupererTable();
		String[] colonnes = new String[] {DatabaseHandler.getColumn1(),DatabaseHandler.getColumn2()};
		int[] textViewAModifier = new int[] {
				R.id.parcours,
				R.id.titreUE,
		};
		dataAdapter = new SimpleCursorAdapter(
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
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
				// On recupere la description a afficher
				Cursor cursor = (Cursor) listView.getItemAtPosition(position);
				String descriptif = cursor.getString(cursor.getColumnIndexOrThrow("description"));
				
				// On la lance dans une nouvelle activite
				Intent descriptionUE = new Intent(DiscoveryActivity.this,DescriptionUEActivity.class);
				descriptionUE.putExtra("descriptionUE", descriptif);
				startActivity(descriptionUE);
				finish();
			}
		});

		// On cree un systeme de recherche d'UE
		EditText myFilter = (EditText) findViewById(R.id.myFilter);
		TextWatcher textWatcher = new TextWatcher() {
			public void afterTextChanged(Editable s) {
			}
			public void beforeTextChanged(CharSequence s, int start,
					int count, int after) {
			}
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				dataAdapter.getFilter().filter(s.toString());
			}
		};
		myFilter.addTextChangedListener(textWatcher);

		FilterQueryProvider query = new FilterQueryProvider() {
			public Cursor runQuery(CharSequence constraint) {
				return cDAO.ueByNom(constraint.toString());
			}
		};
		dataAdapter.setFilterQueryProvider(query);
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
