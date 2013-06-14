package com.shapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ueBDD.CoursDAO;
import ueBDD.DatabaseHandler;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DiscoveryActivity extends ListActivity {
	@SuppressLint("NewApi")

	private CoursDAO cDAO;
	private SimpleCursorAdapter dataAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_discovery);
		// Show the Up button in the action bar.
		setupActionBar();

		System.out.println("Je me connecte");
		new Connection().execute();
		System.out.println("ok !");

/*		//TODO : modifier pour le faire proprement avec AsyncTask http://stackoverflow.com/questions/6343166/android-os-networkonmainthreadexception
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		TextView tv = (TextView) findViewById(R.id.titre_activity_discovery);
		String result = null;
		InputStream is = null;
		JSONObject json_data=null;
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		ArrayList<String> donnees = new ArrayList<String>();

		//commandes httpClient : interroger la branche locale
		try{
			HttpClient httpclient = new DefaultHttpClient();
			HttpConnectionParams.setConnectionTimeout(httpclient.getParams(), 15000);
			//HttpPost httppost = new HttpPost("http://10.0.2.2:8080/connectBDD.php");
			HttpPost httppost = new HttpPost("http://137.194.22.82:8080/connectBDD.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		}
		catch(Exception e){
			Log.i("taghttppost",""+e.toString());
			Toast.makeText(getBaseContext(),e.toString() ,Toast.LENGTH_LONG).show();
		}

		System.out.println("Ok pour le http");
		//conversion de la r�ponse en chaine de caract�re
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			StringBuilder sb  = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
		}
		catch(Exception e)
		{
			Log.i("tagconvertstr",""+e.toString());
		}
		System.out.println("Reponse convertie");
		//recuperation des donnees json
		try{
			JSONArray jArray = new JSONArray(result);
			for(int i=0;i<jArray.length();i++)
			{
				json_data = jArray.getJSONObject(i);
				Log.i("log_tag","id: "+json_data.getInt("id"));
				donnees.add(json_data.getString("title"));
				//r.add(json_data.getString("categorie"));
			}
			setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, donnees));
		}
		catch(JSONException e){
			Log.i("tagjsonexp",""+e.toString());
		} catch (ParseException e) {
			Log.i("tagjsonpars",""+e.toString());
		}  */
	}
	
	private class Connection extends AsyncTask {
		@Override
		protected Object doInBackground(Object... arg0) {
			connect();
			return null;
		}
	}

	private void connect() {
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet("http://www.google.com");
			HttpResponse response = client.execute(request);
		} catch (ClientProtocolException e) {
			Log.d("HTTPCLIENT", e.getLocalizedMessage());
		} catch (IOException e) {
			Log.d("HTTPCLIENT", e.getLocalizedMessage());
		}
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
