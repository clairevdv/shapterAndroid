package ueBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper{
	private static final String TABLE_NAME_UE = "app_course";
	private static final String KEY_UE = "_id";
	private static final String COLUMN1_UE = "school_id";
	private static final String COLUMN2_UE = "code";
	private static final String COLUMN3_UE = "title";
	private static final String COLUMN4_UE = "description";

	private static final String TABLE_NAME_ECOLE = "app_school";
	private static final String KEY_ECOLE = "_id";
	private static final String COLUMN1_ECOLE = "name";
	private static final String COLUMN2_ECOLE = "country";
	private static final String COLUMN3_ECOLE = "description";	
	
	private static final String TABLE_NAME_USER = "auth_user";
	private static final String KEY_USER = "_id";
	private static final String COLUMN1_USER = "username";
	private static final String COLUMN2_USER = "first_name";
	private static final String COLUMN3_USER = "last_name";
	private static final String COLUMN4_USER = "email";	
	private static final String COLUMN5_USER = "password";	
	private static final String COLUMN6_USER = "is_staff";	
	private static final String COLUMN7_USER = "is_active";	
	private static final String COLUMN8_USER = "is_superuser";	

	public static final String CREATE_TABLE_COURS = "CREATE TABLE " + TABLE_NAME_UE + " (" + KEY_UE + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN1_UE + " INTEGER, " + COLUMN2_UE + " TEXT NOT NULL, " + COLUMN3_UE + " TEXT NOT NULL, " + COLUMN4_UE + " TEXT);" ;
	public static final String CREATE_TABLE_ECOLE = "CREATE TABLE " + TABLE_NAME_ECOLE + " (" + KEY_ECOLE + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN1_ECOLE + " TEXT NOT NULL, " + COLUMN2_ECOLE + " TEXT NOT NULL, " + COLUMN3_ECOLE + " TEXT);" ;
	public static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_NAME_USER + " (" + KEY_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN1_USER + " TEXT NOT NULL, " + COLUMN2_USER + " TEXT NOT NULL, " + COLUMN3_USER + " TEXT NOT NULL, " + COLUMN4_USER + " TEXT NOT NULL, " + COLUMN5_USER + " TEXT NOT NULL, " + COLUMN6_USER + " INTEGER, " + COLUMN7_USER + " INTEGER, " + COLUMN8_USER + " INTEGER);";

	public DatabaseHandler(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(CREATE_TABLE_COURS);
			db.execSQL(CREATE_TABLE_ECOLE);
			db.execSQL(CREATE_TABLE_USER);
		}
		catch(SQLiteException e) {
			Log.e("create table error: ",e.toString());
		}
	};
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w("LunchList", "Upgrading database, which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_UE + ";");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ECOLE + ";");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER + ";");
		onCreate(db);
	}


	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO : gérer plus proprement quand on quitte la phase de test (dans le onUpgrade uniquement)
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_UE + ";");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ECOLE + ";");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER + ";");
		db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_NAME_UE + "'");
		db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_NAME_ECOLE + "'");
		db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_NAME_USER + "'");

		onCreate(db);
	}
}
