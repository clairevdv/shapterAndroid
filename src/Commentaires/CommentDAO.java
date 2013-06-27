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
		//Création d'un ContentValues
		ContentValues values = new ContentValues();
		// On ajoute dedans les valeurs de l'Comment
		values.put(TEXTE, comment.getComment());
		values.put(ELEVE, comment.getStudentId());
		//on insère l'objet dans la BDD via le ContentValues
		mDb.insert(TABLE_NAME, null, values);
	}

	public void initialiserTest() {
		Comment e1 = new Comment("Mes 3 ans à Télécom ParisTech ont été les plus bénéfiques, professionnellement parlant. De plus, l'activité associative constante qui y règne m'a à la fois amusé et servi ; amusé par les activités proposées et par l'organisation même d'activités, servi car il est toujours utile de savoir gérer une équipe de 15 personnes, organiser un évènement pour 300 personnes, améliorer ses relations entreprises... Enfin, les activités extra-scolaires (permises entre autres par une administration conciliante) m'ont permis de m'épanouir dans ce que j'aime faire. Bref, je recommande chaudement mon école !", 1);
		Comment e2 = new Comment("Coulos !", 2);
		Comment e3 = new Comment("C'est une UE où l'on en apprend sur beaucoup de domaines mais pas forcément en profondeur. Bref, c'est le but d'une introduction. Ce qui en fait une très bonne UE de base pour ceux qui, comme moi, ne prenne pas pour parcours principal le parcours image : cela permet de se faire une idée générale de ce qu'est le traitement d'images, les différents aspects et applications dans lesquels on peut travailler..", 3);
		Comment e4 = new Comment("Une UE à prendre si on est intéressé (même un tout petit peu) par le domaine de l'image. Les cours sont la plupart du temps bien ficelés avec plusieurs intervenants et de nombreux thèmes abordés (rendu, modélisation, traitement d'image 3D, applications).", 1);
		Comment e5 = new Comment("Ce cours n'était pas exactement ce à quoi je m'attendais. Je m'attendais à un cours sur la théorie et non pas uniquement des travaux pratiques", 4);
		Comment e6 = new Comment("Excellent prof, très ouvert aux questions, réponds vite et bien au mail, prêt à aider dans vos divers projets", 5);
		Comment e7 = new Comment("Le rayon de soleil de ma 2ème année ! Un cours que j'avais pris plus par curiosité que par passion, et je fus comblé. D'excellents profs, la crème de la crème dans le domaine", 6);

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
		//Suppression d'une Comment de la BDD grâce à son index
		mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
	}

	public void deleteCommentEleve(int student_id) {
		mDb.delete(TABLE_NAME, ELEVE + " = ?", new String[] {String.valueOf(student_id)});
	}

	public Cursor tableAffichagecomment(String pays) {
		System.out.println("Affichons la database");
		Cursor c = mDb.rawQuery("select rowid _id,* from " + TABLE_NAME + " order by " + ELEVE, null);

		if(c.getCount() == 0) {
			System.out.println("Aucun commentaire n'a été écrit pour le cours choisi");
			return null;
		}
		return c;  
	}
}
