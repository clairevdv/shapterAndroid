package login;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shapter.LogedActivity;
import com.shapter.R;

/**
 * Activity de login
 */
public class LoginActivity extends Activity {
	@SuppressLint("NewApi")
	private UserDAO uDAO;

	// Le remplissage par défaut de l'username et la longueur minimale de mot de passe souhaitée
	public static final String EXTRA_USERNAME = "com.example.android.authenticatordemo.extra.USERNAME";
	private int password_min_length = 4;

	// Valeurs entrees par l'utilisateur
	private String mUserName;
	private String mPassword;

	// UI references.
	private EditText mUserNameView;
	private EditText mPasswordView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setupActionBar();

		// On cree le formulaire
		mUserName = getIntent().getStringExtra(EXTRA_USERNAME);
		mUserNameView = (EditText) findViewById(R.id.username);
		mUserNameView.setText(mUserName);

		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordView
		.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int id,
					KeyEvent keyEvent) {
				if (id == R.id.login || id == EditorInfo.IME_NULL) {
					attemptLogin();
					return true;
				}
				return false;
			}
		});

		findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
					}
				});
	}

	public void oubli(View view) {
		Intent intentOubli = new Intent(this, OubliMdpActivity.class);
		startActivity(intentOubli);
	}
	
	public void inscription(View view) {
		Intent intentInscription = new Intent(this, InscriptionActivity.class);
		startActivity(intentInscription);
	}

	/**
	 * Tentative de connexion et vérification des champs utilisateur
	 */
	public void attemptLogin() {
		// Au cas ou il y avait des erreurs pour la tentative de login precedente, on les enleve
		mUserNameView.setError(null);
		mPasswordView.setError(null);

		// On enregistre les valeurs entrees par l'utilisateur
		mUserName = mUserNameView.getText().toString();
		mPassword = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// On vérifie que le mot de passe est suffisament long
		if (TextUtils.isEmpty(mPassword)) {
			mPasswordView.setError(getString(R.string.error_field_required));
			focusView = mPasswordView;
			cancel = true;
		} else if (mPassword.length() < password_min_length) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		}

		// On vérifie que le champ du nom d'utilisateur est rempli
		if (TextUtils.isEmpty(mUserName)) {
			mUserNameView.setError(getString(R.string.error_field_required));
			focusView = mUserNameView;
			cancel = true;
		}

		if (cancel) {
			// L'adresse n'est pas valide, on affiche une erreur à côté du champ
			focusView.requestFocus();
		} else {
			// On se connecte a la BDD et on regarde si l'utilisateur existe (en initialisant la base de donnees)
			// On initialise la base de données
			uDAO = new UserDAO(this);
			uDAO.open();
			uDAO.initialiserUsers();
			int result = uDAO.login(mUserName,mPassword);
			uDAO.close();
			if (result == 0) {
				Intent intentLoged = new Intent(this, LogedActivity.class);
				intentLoged.putExtra("username", mUserName);
				startActivity(intentLoged);
			}
			if (result == 1) {
				Toast toast = Toast.makeText(getApplicationContext(), "Nom d'utilisateur erroné. Etes-vous enregistré ?", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
			if (result == 2) {
				Toast toast = Toast.makeText(getApplicationContext(), "Mot de passe erroné", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
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
