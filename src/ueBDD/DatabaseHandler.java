package ueBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{
	private static final String TABLE_NAME = "UE";
	private static final String KEY = "_id";
	private static final String COLUMN1 = "parcours";
	private static final String COLUMN2 = "titreUE";
	private static final String COLUMN3 = "description";
	
	public static final String CREATE_BDD_COURS = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN1 + " TEXT NOT NULL, " + COLUMN2 + " TEXT NOT NULL, " + COLUMN3 + " TEXT);" ;
	public static final String DROP_TABLE_COURS = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
	public static final String EMPTY_TABLE_COURS = "DELETE FROM " + TABLE_NAME +";";
	
	public DatabaseHandler(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_BDD_COURS);
	};
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DROP_TABLE_COURS);
		onCreate(db);
	}
	
	
	@Override
	 public void onOpen(SQLiteDatabase db) {
		db.execSQL(DROP_TABLE_COURS);
		db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_NAME + "'");
		onCreate(db);
	}
	
	public static String getTableName() {return TABLE_NAME;}
	public static String getKey() {return KEY;}
	public static String getColumn1() {return COLUMN1;}
	public static String getColumn2() {return COLUMN2;}
	public static String getColumn3() {return COLUMN3;}
}
