package com.shapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ListActivity;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

public class DiscoveryActivity extends ListActivity {
	@SuppressLint("NewApi")
	String result = null;
	InputStream is = null;
	StringBuilder sb = null;
	JSONArray jArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_discovery);
		// Show the Up button in the action bar.
		setupActionBar();

		new Connection().execute();
	}

	private class Connection extends AsyncTask {
		@Override
		protected Object doInBackground(Object... arg0) {
			connect();
			return null;
		}
	}

	private String connect() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		System.out.println("Je tente de me connecter");
		//commandes httpClient : interroger la branche locale
		try{
			HttpClient httpclient = new DefaultHttpClient();
			//HttpConnectionParams.setConnectionTimeout(httpclient.getParams(), 15000);
			//HttpPost httppost = new HttpPost("http://10.0.2.2:8080/connectBDD.php");
			HttpPost httppost = new HttpPost("http://137.194.22.82:8080/connectBDD.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		}
		catch(Exception e){
			Log.e("log_tag","Error in http connection"+e.toString());
		}

		System.out.println("Ok pour le http");
		//conversion de la réponse en chaine de caractère
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			sb  = new StringBuilder();
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
			Log.e("log_tag","Error converting result"+e.toString());
		}
		System.out.println("Reponse convertie");
		//paring data 
		int ct_id; 
		String ct_name; 
		try{ 
			jArray = new JSONArray(result); 
			JSONObject json_data=null; 
			for(int i=0;i<jArray.length();i++){ 
				json_data = jArray.getJSONObject(i); 
				ct_id=json_data.getInt("id"); 
				ct_name=json_data.getString("titreUE"); 
			} 
		} 
		catch(JSONException e1){ 
			Toast.makeText(getBaseContext(), "Pas d'UE trouvee" ,Toast.LENGTH_LONG).show(); 
		} 
		catch (ParseException e1) { 
			e1.printStackTrace(); 
		} 
		return result;
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
