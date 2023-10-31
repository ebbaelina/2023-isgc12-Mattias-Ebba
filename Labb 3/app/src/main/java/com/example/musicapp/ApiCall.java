package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

public class ApiCall extends AppCompatActivity {
    private final String API_KEY = "d538a1d9647bf180d5b513ae4c4e7abc";
    String artistMatch1;
    String artistMatch2;
    String artistMatch3;
    String artistMatch4;
    String artistMatch5;
    String inputArtist;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String inputKeyValue = intent.getStringExtra("artist");

        getData(inputKeyValue);

    }

    public void getData(String inputKeyValue){
        //Om det finns mellanslag i input strängen byt ut den mot inget mellanslag
        String noSpace = inputKeyValue.replace(" ", "+");

        URL url;
        String apiUrl = "https://ws.audioscrobbler.com/2.0/?method=artist.getsimilar&artist=" + noSpace + "&api_key=" + API_KEY;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            url = new URL(apiUrl);

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();
            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            String tagName;

            int currentTag = 0;

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                if (parserEvent == XmlPullParser.START_TAG) {
                    tagName = parser.getName();

                    if(tagName.equals("similarartists")){
                        inputArtist = parser.getAttributeValue(0);
                    }
                    if (tagName.equals("name")) {
                        currentTag++;
                        if (currentTag == 1) {
                            artistMatch1 = parser.nextText();
                        } else if (currentTag == 2){
                            artistMatch2 = parser.nextText();
                        } else if (currentTag == 3){
                            artistMatch3 = parser.nextText();
                        } else if (currentTag == 4){
                            artistMatch4 = parser.nextText();
                        } else if (currentTag == 5){
                            artistMatch5 = parser.nextText();
                        }
                    }
                }
                parserEvent = parser.next();
            }
        }catch (Exception e){
            Log.e("Error", "An error occurred: " + e.getMessage(), e);
        }

        //Anropar aktivitet SecondActivity och skickar med data från API anropet
        Intent intent = new Intent(ApiCall.this, SecondActivity.class);
        intent.putExtra("inputArtist", inputArtist);
        intent.putExtra("artistMatch1", artistMatch1);
        intent.putExtra("artistMatch2", artistMatch2);
        intent.putExtra("artistMatch3", artistMatch3);
        intent.putExtra("artistMatch4", artistMatch4);
        intent.putExtra("artistMatch5", artistMatch5);
        startActivity(intent);
    }

}
