package com.example.foodstore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class WriteToFile extends AppCompatActivity{
    public static WriteToFile instance;
    private final String FILENAME = "shopL.txt";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hämtar upp namnet på varan som användaren vill köpa
        Intent intent = getIntent();
        String productName = intent.getStringExtra("product");
        writeToFile(productName);
    }

    public WriteToFile(){
        if(instance == null){
            instance = this;
        }
    }

   public void writeToFile(String product){
        Log.e("WriteToFile", product);

        try{
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
            fos.write(product.getBytes(StandardCharsets.US_ASCII));
            fos.write("\n".getBytes());
            fos.close();

            Intent intent = new Intent(WriteToFile.this, MainActivity.class);
            startActivity(intent);

        }catch (Exception e){
            e.printStackTrace();
            Log.e("fel i ReadWriteData", "fel" + e);
        }
   }
}
