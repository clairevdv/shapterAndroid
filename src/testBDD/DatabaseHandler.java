package testBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DatabaseHandler extends SQLiteOpenHelper{
	private static final String COURS_TABLE_NAME = "UE";
	private static final String UE_KEY = "id";
	private static final String PARCOURS_UE = "parcours";
	private static final String TITRE_UE = "titreUE";
	
	public static final String CREATE_BDD_COURS = "CREATE TABLE " + COURS_TABLE_NAME + " (" + UE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PARCOURS_UE + " TEXT NOT NULL, " + TITRE_UE + " TEXT NOT NULL);" ;
	public static final String DROP_TABLE_COURS = "DROP TABLE IF EXISTS " + COURS_TABLE_NAME + ";";
	
	public DatabaseHandler(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		//on crée la table à partir de la requête écrite dans la variable CREATE_BDD
		db.execSQL(CREATE_BDD_COURS);
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
		//comme ça lorsque je change la version les id repartent de 0
		db.execSQL(DROP_TABLE_COURS);
		onCreate(db);
	}
 
}
