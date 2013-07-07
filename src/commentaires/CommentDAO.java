package commentaires;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.shapter.DAOBase;

public class CommentDAO extends DAOBase{
	public CommentDAO(Context pContext) {
		super(pContext);
	}

	private static final String TABLE_NAME = "app_comment";
	private static final String TABLE_NAME_USER = "auth_user";
	private static final String TEXTE = "comment";
	private static final String COURS = "course_id";
	private static final String STUDENT = "student_id";

	public int insertComment(Comment comment){
		// On verifie que l'eleve n'a pas deja commente
		Cursor c = mDb.rawQuery("select * from " + TABLE_NAME + " where " + COURS + " like ? and " + STUDENT + " like ?", new String[] {String.valueOf(comment.getCourseId()), String.valueOf(comment.getStudentId())});

		if (c.getCount() == 0) {
			//Cr�ation d'un ContentValues
			ContentValues values = new ContentValues();
			// On ajoute dedans les valeurs de l'Comment
			values.put(TEXTE, comment.getComment());
			values.put(COURS, comment.getCourseId());
			values.put(STUDENT, comment.getStudentId());
			//on ins�re l'objet dans la BDD via le ContentValues
			mDb.insert(TABLE_NAME, null, values);
			c.close();
			return 0;
		}
		else {
			System.out.println("L'eleve a deja comment� ce cours !");
			c.close();
			return 1;
		}

	}

	public void initialiserTest() {
		Comment e1 = new Comment("Mes 3 ans � T�l�com ParisTech ont �t� les plus b�n�fiques, professionnellement parlant. De plus, l'activit� associative constante qui y r�gne m'a � la fois amus� et servi ; amus� par les activit�s propos�es et par l'organisation m�me d'activit�s, servi car il est toujours utile de savoir g�rer une �quipe de 15 personnes, organiser un �v�nement pour 300 personnes, am�liorer ses relations entreprises... Enfin, les activit�s extra-scolaires (permises entre autres par une administration conciliante) m'ont permis de m'�panouir dans ce que j'aime faire. Bref, je recommande chaudement mon �cole !", 1, 1);
		Comment e2 = new Comment("Coulos !", 1, 2);
		Comment e3 = new Comment("C'est une UE o� l'on en apprend sur beaucoup de domaines mais pas forc�ment en profondeur. Bref, c'est le but d'une introduction. Ce qui en fait une tr�s bonne UE de base pour ceux qui, comme moi, ne prenne pas pour parcours principal le parcours image : cela permet de se faire une id�e g�n�rale de ce qu'est le traitement d'images, les diff�rents aspects et applications dans lesquels on peut travailler.", 2, 3);
		Comment e4 = new Comment("Une UE � prendre si on est int�ress� (m�me un tout petit peu) par le domaine de l'image. Les cours sont la plupart du temps bien ficel�s avec plusieurs intervenants et de nombreux th�mes abord�s (rendu, mod�lisation, traitement d'image 3D, applications).", 2, 2);
		Comment e5 = new Comment("Ce cours n'�tait pas exactement ce � quoi je m'attendais. Je m'attendais � un cours sur la th�orie et non pas uniquement des travaux pratiques", 2, 1);
		Comment e6 = new Comment("Excellent prof, tr�s ouvert aux questions, r�ponds vite et bien au mail, pr�t � aider dans vos divers projets", 3, 1);
		Comment e7 = new Comment("Le rayon de soleil de ma 2�me ann�e ! Un cours que j'avais pris plus par curiosit� que par passion, et je fus combl�. D'excellents profs, la cr�me de la cr�me dans le domaine", 3, 2);

		insertComment(e1);
		insertComment(e2);
		insertComment(e3);
		insertComment(e4);
		insertComment(e5);
		insertComment(e6);
		insertComment(e7);

	}

	public void updateComment(String texte, int cours, int student){
		ContentValues values = new ContentValues();
		values.put(TEXTE,texte);
		values.put(COURS, cours);
		mDb.update(TABLE_NAME, values,STUDENT + " = ?", new String[] {String.valueOf(student)});
	}

	public void deleteComment(int texte){
		mDb.delete(TABLE_NAME,TEXTE + " = ?", new String[] {String.valueOf(texte)});
	}

	public Cursor tableAffichageComment (int course_id) {
		Cursor c = mDb.rawQuery("select * from " + TABLE_NAME + " c INNER JOIN " + TABLE_NAME_USER + " u ON c.student_id=u.student_id where c." + COURS + " like ?",new String[]{String.valueOf(course_id)});
		
		if(c.getCount() == 0) {
			System.out.println("Aucun commentaire n'a �t� �crit pour le cours choisi");
			return null;
		}
		return c;  
	}
}