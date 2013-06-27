package login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shapter.R;

public class ProfilActivity extends Activity {
	private UserDAO uDAO;
	String _username;
	private TextView username;
	private TextView prenom;
	private TextView nom;
	private TextView email;
	private Bitmap photo;
	private ImageView photoProfil;
	private static final int SELECT_PHOTO = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profil);

		username = (TextView) findViewById(R.id.username);
		prenom = (TextView) findViewById(R.id.prenom);
		nom = (TextView) findViewById(R.id.nom);
		email = (TextView) findViewById(R.id.email);
		photoProfil = (ImageView) findViewById(R.id.photoProfil);

		Button modifierProfil = (Button) findViewById(R.id.modifierProfil);
		View.OnClickListener listener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/*Intent intent = new Intent(ProfilActivity.this, PreferencesActivity.class);
				startActivity(intent);*/
				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
				photoPickerIntent.setType("image/*");
				photoPickerIntent.putExtra("crop", "true");
				photoPickerIntent.putExtra("outputX", 180);
				photoPickerIntent.putExtra("outputY", 180);
				photoPickerIntent.putExtra("aspectX", 1);
				photoPickerIntent.putExtra("aspectY", 1);
				photoPickerIntent.putExtra("scale", true);
				photoPickerIntent.putExtra("return-data", true);
				startActivityForResult(photoPickerIntent, SELECT_PHOTO);
			}
		};

		modifierProfil.setOnClickListener(listener);
		afficherProfil();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 

		switch(requestCode) { 
		case SELECT_PHOTO:
			if(resultCode == Activity.RESULT_OK){
				// On recupere l'image croppee pour la mettre en photo de profil
				Bundle extras = imageReturnedIntent.getExtras();
				photo = extras.getParcelable("data");
				photoProfil.setImageBitmap(photo);

				// Et on l'enregistrer dans la base de donnees
				uDAO = new UserDAO(this);
				uDAO.open();
				uDAO.addPhoto(photo, _username);
				uDAO.close();
			}
		}
	}

	private void afficherProfil() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ProfilActivity.this);
		_username = prefs.getString("username", "Default NickName");
		username.setText("Bienvenue " + _username + " !");

		uDAO = new UserDAO(this);
		uDAO.open();
		uDAO.initialiserUsers();
		Cursor infoUser = uDAO.userByUsername(_username);
		uDAO.close();

		if (infoUser == null) {
			Toast toast = Toast.makeText(getApplicationContext(), "Impossible de recuperer les donnees utilisateur", Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}

		prenom.setText(infoUser.getString(2));
		nom.setText(infoUser.getString(3));
		email.setText(infoUser.getString(4));
		byte[] image = infoUser.getBlob(5);
		if (image == null)
			photoProfil.setImageResource(R.drawable.ic_menu_eleves); 
		else {
			photo = BitmapFactory.decodeByteArray(image, 0, image.length);
			photoProfil.setImageBitmap(photo);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.action_bar, menu);
		return true;
	}

}