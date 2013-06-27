package Commentaires;

import com.shapter.DAOBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class CommentDAO extends DAOBase{
	public CommentDAO(Context pContext) {
		super(pContext);
	}

	private static final String TABLE_NAME = "app_usertext";
	private static final String KEY = "_id";
	private static final String TEXTE = "comment";
	private static final String ELEVE = "student_id";

	public void insertComment(Comment comment){
		//Cr�ation d'un ContentValues
		ContentValues values = new ContentValues();
		// On ajoute dedans les valeurs de l'Comment
		values.put(TEXTE, comment.getComment());
		values.put(ELEVE, comment.getStudentId());
		//on ins�re l'objet dans la BDD via le ContentValues
		mDb.insert(TABLE_NAME, null, values);
	}

	public void initialiserTest() {
		Comment e1 = new Comment("Mes 3 ans � T�l�com ParisTech ont �t� les plus b�n�fiques, professionnellement parlant. De plus, l'activit� associative constante qui y r�gne m'a � la fois amus� et servi ; amus� par les activit�s propos�es et par l'organisation m�me d'activit�s, servi car il est toujours utile de savoir g�rer une �quipe de 15 personnes, organiser un �v�nement pour 300 personnes, am�liorer ses relations entreprises... Enfin, les activit�s extra-scolaires (permises entre autres par une administration conciliante) m'ont permis de m'�panouir dans ce que j'aime faire. Bref, je recommande chaudement mon �cole !", 1);
		Comment e2 = new Comment("Coulos !", 2);
		Comment e3 = new Comment("C'est une UE o� l'on en apprend sur beaucoup de domaines mais pas forc�ment en profondeur. Bref, c'est le but d'une introduction. Ce qui en fait une tr�s bonne UE de base pour ceux qui, comme moi, ne prenne pas pour parcours principal le parcours image : cela permet de se faire une id�e g�n�rale de ce qu'est le traitement d'images, les diff�rents aspects et applications dans lesquels on peut travailler..", 3);
		Comment e4 = new Comment("Une UE � prendre si on est int�ress� (m�me un tout petit peu) par le domaine de l'image. Les cours sont la plupart du temps bien ficel�s avec plusieurs intervenants et de nombreux th�mes abord�s (rendu, mod�lisation, traitement d'image 3D, applications).", 1);
		Comment e5 = new Comment("Ce cours n'�tait pas exactement ce � quoi je m'attendais. Je m'attendais � un cours sur la th�orie et non pas uniquement des travaux pratiques", 4);
		Comment e6 = new Comment("Excellent prof, tr�s ouvert aux questions, r�ponds vite et bien au mail, pr�t � aider dans vos divers projets", 5);
		Comment e7 = new Comment("Le rayon de soleil de ma 2�me ann�e ! Un cours que j'avais pris plus par curiosit� que par passion, et je fus combl�. D'excellents profs, la cr�me de la cr�me dans le domaine", 6);

		insertComment(e1);
		insertComment(e2);
		insertComment(e3);
		insertComment(e4);
		insertComment(e5);
		insertComment(e6);
		insertComment(e7);
	}

	public void updateComment(Comment comment){
		ContentValues values = new ContentValues();
		values.put(TEXTE, comment.getComment());
		values.put(ELEVE, comment.getStudentId());
		mDb.update(TABLE_NAME, values, KEY + " = ?", new String[] {String.valueOf(comment.getId())});
	}

	public void deleteCommentIndex(int id){
		//Suppression d'une Comment de la BDD gr�ce � son index
		mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
	}

	public void deleteCommentEleve(int student_id) {
		mDb.delete(TABLE_NAME, ELEVE + " = ?", new String[] {String.valueOf(student_id)});
	}

	public Cursor tableAffichagecomment(String pays) {
		System.out.println("Affichons la database");
		Cursor c = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " order by " + ELEVE, null);

		if(c.getCount() == 0) {
			System.out.println("Aucun commentaire n'a �t� �crit pour le cours choisi");
			return null;
		}
		return c;  
	}
}
