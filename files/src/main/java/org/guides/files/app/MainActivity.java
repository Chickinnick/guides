package org.guides.files.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.*;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.list_files_on_sd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File root = Environment.getExternalStorageDirectory();
                if (root == null) {
                    Toast.makeText(MainActivity.this, "No SD Card detected", Toast.LENGTH_SHORT).show();
                    return;
                }
                showListFiles(root);
            }
        });

        findViewById(R.id.list_files_in_cache).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File root = getCacheDir();
                showListFiles(root);
            }
        });

        findViewById(R.id.create_on_sd_card).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewFileOnSdCard();
            }
        });
        findViewById(R.id.create_on_sd_cache).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewFileInCache();
            }
        });

        findViewById(R.id.write_to_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeToFile();
            }
        });

        findViewById(R.id.read_from_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromFile();
            }
        });
    }

    private void readFromFile() {
        File root = Environment.getExternalStorageDirectory();
        if (root == null) {
            Toast.makeText(MainActivity.this, "No SD Card detected", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(root, "new_file.txt")));
            StringBuilder read = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null){
                read.append(line);
            }
            reader.close();
            Toast.makeText(MainActivity.this, "File read from SD Card : " + read.toString(), Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void writeToFile() {
        File root = Environment.getExternalStorageDirectory();
        if (root == null) {
            Toast.makeText(MainActivity.this, "No SD Card detected", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            FileWriter writer = new FileWriter(new File(root, "new_file.txt"));
            writer.write("Hello");
            writer.write("\n");
            writer.write("Hello");
            writer.close();
            Toast.makeText(MainActivity.this, "File wrote on SD Card", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void createNewFileOnSdCard() {
        File root = Environment.getExternalStorageDirectory();
        if (root == null) {
            Toast.makeText(MainActivity.this, "No SD Card detected", Toast.LENGTH_SHORT).show();
            return;
        }
        File newFile = new File(root, "new_file.txt");
        if(newFile.exists()){
            Toast.makeText(MainActivity.this, "File already existed on SD Card", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            boolean result = newFile.createNewFile();
            if(result){
                Toast.makeText(MainActivity.this, "File created on SD Card", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNewFileInCache() {
        File root = getCacheDir();
        File newFile = new File(root, "new_file.txt");
        if(newFile.exists()){
            Toast.makeText(MainActivity.this, "File already existed in cache directory", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            boolean result = newFile.createNewFile();
            if(result){
                Toast.makeText(MainActivity.this, "File created in cache directory", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showListFiles(File root){
        Intent intent = new Intent(this, ListFilesActivity.class);
        intent.setData(Uri.fromFile(root));
        startActivity(intent);
    }
}
