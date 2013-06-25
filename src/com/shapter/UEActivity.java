package com.shapter;

import ueBDD.SchoolDAO;
import ueBDD.UEDAO;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.Spinner;

@SuppressLint("NewApi")
public class UEActivity extends Activity implements OnItemSelectedListener {

	private UEDAO cDAO;
	private SchoolDAO sDAO;
	private SimpleCursorAdapter UEAdapter;
	private SimpleCursorAdapter listeEcolesAdapter;
	private int school = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_ue);
		// Show the Up button in the action bar.
		setupActionBar();

		// On cree un filtre par ecole des UE
		sDAO = new SchoolDAO(this);
		sDAO.open();
		sDAO.initialiserTest();
		afficherChoixTri();

		// On affiche les UE disponibles
		cDAO = new UEDAO(this);
		cDAO.open();
		cDAO.initialiserTest();
		afficherCours();		
	}

	private void afficherChoixTri() {
		// On cree un adapteur des ecoles de la BDD au spinner
		Cursor listeEcoles = sDAO.listeEcoles();
		String[] colonnes = new String[] {"name"};
		int[] textViewAModifier = new int[] {android.R.id.text1};
		listeEcolesAdapter = new SimpleCursorAdapter(
				this, android.R.layout.simple_spinner_item,
				listeEcoles,
				colonnes,
				textViewAModifier,
				0);
		listeEcolesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);	
		Spinner spinner = (Spinner) findViewById(R.id.choixEcole);
		spinner.setAdapter(listeEcolesAdapter);

		// On filtre l'affichage au clic sur une école
		spinner.setOnItemSelectedListener(this);
	}

	private void afficherCours() {
		// On cree un adapteur de la bdd a une listView
		Cursor listeCoursAffichage = cDAO.tableAffichageUE(school);
		String[] colonnes = new String[] {"code", "title"};
		int[] textViewAModifier = new int[] {
				R.id.code,
				R.id.titreUE,
		};
		UEAdapter = new SimpleCursorAdapter(
				this, R.layout.liste_ue,
				listeCoursAffichage,
				colonnes,
				textViewAModifier,
				0);
		UEAdapter.notifyDataSetChanged ();

		// On assigne l'adapteur a la ListView
		ListView listView = (ListView) findViewById(R.id.listViewCours);
		listView.setAdapter(UEAdapter);

		// On ouvre la vue de description au clic sur l'UE
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
				// On recupere la description a afficher
				Cursor cursor = (Cursor) listView.getItemAtPosition(position);
				String descriptif = cursor.getString(cursor.getColumnIndexOrThrow("description"));

				// On la lance dans une nouvelle activite
				Intent descriptionUE = new Intent(UEActivity.this,DescriptionUEActivity.class);
				descriptionUE.putExtra("descriptionUE", descriptif);
				startActivity(descriptionUE);
				finish();
			}
		});

		// On cree un systeme de recherche d'UE
		EditText myFilterUE = (EditText) findViewById(R.id.myFilterUE);
		TextWatcher textWatcher = new TextWatcher() {
			public void afterTextChanged(Editable s) {
			}
			public void beforeTextChanged(CharSequence s, int start,
					int count, int after) {
			}
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				UEAdapter.getFilter().filter(s.toString());
			}
		};
		myFilterUE.addTextChangedListener(textWatcher);

		FilterQueryProvider query = new FilterQueryProvider() {
			public Cursor runQuery(CharSequence constraint) {
				return cDAO.ueByNom(school, constraint.toString());
			}
		};
		UEAdapter.setFilterQueryProvider(query);
	}

	// On définit ce qui se passe au clic sur le spinner
	public void onItemSelected(AdapterView<?> parent, View view, 
			int pos, long id) {
			Cursor cursor = (Cursor) parent.getItemAtPosition(pos);
			school = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
			afficherCours();
	}
	public void onNothingSelected(AdapterView<?> parent) {}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.action_bar, menu);
		return true;
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
