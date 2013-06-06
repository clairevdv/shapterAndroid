package testBDD;

import java.util.ArrayList;
import java.util.List;

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
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(PARCOURS_UE, UE.getParcours());
		values.put(TITRE_UE, UE.getTitreUE());
		//on insère l'objet dans la BDD via le ContentValues
		mDb.insert(TABLE_NAME, null, values);
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

	public List showTable() {
		int test = 0;
		Cursor c = mDb.rawQuery("select * from " + TABLE_NAME, null);
		if(c.getCount() == 0) {
			System.out.println("Table Vide");
			return null;
		}

		List listeCours = new ArrayList();
		String s = new String("");

		c.moveToFirst();
		while(c.moveToNext()){
			Cours cours = new Cours();
			//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
			cours.setId(c.getInt(0));
			cours.setParcours(c.getInt(1));
			cours.setTitreUE(c.getString(2));
			listeCours.add(cours);
			s = String.valueOf(cours.getId()) + " | " + String.valueOf(cours.getParcours()) + " | " + cours.getTitreUE() + "\n";
			System.out.println(s);
			c.moveToNext();
		}
		
		c.close();
		return listeCours;  
	}
}