package ue;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.shapter.R;
import commentaires.CommentActivity;

public class DescriptionUEActivity extends Activity {	
	private UEDAO uDAO;
	private String course_id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_description_ue);
		// Show the Up button in the action bar
		setupActionBar();
		
		TextView description = (TextView) findViewById(R.id.descriptionUE);
		Intent intent = getIntent();
		if (intent != null) {
			course_id = intent.getStringExtra("course_id");
			uDAO = new UEDAO(this);
			uDAO.open();
			uDAO.initialiserTest();
			Cursor ue = uDAO.ueById(course_id);
			uDAO.close();
			
			String descriptionUE = ue.getString(5);
			description.setText(descriptionUE);
		}
		else
			System.out.println("Les donn�es ne sont pas pass�es entre les deux activit�s au clic sur l'UE");
	}
	
	public void comment(View view) {
		Intent commentEcole = new Intent(DescriptionUEActivity.this, CommentActivity.class);
		commentEcole.putExtra("course_id", course_id);
		startActivity(commentEcole);
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
		getMenuInflater().inflate(R.menu.action_bar, menu);
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
