package com.example.deivi.pedidosonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import collections.MenuAdapter;
import collections.Menus;
import collections.VerMenuAdapter;
import cz.msebera.android.httpclient.Header;


public class VerMenu extends AppCompatActivity {
    ListView listamenu;
    ImageButton atras;

    ArrayList<Menus> list_data = new ArrayList<Menus> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_menu);
        atras = findViewById(R.id.imgatras);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VerMenu.this,InfoRestaurant.class));
                finish();
            }
        });
        loadComponents();
    }
    private void loadComponents() {
        AsyncHttpClient client = new AsyncHttpClient ();
        client.get ("http://192.168.1.108:7777/api/v1.0/menus",  new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {

                try {

                    for (int i =0 ; i < jsonArray.length(); i++) {
                        Menus menus = new Menus();
                        JSONObject object = jsonArray.getJSONObject(i);
                        menus.setId(object.getString("_id"));
                        menus.setNombre(object.getString("nombre"));
                        menus.setDescripcion(object.getString("descripcion"));
                        menus.setPrecio(object.getInt("precio"));
                        //menus.setFoto(object.getString("foto"));
                        list_data.add(menus);
                    }
                    VerMenuAdapter adapter =  new VerMenuAdapter(VerMenu.this,list_data);
                    listamenu = findViewById(R.id.listamenu);
                    listamenu.setAdapter(adapter);


                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });




    }
}
