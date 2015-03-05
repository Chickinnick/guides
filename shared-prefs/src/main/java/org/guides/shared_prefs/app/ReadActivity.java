package org.guides.shared_prefs.app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Map;

public class ReadActivity extends Activity {

    private TextView textView;

    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        textView = (TextView) findViewById(R.id.text_output);

        sharedPrefs = getSharedPreferences(MainActivity.DEMO_PREFS, MODE_PRIVATE);
        Map<String, ?> all = sharedPrefs.getAll();
        for(String key : all.keySet()){
            textView.append(key);
            textView.append(" = ");
            textView.append(String.valueOf(all.get(key)));
            textView.append("\n");
        }

    }
}
