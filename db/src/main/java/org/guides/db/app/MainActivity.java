package org.guides.db.app;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StudentsDbHelper mDbHelper = new StudentsDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(StudentContract.StudentEntry._ID, 101);
        values.put(StudentContract.StudentEntry.COLUMN_NAME_NAME, "Peter Ivan");
        values.put(StudentContract.StudentEntry.COLUMN_NAME_AGE, 21);

        long newRowId;
        newRowId = db.insert(StudentContract.StudentEntry.TABLE_NAME, null, values);
        Log.d(TAG, "inserted " + newRowId);
    }

    public void read(StudentsDbHelper helper) {
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(StudentContract.StudentEntry.TABLE_NAME, new String[]{StudentContract.StudentEntry.COLUMN_NAME_NAME,
                StudentContract.StudentEntry.COLUMN_NAME_AGE}, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex(StudentContract.StudentEntry.COLUMN_NAME_NAME));
            int age = cursor.getInt(cursor.getColumnIndex(StudentContract.StudentEntry.COLUMN_NAME_AGE));
            Log.d(TAG, "take " + name + " " + age);
        }

    }
}
