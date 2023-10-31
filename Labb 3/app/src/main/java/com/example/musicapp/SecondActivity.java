//https://www.c-sharpcorner.com/article/how-to-be-working-with-multiple-activities-and-navigate-the-activities-in-androi/

package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

public class SecondActivity extends MainActivity{
    //deklarera variabler
    Button btnSecondActivity;
    TextView artistTxt;
    TextView relatedArtists1;
    TextView relatedArtists2;
    TextView relatedArtists3;
    TextView relatedArtists4;
    TextView relatedArtists5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //initialisera variablerna
        btnSecondActivity = findViewById(R.id.btnSecondActivity);
        artistTxt = findViewById(R.id.ArtistTxt);
        relatedArtists1 = findViewById(R.id.relatedArtists1);
        relatedArtists2 = findViewById(R.id.relatedArtists2);
        relatedArtists3 = findViewById(R.id.relatedArtists3);
        relatedArtists4 = findViewById(R.id.relatedArtists4);
        relatedArtists5 = findViewById(R.id.relatedArtists5);


        //lyssnare på knapp som anropar main activity
        btnSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent First = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(First);
            }
        });

        //Hämtar data från API anropet
        Intent getApiData = getIntent();
        String inputArtist = getApiData.getStringExtra("inputArtist");
        String artistMatch1 = getApiData.getStringExtra("artistMatch1");
        String artistMatch2 = getApiData.getStringExtra("artistMatch2");
        String artistMatch3 = getApiData.getStringExtra("artistMatch3");
        String artistMatch4 = getApiData.getStringExtra("artistMatch4");
        String artistMatch5 = getApiData.getStringExtra("artistMatch5");

        artistTxt.setText(inputArtist);
        relatedArtists1.setText(artistMatch1);
        relatedArtists2.setText(artistMatch2);
        relatedArtists3.setText(artistMatch3);
        relatedArtists4.setText(artistMatch4);
        relatedArtists5.setText(artistMatch5);

    }

}
