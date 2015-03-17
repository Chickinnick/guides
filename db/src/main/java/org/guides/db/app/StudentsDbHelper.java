package org.guides.db.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class StudentsDbHelper extends SQLiteOpenHelper {

    private static final String CREATE_STUDENTS =
            "CREATE TABLE IF NOT EXISTS " + StudentContract.StudentEntry.TABLE_NAME + " (" +
                    StudentContract.StudentEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    StudentContract.StudentEntry.COLUMN_NAME_NAME + " TEXT," +
                    StudentContract.StudentEntry.COLUMN_NAME_AGE + " INTEGER)";

    private static final String DELETE_STUDENTS =
            "DROP TABLE IF EXISTS " + StudentContract.StudentEntry.TABLE_NAME;

    public static final String DB_NAME = "university.db";

    public static final int DB_VERSION = 1;

    public StudentsDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENTS);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_STUDENTS);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
