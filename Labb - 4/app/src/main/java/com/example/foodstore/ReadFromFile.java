package com.example.foodstore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class ReadFromFile extends AppCompatActivity{
    public static WriteToFile instance;
    private final String FILENAME = "shopL.txt";
    private StringBuilder res;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        readFromFile();
    }
    public void readFromFile(){
        ArrayList<String> aList = new ArrayList<>();

        try {
            InputStream in = openFileInput(FILENAME);
            String line;

            if (in != null) {
                InputStreamReader input = new InputStreamReader(in);
                BufferedReader buff = new BufferedReader(input);

                while ((line = buff.readLine()) != null) {
                    aList.add(line);
                }
                in.close();
                Intent intent = new Intent(ReadFromFile.this, ThirdActivity.class);
                intent.putExtra("productsFromFile", aList);
                startActivity(intent);
            }
        } catch (Exception e) {
            Log.e("fel i readFromFile", "readFromFile: " + e.toString());
        }
    }

}


