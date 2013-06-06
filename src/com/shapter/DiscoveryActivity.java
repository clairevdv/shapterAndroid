package com.shapter;

import java.util.List;

import testBDD.*;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

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
        
        Cours c1 = new Cours(1, "3D");
        Cours c2 = new Cours(2, "Image");
        Cours c3 = new Cours(3, "Projet libre");
        
        cDAO.open();
        
        cDAO.insertCours(c1);
        cDAO.insertCours(c2);
        cDAO.insertCours(c3);
                
        List listeCours = cDAO.showTable();
        cDAO.close();
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
