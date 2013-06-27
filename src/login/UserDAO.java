package login;

import java.io.ByteArrayOutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;

import com.shapter.DAOBase;

public class UserDAO extends DAOBase {

	public UserDAO(Context pContext) {
		super(pContext);
	}

	private static final String TABLE_NAME = "auth_user";
	private static final String KEY = "_id";
	private static final String UTILISATEUR = "username";
	private static final String PRENOM = "first_name";
	private static final String NOM = "last_name";
	private static final String MAIL = "email";
	private static final String PHOTO = "photo";
	private static final String MDP = "password";

	public int insertUser(User newUser){
		// On verifie que la ligne n'y est pas deja
		Cursor user = mDb.rawQuery("select * from " + TABLE_NAME + " where " + PRENOM + " like ? and " + NOM + " like ?", new String[] {newUser.getFirstName(), newUser.getLastName()});
		Cursor username = mDb.rawQuery("select * from " + TABLE_NAME + " where " + UTILISATEUR + " like ?", new String[] {newUser.getUserName()});
		if (user.getCount() != 0) {
			// On verifie que l'utilisateur n'est pas dans la base
			user.close();
			username.close();
			return 1;
		}
		else if (username.getCount() != 0) {
			// On verifie que l'username n'est pas pris
			user.close();
			username.close();
			return 2;
		}
		else {
			//Création d'un ContentValues pour ajouter l'utilisateur
			ContentValues values = new ContentValues();
			// On ajoute dedans les valeurs de l'School
			values.put(UTILISATEUR, newUser.getUserName());
			values.put(PRENOM, newUser.getFirstName());
			values.put(NOM, newUser.getLastName());
			values.put(MAIL, newUser.getEmail());
			values.put(MDP, newUser.getPassword());
			//on insère l'objet dans la BDD via le ContentValues
			mDb.insert(TABLE_NAME, null, values);
			return 0;
		}
	}

	public void initialiserUsers() {
		User u1 = new User("claire.vdv", "Claire", "VdV", "vandevoo@enst.fr", "shapter");
		User u2 = new User("sam.tardieu", "Samuel", "Tardieu", "tardieu@telecom-paristech.fr", "1234");
		User u3 = new User("tibere", "Adrien", "Tibere", "adrien.tibere-inglesse@telecom-paristech.fr", "test");

		insertUser(u1);
		insertUser(u2);
		insertUser(u3);
	}

	public void updateUser(User user){
		ContentValues values = new ContentValues();
		values.put(UTILISATEUR, user.getUserName());
		values.put(PRENOM, user.getFirstName());
		values.put(NOM, user.getLastName());
		values.put(MAIL, user.getEmail());
		values.put(MDP, user.getPassword());
		
		// On converti puis on ajoute la photo
		Bitmap photo = user.getPhoto();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		photo.compress(Bitmap.CompressFormat.PNG, 100, out);
		byte[] buffer=out.toByteArray();
		values.put(PHOTO, buffer);
		
		mDb.update(TABLE_NAME, values, KEY + " = ?", new String[] {String.valueOf(user.getId())});
	}
	
	public void addPhoto(Bitmap photo, String username){		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		photo.compress(Bitmap.CompressFormat.PNG, 100, out);
		byte[] buffer=out.toByteArray();
		ContentValues values = new ContentValues();
		values.put(PHOTO, buffer);
		mDb.update(TABLE_NAME, values, UTILISATEUR + " = ?", new String[] {username});
	}

	public int login(String username, String password) {
		Cursor login = mDb.rawQuery("select * from " + TABLE_NAME + " where " + UTILISATEUR + " like ?", new String[] {username});
		// Si l'utilisateur n'est pas enregistre
		if (login.getCount() == 0) {
			login.close();
			return 1;
		}
		login.moveToFirst();
		// Si le mot de passe est le bon
		if (password.equals(login.getString(6))) {
			login.close();
			return 0;
		}
		login.close();
		// Si l'utilisateur existe mais que le mot de passe est errone
		return 2;
	}

	public Cursor userByUsername(String username){
		Cursor user = mDb.rawQuery("select * from " + TABLE_NAME + " where " + UTILISATEUR + " like ?", new String[] {username});
		if (user.getCount() == 0) {
			user.close();
			return null;
		}
		user.moveToFirst();
		return user;
	}
	
	public boolean checkMdp(String username, String mdp){
		Cursor user = mDb.rawQuery("select * from " + TABLE_NAME + " where " + UTILISATEUR + " like ? and " + MDP + " like ?", new String[] {username, mdp});
		if (user.getCount() == 0) {
			user.close();
			return false;
		}
		return true;
	}
}
