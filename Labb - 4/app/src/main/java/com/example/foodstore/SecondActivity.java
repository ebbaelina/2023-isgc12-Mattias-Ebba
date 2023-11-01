package com.example.foodstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    TextView txtName;
    TextView txtPrice;
    TextView txtDescription;
    TextView txtCompany;
    private Model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_product);
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtDescription = findViewById(R.id.txtDescription);
        txtCompany = findViewById(R.id.txtCompany);
        Button backBtn = findViewById(R.id.backBtn);
        Button btnBuy = findViewById(R.id.btnBuy);
        new WriteToFile();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });

        //När användaren klickar på köp skickas model.getName() till ReadWriteData för att skriva in vara i fil
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, WriteToFile.class);
                intent.putExtra("product", model.getName());
                startActivity(intent);
            }
        });

        //hämtar ut model för det card(vara) som användaren klickade på
       model = (Model) getIntent().getSerializableExtra("products");

       //Skriver ut JSON info för den vara som blev klickad i MainActivity
       txtName.setText(model.getName());
       txtPrice.setText(model.getPrice());
       txtDescription.setText(model.getDescription());
       txtCompany.setText(model.getCompany());
    }
}
