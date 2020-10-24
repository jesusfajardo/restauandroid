package com.example.deivi.pedidosonline;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

import collections.MenuAdapter;
import collections.Menus;
import cz.msebera.android.httpclient.Header;

public class Menu extends AppCompatActivity {
    ListView lista;
    EditText c;
    TextView p;
    String precios ,cant, total1;
    Double pre,total,can;

    ArrayList<Menus> list_data = new ArrayList<Menus> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        lista = Menu.this.findViewById (R.id.lismenu);
        loadComponents();
        lista.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final Menus menus= list_data.get (position);
                AlertDialog.Builder builder = new AlertDialog.Builder (Menu.this);
                LayoutInflater inflater = (Menu.this).getLayoutInflater();
                builder.setTitle("Cantidad");
                builder.setView(inflater.inflate(R.layout.dialogo, null));
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        Intent i = new Intent(Menu.this, Carrito.class);
                        i.putExtra("nombre", menus.nombre);
                        i.putExtra("descripcion", menus.descripcion);
                        i.putExtra("precio", menus.precio);
                        // i.putExtra ("image",item.foto);

                        startActivity(i);


                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                Dialog dialog = builder.create ();
                dialog.show ();


            }
        });


    }
    private void loadComponents() {
        AsyncHttpClient client = new AsyncHttpClient ();
<<<<<<< HEAD
        client.get ("http://192.168.1.102:8000/api/v1.0/menus",  new JsonHttpResponseHandler(){
=======
        client.get ("http://172.25.0.1:8000/api/v1.0/menus",  new JsonHttpResponseHandler(){
>>>>>>> d6d77596d4c6637dad1044d5df21eddffc0100de
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONArray data = response.getJSONArray("result");
                    for (int i =0 ; i < data.length(); i++) {
                        Menus menus = new Menus();
                        JSONObject object = data.getJSONObject(i);
                        menus.setId(object.getString("_id"));
                        menus.setNombre(object.getString("nombre"));
                        menus.setDescripcion(object.getString("descripcion"));
                        menus.setPrecio(object.getInt("precio"));
                        //menus.setFoto(object.getString("foto"));
                        list_data.add(menus);
                    }
                    MenuAdapter adapter =  new MenuAdapter(Menu.this,list_data);
                    lista.setAdapter(adapter);


                }catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        });




    }




}
