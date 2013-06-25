package ecoles;

import com.shapter.DAOBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

public class SchoolDAO extends DAOBase{

	public SchoolDAO(Context pContext) {
		super(pContext);
	}

	private static final String TABLE_NAME = "app_school";
	private static final String KEY = "_id";
	private static final String NOM_ECOLE = "name";
	private static final String PAYS_ECOLE = "country";
	private static final String DESCRIPTION_ECOLE = "description";
	private static final String EMPTY_SPINNER_STRING_ECOLES = " Toutes les écoles";
	private static final String EMPTY_SPINNER_STRING_PAYS = " Tous les pays";
	
	public void insertEcole(School ecole){
		// On verifie que la ligne n'y est pas deja
		Cursor c = mDb.rawQuery("select * from " + TABLE_NAME + " where " + NOM_ECOLE + " like ?" + " and " + PAYS_ECOLE + " like ?", new String[] {ecole.getName(), ecole.getCountry()});

		if (c.getCount() == 0) {
			//Création d'un ContentValues
			ContentValues values = new ContentValues();
			// On ajoute dedans les valeurs de l'School
			values.put(NOM_ECOLE, ecole.getName());
			values.put(PAYS_ECOLE, ecole.getCountry());
			values.put(DESCRIPTION_ECOLE, ecole.getDescriptionEcole());
			//on insère l'objet dans la BDD via le ContentValues
			mDb.insert(TABLE_NAME, null, values);
		}
		c.close();
	}

	public void initialiserTest() {
		School e1 = new School("Telecom Paristech", "France");
		School e2 = new School("Czech Technical University in Prague", "République tchèque");
		School e3 = new School("Imperial College London", "United Kingdom", "Rendez-vous sur le site : [à renseigner]");

		insertEcole(e1);
		insertEcole(e2);
		insertEcole(e3);
	}

	public void updateEcole(School ecole){
		ContentValues values = new ContentValues();
		values.put(NOM_ECOLE, ecole.getName());
		values.put(PAYS_ECOLE, ecole.getCountry());
		values.put(DESCRIPTION_ECOLE, ecole.getDescriptionEcole());
		mDb.update(TABLE_NAME, values, KEY + " = ?", new String[] {String.valueOf(ecole.getId())});
	}

	public void deleteEcoleIndex(int id){
		//Suppression d'une School de la BDD grâce à son index
		mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
	}

	public void deleteEcoleNom(String nom) {
		mDb.delete(TABLE_NAME, NOM_ECOLE + " = ?", new String[] {nom});
	}
	
	public Cursor listeEcoles() {
		Cursor c;
		c = mDb.rawQuery("select distinct rowid _id,* from " + TABLE_NAME + " UNION select '' as 'rowid', '-1' as '_id', '" + EMPTY_SPINNER_STRING_ECOLES + "' AS 'name', 'None' AS 'country', '' AS 'description'" + " order by " + NOM_ECOLE, null);
		if(c.getCount() == 0) {
			return null;
		}
		return c;  
	}
	
	public Cursor listePays() {
		Cursor c;
		c = mDb.rawQuery("select distinct rowid _id,* from " + TABLE_NAME + " UNION select '' as 'rowid', '' as '_id', '' AS 'name', '" + EMPTY_SPINNER_STRING_PAYS + "' AS 'country', '' AS 'description'" + " order by " + PAYS_ECOLE, null);
		if(c.getCount() == 0) {
			return null;
		}
		return c;  
	}

	public Cursor tableAffichageEcole(String pays) {
		System.out.println("Affichons la database");
		Cursor c;
		if ((pays == null)||(pays.trim().equals(""))||(pays.trim().equals(EMPTY_SPINNER_STRING_PAYS.trim())))
			c = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " order by " + PAYS_ECOLE, null);
		else
			c = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " where " + PAYS_ECOLE + " like ? "+ " order by " + NOM_ECOLE, new String[] {String.valueOf(pays)});
		if(c.getCount() == 0) {
			System.out.println("Table Vide pour le pays sélectionné");
			return null;
		}
		return c;  
	}

	// En cas de recherche, on filtre les ecoles affichees
	public Cursor ecoleByNom(String pays, String nomEcole) throws SQLException {
		Log.w("SchoolDAO", nomEcole);
		Cursor mCursor = null;
		// Si la recherche est nulle, on affiche toutes les UE sinon on active le filtrage
		if (nomEcole == null  ||  nomEcole.length () == 0)  {
			if ((pays == null)||(pays.trim().equals(""))||(pays == EMPTY_SPINNER_STRING_PAYS))
				mCursor = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " order by " + PAYS_ECOLE, null);
			else
				mCursor = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " where " + PAYS_ECOLE + " like ?" + " order by " + NOM_ECOLE, new String[] {String.valueOf(pays)});
		}

		else {
			if ((pays == null)||(pays.trim().equals(""))|| (pays == EMPTY_SPINNER_STRING_PAYS))
				mCursor = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " where " + NOM_ECOLE + " like '%" + nomEcole + "%'" + " order by " + PAYS_ECOLE, null);
			else
				mCursor = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " where " + NOM_ECOLE + " like '%" + nomEcole + "%'" + " and " + PAYS_ECOLE + " like ? " + " order by " + NOM_ECOLE, new String[] {String.valueOf(pays)});
		}
		if (mCursor != null) {
			mCursor.moveToFirst();
			System.out.println("Il y a un résultat correspondant au filtrage demandé");
		}
		return mCursor;
	}
}
