package Commentaires;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.shapter.DAOBase;

public class CourseCommentDAO extends DAOBase{
	public CourseCommentDAO(Context pContext) {
		super(pContext);
	}

	private static final String TABLE_NAME = "app_coursecomment";
	private static final String TEXTE = "usertext_ptr_id";
	private static final String COURS = "course_id";
	private static final String STUDY = "study_id";

	public int insertCourseComment(int texte, int cours, int study){
		// On verifie que l'eleve n'a pas deja commente
		Cursor c = mDb.rawQuery("select * from " + TABLE_NAME + " where " + COURS + " like ? and " + STUDY + " like ?", new String[] {String.valueOf(cours), String.valueOf(STUDY)});

		if (c.getCount() == 0) {
			//Création d'un ContentValues
			ContentValues values = new ContentValues();
			// On ajoute dedans les valeurs de l'Comment
			values.put(TEXTE, texte);
			values.put(COURS, cours);
			values.put(STUDY, study);
			//on insère l'objet dans la BDD via le ContentValues
			mDb.insert(TABLE_NAME, null, values);
			c.close();
			return 0;
		}
		else {
			System.out.println("L'eleve a deja commenté ce cours !");
			c.close();
			return 1;
		}

	}

	/* TODO : initialiser la base de donnees 
	 * public void initialiserTest() {		insertCourseComment(2,,);
		insertCourseComment(3,,);
		insertCourseComment(4,,);
		insertCourseComment(5,,);
		insertCourseComment(6,,);
		insertCourseComment(7,,);;
	}*/

	public void updateCourseComment(int texte, int cours, int study){
		ContentValues values = new ContentValues();
		values.put(TEXTE,texte);
		values.put(COURS, cours);
		mDb.update(TABLE_NAME, values,STUDY + " = ?", new String[] {String.valueOf(study)});
	}

	public void deleteComment(int texte){
		mDb.delete(TABLE_NAME,TEXTE + " = ?", new String[] {String.valueOf(texte)});
	}

	public Cursor commentCours (int course_id) {
		System.out.println("Affichons la database");
		Cursor c = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME +" where " + COURS + " like ?? order by " +STUDY,new String[]{String.valueOf(course_id)});

		if(c.getCount() == 0) {
			System.out.println("Aucun commentaire n'a été écrit pour le cours choisi");
			return null;
		}
		return c;  
	}
}
