package org.guides.shared_prefs.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    public static final String DEMO_PREFS = "demo_prefs";
    private EditText editIntValue;
    private EditText editIntKey;

    private EditText editStringValue;
    private EditText editStringKey;

    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editIntKey = (EditText) findViewById(R.id.int_key);
        editIntValue = (EditText) findViewById(R.id.int_value);

        editStringKey = (EditText) findViewById(R.id.string_key);
        editStringValue = (EditText) findViewById(R.id.string_value);

        findViewById(R.id.add_int).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIntValue();
            }
        });

        findViewById(R.id.add_string).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStringValue();
            }
        });

        findViewById(R.id.read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readPrefs();
            }
        });

        sharedPrefs = getSharedPreferences(DEMO_PREFS, MODE_PRIVATE);
    }

    private void readPrefs() {
        Intent intent = new Intent(this, ReadActivity.class);
        startActivity(intent);
    }

    private void addStringValue() {
        String key = editStringKey.getText().toString();
        if (TextUtils.isEmpty(key)) {
            return;
        }
        SharedPreferences.Editor editor = sharedPrefs.edit();
        String value = editStringValue.getText().toString();
        editor.putString(key, value);
        editor.apply();

        editStringKey.setText("");
        editStringValue.setText("");
    }

    private void addIntValue() {
        String key = editIntKey.getText().toString();
        if (TextUtils.isEmpty(key)) {
            return;
        }
        String value = editIntValue.getText().toString();
        int realValue = 0;
        try {
            realValue = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(key, realValue);
        editor.apply();

        editIntKey.setText("");
        editIntValue.setText("");
    }
}
