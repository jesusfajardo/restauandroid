package com.example.deivi.pedidosonline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import collections.MenuAdapter;
import collections.Menus;
import collections.ResAdapter;
import collections.Restaurants;
import cz.msebera.android.httpclient.Header;

public class VerRestaurant extends AppCompatActivity {
    ListView listares;
    ArrayList<Restaurants>restaurants=new ArrayList<Restaurants>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_restaurant);
        listares = findViewById(R.id.lisrestaurant);
        loadComponents();
    }
    private void loadComponents() {
        AsyncHttpClient client = new AsyncHttpClient ();
        client.get ("http://172.25.0.1:8000/api/v1.0/restaurant",  new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONArray data = response.getJSONArray("result");
                    for (int i =0 ; i < data.length(); i++) {
                        Restaurants res =new Restaurants();
                        JSONObject object = data.getJSONObject(i);
                        res.setNombre(object.getString("nombre"));
                        res.setTelefono(object.getInt("telefono"));
                        res.setCalle(object.getString("calle"));
                        //menus.setFoto(object.getString("foto"));
                        restaurants.add(res);
                    }
                    ResAdapter adapter =  new ResAdapter(VerRestaurant.this,restaurants);
                    listares.setAdapter(adapter);


                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        });




    }
}
