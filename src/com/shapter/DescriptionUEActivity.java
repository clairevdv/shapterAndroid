package com.shapter;

import ueBDD.CoursDAO;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class DescriptionUEActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_description_ue);
		// Show the Up button in the action bar
		setupActionBar();

		TextView description = (TextView) findViewById(R.id.descriptionUE);
		Intent intent = getIntent();
		if (intent != null) {
			String stringRowid = intent.getStringExtra("nomUE");
			long rowid = Long.valueOf(stringRowid);
			CoursDAO cDAO = new CoursDAO(this);
			cDAO.open();
			String descriptionUE = cDAO.rowidToDescription(rowid);
			cDAO.close();
			
			description.setText(descriptionUE);
		}
		else
			System.out.println("Les donn�es ne sont pas pass�es entre les deux activit�s au clic sur l'UE");
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
		getMenuInflater().inflate(R.menu.description_ue, menu);
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