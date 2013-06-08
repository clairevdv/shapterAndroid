package testBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class CoursDAO extends DAOBase{

	public CoursDAO(Context pContext) {
		super(pContext);
	}

	private static final String TABLE_NAME = "UE";
	private static final String KEY = "id";
	public static final int NUM_COLUMN_KEY = 0;
	private static final String PARCOURS_UE = "parcours";
	public static final int NUM_COLUMN_PARCOURS = 1;
	private static final String TITRE_UE = "titreUE";
	public static final int NUM_COLUMN_TITRE = 2;

	public void insertCours(Cours UE){
		// On verifie que la ligne n'y est pas deja
		Cursor c = mDb.rawQuery("select * from " + TABLE_NAME + " where titreUE like ?", new String[] {String.valueOf(UE.getTitreUE())});

		if (c.getCount() == 0) {
			//Création d'un ContentValues (fonctionne comme une HashMap)
			ContentValues values = new ContentValues();
			//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
			values.put(PARCOURS_UE, UE.getParcours());
			values.put(TITRE_UE, UE.getTitreUE());
			//on insère l'objet dans la BDD via le ContentValues
			mDb.insert(TABLE_NAME, null, values);
		}
	}
	
	public void initialiserTest() {
		viderTable();
		
		Cours c1 = new Cours(1, "3D");
		Cours c2 = new Cours(2, "Image");
		Cours c3 = new Cours(3, "Projet libre");
		
		insertCours(c1);
		insertCours(c2);
		insertCours(c3);
	}

	public void updateCours(Cours UE){
		//La mise à jour d'une UE dans la BDD fonctionne plus ou moins comme une insertion
		//il faut simple préciser quelle UE on doit mettre à jour grâce à l'ID
		ContentValues values = new ContentValues();
		values.put(PARCOURS_UE, UE.getParcours());
		values.put(TITRE_UE, UE.getTitreUE());
		mDb.update(TABLE_NAME, values, KEY + " = ?", new String[] {String.valueOf(UE.getId())});
	}

	public void deleteCoursIndex(int id){
		//Suppression d'une UE de la BDD grâce à son index
		mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
	}
	
	public void viderTable() {
		//mDb.rawQuery("delete * from "+ TABLE_NAME, null);
	}

	public Cursor recupererTable() {
		Cursor c = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME, null);
		if(c.getCount() == 0) {
			System.out.println("Table Vide");
			return null;
		}
		
		System.out.println(c.getColumnCount() + "colonnes;" );
		System.out.println(c.getCount() + "lignes;" );

		return c;  
	}
}