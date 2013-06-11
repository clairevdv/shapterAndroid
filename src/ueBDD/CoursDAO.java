package ueBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

public class CoursDAO extends DAOBase{

	public CoursDAO(Context pContext) {
		super(pContext);
	}

	private static final String TABLE_NAME = "UE";
	private static final String KEY = "_id";
	private static final String PARCOURS_UE = "parcours";
	private static final String TITRE_UE = "titreUE";
	private static final String DESCRIPTION_UE = "description";

	public void insertCours(Cours UE){
		// On verifie que la ligne n'y est pas deja
		Cursor c = mDb.rawQuery("select * from " + TABLE_NAME + " where " + TITRE_UE + " like ?", new String[] {String.valueOf(UE.getTitreUE())});

		if (c.getCount() == 0) {
			//Création d'un ContentValues
			ContentValues values = new ContentValues();
			// On ajoute dedans les valeurs de l'UE
			values.put(PARCOURS_UE, UE.getParcours());
			values.put(TITRE_UE, UE.getTitreUE());
			values.put(DESCRIPTION_UE, UE.getDescriptionUE());
			//on insère l'objet dans la BDD via le ContentValues
			mDb.insert(TABLE_NAME, null, values);
		}
		c.close();
	}

	public void initialiserTest() {
		Cours c1 = new Cours(1, "3D");
		Cours c2 = new Cours(2, "Image");
		Cours c3 = new Cours(2, "Image avancé");
		Cours c4 = new Cours(3, "Projet libre");
		Cours c5 = new Cours(1, "Interfaces homme-machine", "Cette unité d'enseignement présente les méthodes et techniques permettant la conception et la réalisation d'interfaces homme-machine conviviales et performantes. L'enseignement comprend trois parties respectivement consacrées: aux facteurs humains, aux aspects logiciels et au contrôle interactif du contenu visuel");

		insertCours(c1);
		insertCours(c2);
		insertCours(c3);
		insertCours(c4);
		insertCours(c5);
	}

	public void updateCours(Cours UE){
		ContentValues values = new ContentValues();
		values.put(PARCOURS_UE, UE.getParcours());
		values.put(TITRE_UE, UE.getTitreUE());
		values.put(DESCRIPTION_UE, UE.getDescriptionUE());
		mDb.update(TABLE_NAME, values, KEY + " = ?", new String[] {String.valueOf(UE.getId())});
	}

	public void deleteCoursIndex(int id){
		//Suppression d'une UE de la BDD grâce à son index
		mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
	}

	public Cursor recupererTable() {
		System.out.println("Affichons la database");
		Cursor c = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " order by " + PARCOURS_UE, null);
		if(c.getCount() == 0) {
			System.out.println("Table Vide");
			return null;
		}
		return c;  
	}

	// En cas de recherche, on filtre les UE affichees
	public Cursor ueByNom(String nomUE) throws SQLException {
		Log.w("CoursDAO", nomUE);
		Cursor mCursor = null;
		// Si la recherche est nulle
		if (nomUE == null  ||  nomUE.length () == 0)  {
			mCursor = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME, null);
		}
		// sinon
		else {
			mCursor = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " where " + TITRE_UE + " like '%" + nomUE + "%'", null);
			System.out.println("J'ai enregistré la requête");
		}
		if (mCursor != null) {
			mCursor.moveToFirst();
			System.out.println("Et j'ai un résultat");
		}
		return mCursor;
	}
}