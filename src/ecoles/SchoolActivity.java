package ecoles;

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

import com.shapter.R;

@SuppressLint("NewApi")
public class SchoolActivity extends Activity implements OnItemSelectedListener {

	private SchoolDAO sDAO;
	private SimpleCursorAdapter EcoleAdapter;
	private SimpleCursorAdapter listePaysAdapter;
	private String pays = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_school);
		// Show the Up button in the action bar.
		setupActionBar();

		// On cree un filtre par pays des écoles et on les affiche
		sDAO = new SchoolDAO(this);
		sDAO.open();
		sDAO.initialiserTest();
		afficherChoixTri();
		afficherEcoles();		
	}

	private void afficherChoixTri() {
		// On cree un adapteur des ecoles de la BDD au spinner
		Cursor listePays = sDAO.listePays();
		String[] colonnes = new String[] {"country"};
		int[] textViewAModifier = new int[] {android.R.id.text1};
		listePaysAdapter = new SimpleCursorAdapter(
				this, android.R.layout.simple_spinner_item,
				listePays,
				colonnes,
				textViewAModifier,
				0);
		listePaysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);	
		Spinner spinner = (Spinner) findViewById(R.id.choixPays);
		spinner.setAdapter(listePaysAdapter);

		// On filtre l'affichage au clic sur un pays
		spinner.setOnItemSelectedListener(this);
	}

	private void afficherEcoles() {
		// On cree un adapteur de la bdd a une listView
		Cursor listeCoursAffichage = sDAO.tableAffichageEcole(pays);
		String[] colonnes = new String[] {"country", "name"};
		int[] textViewAModifier = new int[] {
				R.id.pays,
				R.id.nomEcole,
		};
		EcoleAdapter = new SimpleCursorAdapter(
				this, R.layout.liste_ecoles,
				listeCoursAffichage,
				colonnes,
				textViewAModifier,
				0);
		EcoleAdapter.notifyDataSetChanged ();

		// On assigne l'adapteur a la ListView
		ListView listView = (ListView) findViewById(R.id.listViewEcoles);
		listView.setAdapter(EcoleAdapter);

		// On ouvre la vue de description au clic sur l'ecole
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
				// On recupere la description a afficher
				Cursor cursor = (Cursor) listView.getItemAtPosition(position);
				String descriptif = cursor.getString(cursor.getColumnIndexOrThrow("description"));

				// On la lance dans une nouvelle activite
				Intent descriptionEcole = new Intent(SchoolActivity.this,DescriptionEcoleActivity.class);
				descriptionEcole.putExtra("web_ecole", descriptif);
				startActivity(descriptionEcole);
				finish();
			}
		});

		// On cree un systeme de recherche d'UE
		EditText myFilterEcole = (EditText) findViewById(R.id.myFilterEcole);
		TextWatcher textWatcher = new TextWatcher() {
			public void afterTextChanged(Editable s) {
			}
			public void beforeTextChanged(CharSequence s, int start,
					int count, int after) {
			}
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				EcoleAdapter.getFilter().filter(s.toString());
			}
		};
		myFilterEcole.addTextChangedListener(textWatcher);

		FilterQueryProvider query = new FilterQueryProvider() {
			public Cursor runQuery(CharSequence constraint) {
				return sDAO.ecoleByNom(pays, constraint.toString());
			}
		};
		EcoleAdapter.setFilterQueryProvider(query);
	}

	// On définit ce qui se passe au clic sur le spinner
	public void onItemSelected(AdapterView<?> parent, View view, 
			int pos, long id) {
			Cursor cursor = (Cursor) parent.getItemAtPosition(pos);
			pays = cursor.getString(cursor.getColumnIndexOrThrow("country"));
			afficherEcoles();
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
