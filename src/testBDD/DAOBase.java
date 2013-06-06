package testBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class DAOBase {
	//Attribut de version, a modifier a chaque mise a jour
	protected final static int VERSION = 1;
	protected final static String NOM_BASE = "database.db";

	protected SQLiteDatabase mDb = null;
	protected DatabaseHandler mHandler = null;

	public DAOBase(Context pContext) {
		this.mHandler = new DatabaseHandler(pContext, NOM_BASE, null, VERSION);
	}

	public SQLiteDatabase open() {
		mDb = mHandler.getWritableDatabase();
		return mDb;
	}

	public void close() {
		mDb.close();
	}

	public SQLiteDatabase getDb() {
		return mDb;
	}
}
