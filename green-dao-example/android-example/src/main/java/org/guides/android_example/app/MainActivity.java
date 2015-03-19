package org.guides.android_example.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import org.guides.android_example.app.dao.DaoSession;
import org.guides.android_example.app.dao.Student;

import java.util.List;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText nameText;

    private ListView listView;

    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent(nameText.getText().toString());
            }
        });
        nameText = (EditText) findViewById(R.id.edit_name);
        MainApplication application = (MainApplication) getApplication();

        daoSession = application.getDaoSession();

        List<Student> students = daoSession.getStudentDao().loadAll();

        StudentAdapter adapter = new StudentAdapter(this, students);

        listView = (ListView) findViewById(R.id.data_list);

        listView.setAdapter(adapter);
    }

    private void addStudent(String s) {
        Student student = new Student();
        student.setName(s);
        student.setAge(22);

        daoSession.getStudentDao().insert(student);
    }

    private static class StudentAdapter extends ArrayAdapter<Student> {

        public StudentAdapter(Context context, List<Student> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = View.inflate(getContext(), android.R.layout.simple_list_item_1, null);
            }
            TextView text = (TextView) convertView.findViewById(android.R.id.text1);
            text.setText(getItem(position).getName());
            return convertView;
        }
    }
}
