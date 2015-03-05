package org.guides.files.app;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;

public class ListFilesActivity extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_files);
        textView = (TextView) findViewById(R.id.list_files);

        Uri uri = getIntent().getData();
        File root = new File(uri.getPath());
        File[] files = root.listFiles();
        for (File f : files){
            if(f.isDirectory()){
                textView.append(f.getName() + " - [DIRECTORY]\n");
            }else{
                textView.append(f.getName() + " - [FILE]\n");
            }
            textView.append("\n");
        }
    }
}
