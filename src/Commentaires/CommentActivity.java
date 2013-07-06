package Commentaires;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.shapter.R;

public class CommentActivity extends Activity {

	private CommentDAO cDAO;
	private SimpleCursorAdapter CommentAdapter;
	private int course_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment);
		// Show the Up button in the action bar.
		setupActionBar();

		cDAO = new CommentDAO(this);
		cDAO.open();
		cDAO.initialiserTest();
		
		Intent intent = getIntent();
		if (intent != null) {
			String id = intent.getStringExtra("course_id");
			course_id = Integer.valueOf(id);
		}
		else
			System.out.println("Les données ne sont pas passées entre les deux activités au clic sur l'UE"); 
		afficherCommentaires(course_id);	
	}

	private void afficherCommentaires(int course_id) {
		// On cree un adapteur de la bdd a une listView
		Cursor listeCommentAffichage = cDAO.tableAffichageComment(course_id);
		String[] colonnes = new String[] {"student_id", "comment"};
		int[] textViewAModifier = new int[] {
				R.id.pays,
				R.id.nomEcole,
		};
		CommentAdapter = new SimpleCursorAdapter(
				this, R.layout.liste_ecoles,
				listeCommentAffichage,
				colonnes,
				textViewAModifier,
				0);
		CommentAdapter.notifyDataSetChanged ();

		// On assigne l'adapteur a la ListView
		ListView listView = (ListView) findViewById(R.id.listViewComment);
		listView.setAdapter(CommentAdapter);
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comment, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
