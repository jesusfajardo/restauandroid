package collections;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.deivi.pedidosonline.CrearMenu;
import com.example.deivi.pedidosonline.R;
import com.example.deivi.pedidosonline.utils.Data;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class BorrarMenuAdapter extends BaseAdapter {
    Context context;
    List<Menus> menus;

    public BorrarMenuAdapter(Context context, ArrayList<Menus> menus) {
        this.context = context;
        this.menus = menus;
    }

    @Override
    public int getCount() {
        return this.menus.size();
    }

    @Override
    public Object getItem(int position) {
        return this.menus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong((String)this.menus.get(position).getId());

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflate = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflate.inflate(R.layout.borrar_menu, null);
        }

        TextView nombre = view.findViewById(R.id.nombreproducto);
        TextView descripcion = view.findViewById(R.id.descripcionproducto);
        TextView precio = view.findViewById(R.id.precioproducto);
        ImageView image = view.findViewById(R.id.imgproducto);
        final String id;
        Button borrar = view.findViewById(R.id.borrarproducto);
      //  Glide.with(context).load("http://192.168.1.102:7777/api/v1.0/" + menus.get(i).getFoto()).into(image);
        nombre.setText(this.menus.get(i).getNombre());
        descripcion.setText(this.menus.get(i).getDescripcion());
        precio.setText(this.menus.get(i).getPrecio().toString());
        id = this.menus.get(i).getId();

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                // Add the buttons
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        // User clicked OK button
                        deleteMenu();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                builder.setTitle("Esta seguro de eliminar el menu");

                AlertDialog dialog = builder.create();
                dialog.show();
            }

            private void deleteMenu() {

                AsyncHttpClient client = new AsyncHttpClient();

                client.delete("http://172.25.0.1:8000/api/v1.0/menus" + "/" + id, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            String message = response.getString("message");
                            if (message != null) {


                                // loadComponents();
                                /*BorrarMenuAdapter adapter = new BorrarMenuAdapter(view);
                                        adapter.notifyDataSetChanged();9*/

                            } else {
                                Toast.makeText(context, "Error al borrar", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }




                });
            }

        });
       /* private void loadComponents(){
            AsyncHttpClient client = new AsyncHttpClient ();
            client.get ("http://192.168.1.102:7777/api/v1.0/menus",  new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    try {
                        JSONArray data = response.getJSONArray("result");
                        for (int i =0 ; i < data.length(); i++) {
                            Menus menus1 = new Menus();
                            JSONObject object = data.getJSONObject(i);
                            menus1.setId(object.getString("_id"));
                            menus1.setNombre(object.getString("nombre"));
                            menus1.setDescripcion(object.getString("descripcion"));
                            menus1.setPrecio(object.getDouble("precio"));
                            //menus.setFoto(object.getString("foto"));
                            menus.add(menus1);
                        }
                        BorrarMenuAdapter adapter =  new BorrarMenuAdapter(context.this,menus);
                        lista.setAdapter(adapter);


                    }catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            });*/





        return view;
    }


}
