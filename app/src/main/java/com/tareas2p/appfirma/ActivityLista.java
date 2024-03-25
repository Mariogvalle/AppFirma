package com.tareas2p.appfirma;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import android.widget.SearchView;
import android.widget.Toast;

import Configuracion.SQLiteConexion;
import Configuracion.Transacciones;
import Modelos.Signatures;

public class ActivityLista extends AppCompatActivity {
    SQLiteConexion conexion;
    ListView listsignatures;
    ArrayList<Signatures> lista;
    ArrayList<String> Arreglo;
    ArrayAdapter<String> ad;


    Button eliminar, actualizar, agregar, compartir, verimagen;
    String id;


    static final String ACTION_CALL="100";

    ArrayList<String> list;


    SearchView busqueda;
    Integer idposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        conexion = new SQLiteConexion(this, Transacciones.DBName, null, 1);
        listsignatures = (ListView) findViewById(R.id.listsignatures);
        obtenerDatos();

        listsignatures.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String elementoSeleccionado = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"Seleccionates " + elementoSeleccionado, Toast.LENGTH_LONG).show();
            }
        });
        listsignatures.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private long lastClickTime = 0;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long currentTime = System.currentTimeMillis();

                if (currentTime - lastClickTime < 500) { // Considerar como doble clic si ocurre dentro de 500 ms

                    onItemDoubleClick(position);

                } else {

                    onItemSingleClick(position);
                }
                int idposition=position;
                lastClickTime = currentTime;
            }
        });
    }

    private void onItemSingleClick(int position) {
    }

    private void onItemDoubleClick(int position) {
    }

    private void obtenerDatos() {
        SQLiteDatabase db =  conexion.getReadableDatabase();
        Signatures signature = null;
        lista = new ArrayList<>();
        Arreglo = new ArrayList<>();
        Cursor cursor = db.rawQuery(Transacciones.SelectAllSignatures,null);

        while (cursor.moveToNext()){
            signature = new Signatures();
            signature.setId(cursor.getInt(0));
            signature.setDescripcion(cursor.getString(1));
            signature.setSign(cursor.getBlob(2));
            lista.add(signature);

        }
        cursor.close();
        LlenarData();
    }

    private void LlenarData() {
        Arreglo = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++) {
            Arreglo.add(lista.get(i).getId() + "\n" +
                    lista.get(i).getDescripcion() + "\n"+
                    lista.get(i).getSign() + "\n");
        }

        list = new ArrayList<>(Arreglo);
        ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listsignatures.setAdapter(ad);

    }
}