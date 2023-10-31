package com.example.foodstore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sax.Element;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {
    Button btnVaror;
    TextView listaVaror;
    ArrayList<String> productsFromFile;
    LinearLayout linearLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_cart);
        btnVaror = findViewById(R.id.btnVaror2);
        linearLayout =findViewById(R.id.linearLayout);


        Intent intent = getIntent();
        productsFromFile = intent.getStringArrayListExtra("productsFromFile");
        AddToShoppingList(productsFromFile);

        btnVaror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        }

    public void AddToShoppingList(ArrayList<String> productsFromFile) {
        for(int i = 0; i < productsFromFile.size(); i++){
            Log.e("string from Arraylist", productsFromFile.get(i));
            final TextView text = new TextView(this);
            text.setText(productsFromFile.get(i));
            linearLayout.addView(text);

        }
    }

}


