package org.guides.content_provider.app;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.*;


public class MainActivity extends ActionBarActivity {
    private EditText nameText;

    private ListView listView;

    private CursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent(nameText.getText().toString());
            }
        });
        nameText = (EditText) findViewById(R.id.edit_name);

        listView = (ListView) findViewById(R.id.data_list);

        Uri studentsUri = Uri.withAppendedPath(StudentsContentProvider.CONTENT_URI, "students");
        Cursor cursor = getContentResolver().query(studentsUri, new String[]{
                StudentContract.StudentEntry._ID,
                StudentContract.StudentEntry.COLUMN_NAME_NAME,
                StudentContract.StudentEntry.COLUMN_NAME_AGE
        }, null, null, null);
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor,
                new String[]{StudentContract.StudentEntry.COLUMN_NAME_NAME},
                new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        listView.setAdapter(adapter);
    }

    private void addStudent(String studentName) {
        ContentValues values = new ContentValues();
        values.put(StudentContract.StudentEntry.COLUMN_NAME_NAME, studentName);
        values.put(StudentContract.StudentEntry.COLUMN_NAME_AGE, 21);

        Uri studentsUri = Uri.withAppendedPath(StudentsContentProvider.CONTENT_URI, "students");

        getContentResolver().insert(studentsUri,
                values
        );
        adapter.notifyDataSetChanged();
    }

}
