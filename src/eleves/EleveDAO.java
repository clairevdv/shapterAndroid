package eleves;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.shapter.DAOBase;

public class EleveDAO extends DAOBase{
	
	public EleveDAO(Context pContext) {
		super(pContext);
	}

	private static final String TABLE_NAME = "app_student";
	private static final String KEY = "_id";
	private static final String YEAR = "student_year";
	private static final String ECOLE = "school";
	private static final String DESC = "personal_desc";

	public void insertEleve(Eleve eleve){
		//Création d'un ContentValues
		ContentValues values = new ContentValues();
		// On ajoute dedans les valeurs de l'eleve
		values.put(YEAR, eleve.getYear());
		values.put(ECOLE, eleve.getSchool());
		values.put(DESC, eleve.getDescription());
		//on insère l'objet dans la BDD via le ContentValues
		mDb.insert(TABLE_NAME, null, values);

	}

	public void initialiserTest() {
		Eleve e1 = new Eleve(1,1);
		Eleve e2 = new Eleve(2,1);
		Eleve e3 = new Eleve(3,1);
		Eleve e4 = new Eleve(4,1);
		Eleve e5 = new Eleve(5,1);
		Eleve e6 = new Eleve(6,1);
		Eleve e7 = new Eleve(7,1);

		insertEleve(e1);
		insertEleve(e2);
		insertEleve(e3);
		insertEleve(e4);
		insertEleve(e5);
		insertEleve(e6);
		insertEleve(e7);
	}

	public void updateEleve(Eleve eleve){
		ContentValues values = new ContentValues();
		values.put(YEAR, eleve.getYear());
		values.put(ECOLE, eleve.getSchool());
		values.put(DESC, eleve.getDescription());
		mDb.update(TABLE_NAME, values, KEY + " = ?", new String[] {String.valueOf(eleve.getId())});
	}

	public Cursor tableAffichageEleve(int ecole) {
		System.out.println("Affichons la database");
		Cursor c;
		if (ecole == -1)
			c = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " order by " + ECOLE, null);
		else
			c = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " where " + ECOLE + " like ? "+ " order by " + YEAR, new String[] {String.valueOf(ecole)});

		if(c.getCount() == 0) {
			return null;
		}
		return c;  
	}

	// TODO : En cas de recherche, on filtre les eleves affiches
	/*public Cursor eleveByNom(int ecole, String nomEleve) throws SQLException {
		Log.w("EleveDAO", nomEleve);
		Cursor mCursor = null;
		// Si la recherche est nulle, on affiche toutes les eleves sinon on active le filtrage
		if (nomEleve == null  ||  nomEleve.length () == 0)  {
			if (ecole == -1)
				mCursor = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " order by " + TITRE_Eleve, null);
			else
				mCursor = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME+ " where " + ECOLE + " like ?" + " order by " + CODE, new String[] {String.valueOf(ecole)});
		}

		else {
			if (ecole == -1)
				mCursor = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " where " + TITRE_Eleve + " like '%" + nomEleve + "%'" + " order by " + TITRE_Eleve, null);
			else
				mCursor = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " where " + TITRE_Eleve + " like '%" + nomEleve + "%'" + " and " + ECOLE + " like ? " + " order by " + CODE, new String[] {String.valueOf(ecole)});
		}
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}*/

}
