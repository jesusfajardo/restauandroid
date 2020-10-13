package com.example.deivi.pedidosonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoRestaurant extends AppCompatActivity {
    Button crear,ver,edit,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_restaurant);
        crear = findViewById(R.id.crearmenu);
        ver = findViewById(R.id.vermenu);
        edit = findViewById(R.id.editarmenu);
        delete = findViewById(R.id.elimenu);
        final TextView nombre=(TextView) findViewById(R.id.nombre);
        nombre.setText( getIntent().getExtras().getString("nombre"));
        final TextView calle=(TextView) findViewById(R.id.calle);
        calle.setText( getIntent().getExtras().getString("calle"));
        final TextView telefono=(TextView) findViewById(R.id.telefono);
        telefono.setText( getIntent().getExtras().getString("telefono"));
        final TextView propietario=(TextView) findViewById(R.id.property);
        propietario.setText( getIntent().getExtras().getString("propietario"));

        String _id_de_mi_restaurant=getIntent().getExtras().getString("_id");

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfoRestaurant.this,CrearMenu.class));

            }
        });
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfoRestaurant.this,VerMenu.class));

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfoRestaurant.this,EditarMenu.class));
            }
        });
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfoRestaurant.this,EliminarMenu.class));

            }
        });
    }
}
