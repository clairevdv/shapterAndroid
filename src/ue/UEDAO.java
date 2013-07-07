package ue;


import com.shapter.DAOBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

public class UEDAO extends DAOBase{

	public UEDAO(Context pContext) {
		super(pContext);
	}

	private static final String TABLE_NAME = "app_course";
	private static final String KEY = "_id";
	private static final String ECOLE = "school_id";
	private static final String CODE = "code";
	private static final String TITRE_UE = "title";
	private static final String DESCRIPTION_UE = "description";

	public void insertCours(UE UE){
		// On verifie que la ligne n'y est pas deja
		Cursor c = mDb.rawQuery("select * from " + TABLE_NAME + " where " + TITRE_UE + " like ? " + " and " + ECOLE + " like ? ", new String[] {String.valueOf(UE.getTitle()), String.valueOf(UE.getSchool())});

		if (c.getCount() == 0) {
			//Création d'un ContentValues
			ContentValues values = new ContentValues();
			// On ajoute dedans les valeurs de l'UE
			values.put(ECOLE, UE.getSchool());
			values.put(CODE, UE.getCode());
			values.put(TITRE_UE, UE.getTitle());
			values.put(DESCRIPTION_UE, UE.getDescriptionUE());
			//on insère l'objet dans la BDD via le ContentValues
			mDb.insert(TABLE_NAME, null, values);
		}
		c.close();
	}

	public void initialiserTest() {
		UE c1 = new UE(1, "INFSI350", "3D");
		UE c2 = new UE(1, "SI350", "Image");
		UE c3 = new UE(2, "IMG200", "Image avancé");
		UE c4 = new UE(1, "PJL380", "Projet libre");
		UE c5 = new UE(2, "IHM315", "Interfaces homme-machine", "Cette unité d'enseignement présente les méthodes et techniques permettant la conception et la réalisation d'interfaces homme-machine conviviales et performantes. L'enseignement comprend trois parties respectivement consacrées: aux facteurs humains, aux aspects logiciels et au contrôle interactif du contenu visuel");

		insertCours(c1);
		insertCours(c2);
		insertCours(c3);
		insertCours(c4);
		insertCours(c5);
	}

	public void updateCours(UE UE){
		ContentValues values = new ContentValues();
		values.put(ECOLE, UE.getSchool());
		values.put(CODE, UE.getCode());
		values.put(TITRE_UE, UE.getTitle());
		values.put(DESCRIPTION_UE, UE.getDescriptionUE());
		mDb.update(TABLE_NAME, values, KEY + " = ?", new String[] {String.valueOf(UE.getId())});
	}

	public void deleteCoursIndex(int id){
		//Suppression d'une UE de la BDD grâce à son index
		mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
	}

	public void deleteCoursCode(String code) {
		mDb.delete(TABLE_NAME, CODE + " = ?", new String[] {code});
	}

	public Cursor tableAffichageUE(int ecole) {
		System.out.println("Affichons la database");
		Cursor c;
		if (ecole == -1)
			c = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " order by " + TITRE_UE, null);
		else
			c = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " where " + ECOLE + " like ? "+ " order by " + CODE, new String[] {String.valueOf(ecole)});

		if(c.getCount() == 0) {
			return null;
		}
		return c;  
	}

	// En cas de recherche, on filtre les UE affichees
	public Cursor ueByNom(int ecole, String nomUE) throws SQLException {
		Log.w("CoursDAO", nomUE);
		Cursor mCursor = null;
		// Si la recherche est nulle, on affiche toutes les UE sinon on active le filtrage
		if (nomUE == null  ||  nomUE.length () == 0)  {
			if (ecole == -1)
				mCursor = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " order by " + TITRE_UE, null);
			else
				mCursor = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " where " + ECOLE + " like ?" + " order by " + CODE, new String[] {String.valueOf(ecole)});
		}

		else {
			if (ecole == -1)
				mCursor = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " where " + TITRE_UE + " like '%" + nomUE + "%'" + " or " + CODE + " like '%" + nomUE + "%'" + " order by " + TITRE_UE, null);
			else
				mCursor = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " where " + TITRE_UE + " like '%" + nomUE + "%'" + " or " + CODE + " like '%" + nomUE + "%'" + " and " + ECOLE + " like ? " + " order by " + CODE, new String[] {String.valueOf(ecole)});
		}
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public Cursor ueById(String course_id) throws SQLException {
		Cursor mCursor = null;
		mCursor = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " where " + KEY + " like ?", new String[] {course_id});

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
}