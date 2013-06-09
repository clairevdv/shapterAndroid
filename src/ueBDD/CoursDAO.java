package ueBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class CoursDAO extends DAOBase{

	public CoursDAO(Context pContext) {
		super(pContext);
	}

	private static final String TABLE_NAME = "UE";
	private static final String KEY = "id";
	private static final String PARCOURS_UE = "parcours";
	private static final String TITRE_UE = "titreUE";
	private static final String DESCRIPTION_UE = "description";

	public void insertCours(Cours UE){
		// On verifie que la ligne n'y est pas deja
		Cursor c = mDb.rawQuery("select * from " + TABLE_NAME + " where titreUE like ?", new String[] {String.valueOf(UE.getTitreUE())});

		if (c.getCount() == 0) {
			//Cr�ation d'un ContentValues
			ContentValues values = new ContentValues();
			// On ajoute dedans les valeurs de l'UE
			values.put(PARCOURS_UE, UE.getParcours());
			values.put(TITRE_UE, UE.getTitreUE());
			values.put(DESCRIPTION_UE, UE.getDescriptionUE());
			//on ins�re l'objet dans la BDD via le ContentValues
			mDb.insert(TABLE_NAME, null, values);
		}
		c.close();
	}

	public void initialiserTest() {
		Cours c1 = new Cours(1, "3D");
		Cours c2 = new Cours(2, "Image");
		Cours c3 = new Cours(2, "Image avanc�");
		Cours c4 = new Cours(3, "Projet libre");
		Cours c5 = new Cours(1, "Interfaces homme-machine", "Cette unit� d'enseignement pr�sente les m�thodes et techniques permettant la conception et la r�alisation d'interfaces homme-machine conviviales et performantes. L'enseignement comprend trois parties respectivement consacr�es: aux facteurs humains, aux aspects logiciels et au contr�le interactif du contenu visuel");

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
		//Suppression d'une UE de la BDD gr�ce � son index
		mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
	}
	
	public String rowidToDescription (long id) {
		Cursor c = mDb.rawQuery("select description from " + TABLE_NAME + " where rowid like ?", new String[] {String.valueOf(id)});
		if (c.getCount() == 0) {
			System.out.println("Pas d'UE correspondante a l'UE choisie");
			return null;
		}

		c.moveToFirst();
		String titreUE = c.getString(0);
		c.close();
		
		return titreUE;
	}

	public Cursor recupererTable() {
		Cursor c = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " order by " + PARCOURS_UE, null);
		if(c.getCount() == 0) {
			System.out.println("Table Vide");
			return null;
		}
		return c;  
	}
}