package org.guides.db.app;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText nameText;

    private SQLiteDatabase database;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addValue(nameText.getText().toString(), database);
                queryData();
            }
        });
        nameText = (EditText) findViewById(R.id.edit_name);
        StudentsDbHelper mDbHelper = new StudentsDbHelper(this);
        database = mDbHelper.getWritableDatabase();

        listView = (ListView) findViewById(R.id.data_list);
        queryData();
    }

    private void queryData(){
        Cursor cursor = database.query(StudentContract.StudentEntry.TABLE_NAME,
                new String[]{StudentContract.StudentEntry._ID,StudentContract.StudentEntry.COLUMN_NAME_NAME,
                        StudentContract.StudentEntry.COLUMN_NAME_AGE}, null, null, null, null, null, null);

        ListAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor,
                new String[]{StudentContract.StudentEntry.COLUMN_NAME_NAME},
                new int[]{android.R.id.text1}, CursorAdapter.FLAG_AUTO_REQUERY);

        listView.setAdapter(adapter);
    }

    private void addValue(String name, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(StudentContract.StudentEntry.COLUMN_NAME_NAME, name);
        values.put(StudentContract.StudentEntry.COLUMN_NAME_AGE, 21);

        long newRowId = db.insert(StudentContract.StudentEntry.TABLE_NAME, null, values);
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
