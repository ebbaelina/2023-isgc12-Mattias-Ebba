//https://www.geeksforgeeks.org/cardview-using-recyclerview-in-android-with-example/
package com.example.foodstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.Cache;
import com.android.volley.Network;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements VolleyCallback, OnProductClickListener{

    private APIcall apiCall;
    private ArrayList<Model> modelArrayList;
    RecyclerView rv;
    Button btnLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.recyclerView);
        btnLista = findViewById(R.id.btnLista);
        new WriteToFile();

        //API
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 *1024);
        Network network = new BasicNetwork(new HurlStack());
        RequestQueue requestQueue = new RequestQueue(cache, network);
        requestQueue.start();

        String API_KEY = "h2s269nsMn012NASi2537bsA9dBSa2";
        String url = "https://informatik-webbkurser.hotell.kau.se/WebAPI/v1/products?limit=10&apikey=" + API_KEY + "";

        apiCall = new APIcall();

        apiCall.get(requestQueue, this, url);

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ReadFromFile.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSuccess(JSONObject object) {
        RecyclerView rv = findViewById(R.id.recyclerView);

        try {
            Log.e("tag", object.toString());
            modelArrayList = new ArrayList<Model>();
            JSONArray products = object.getJSONArray("products");

            for(int i = 0; i < products.length(); i++){
            String name = products.getJSONObject(i).get("name").toString();
            String price = products.getJSONObject(i).get("price").toString();
            String description = products.getJSONObject(i).get("description").toString();
            String company = products.getJSONObject(i).get("company").toString();

            modelArrayList.add(new Model(name, "$" + price, description, company));

            Adapter adapter = new Adapter(this, modelArrayList,this);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

            rv.setLayoutManager(linearLayoutManager);
            rv.setAdapter(adapter);
        }
        }catch (Exception e){
            Log.e("error", e.toString());
        }
    }

    @Override
    public void onFailure(Exception e) {
        Log.e("MAIN ACTIVITY!", "onFailure: " + e.toString());
    }

    @Override
    //om användaren klickar på ett card(en vara)
    public void onItemClick(int position){

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        intent.putExtra("products", modelArrayList.get(position));

        startActivity(intent);
    }

}