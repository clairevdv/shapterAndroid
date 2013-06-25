package ueBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class UserDAO extends DAOBase {

	public UserDAO(Context pContext) {
		super(pContext);
	}

	private static final String TABLE_NAME = "auth_user";
	private static final String KEY = "_id";

	public int insertUser(User newUser){
		// On verifie que la ligne n'y est pas deja
		Cursor user = mDb.rawQuery("select * from " + TABLE_NAME + " where first_name like ? and last_name like ?", new String[] {newUser.getFirstName(), newUser.getLastName()});
		Cursor username = mDb.rawQuery("select * from " + TABLE_NAME + " where username like ?", new String[] {newUser.getUserName()});
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
			values.put("username", newUser.getUserName());
			values.put("first_name", newUser.getFirstName());
			values.put("last_name", newUser.getLastName());
			values.put("email", newUser.getEmail());
			values.put("password", newUser.getPassword());
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
		values.put("username", user.getUserName());
		values.put("first_name", user.getFirstName());
		values.put("last_name", user.getLastName());
		values.put("email", user.getEmail());
		values.put("password", user.getPassword());
		mDb.update(TABLE_NAME, values, KEY + " = ?", new String[] {String.valueOf(user.getId())});
	}

	public int login(String username, String password) {
		Cursor login = mDb.rawQuery("select * from " + TABLE_NAME + " where username like ?", new String[] {username});
		// Si l'utilisateur n'est pas enregistre
		if (login.getCount() == 0) {
			login.close();
			return 1;
		}
		login.moveToFirst();
		// Si le mot de passe est le bon
		if (password.equals(login.getString(5))) {
			login.close();
			return 0;
		}
		login.close();
		// Si l'utilisateur existe mais que le mot de passe est errone
		return 2;
	}
}
