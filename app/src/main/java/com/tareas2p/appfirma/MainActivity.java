package com.tareas2p.appfirma;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.ByteArrayOutputStream;

import Configuracion.SQLiteConexion;
import Configuracion.Transacciones;

public class MainActivity extends AppCompatActivity {

    EditText editTextId, editTextDescription;
    SignaturePad signaturePad;
    Button buttonSave, buttonverfirmas;
    String signatureBase64;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //editTextDescription = findViewById(R.id.editTextDescription);
        signaturePad = findViewById(R.id.signaturePad);
        buttonSave = findViewById(R.id.btnSave);
        buttonverfirmas = findViewById(R.id.btnfirmas);

        //dbHelper = new DBHelper(this);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        buttonverfirmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityLista.class);
                startActivity(intent);
            }
        });
    }

    private void saveData() {
        //String id = "1"editTextId.getText().toString();
        //String description = editTextDescription.getText().toString();
        String id="1";
        String descripcion="fima primaria";

        if (id.isEmpty() || descripcion.isEmpty() || signaturePad.isEmpty()) {
            Toast.makeText(this, "Incluya información adicional para la firma", Toast.LENGTH_SHORT).show();
            return;
        }

        Bitmap signatureBitmap = signaturePad.getSignatureBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        signatureBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        signatureBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

        saveSignDB(signatureBase64);


        //long result = dbHelper.insertData(id, description, signatureBase64);
        //if (result == -1) {
        //    Toast.makeText(this, "Error saving data", Toast.LENGTH_SHORT).show();
        //} else {
        //    Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();
        //    editTextId.getText().clear();
        //    editTextDescription.getText().clear();
        //    signaturePad.clear();
       // }
    }

    private void saveSignDB(String signa) {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.DBName, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues datos = new ContentValues();
        datos.put(Transacciones.descripcion, "firma principal");
        datos.put(Transacciones.sign,signa);

        Long resultado = db.insert(Transacciones.TableSignatures, Transacciones.id, datos);

        Toast.makeText(getApplicationContext(), "Información ingresada correctamente " + resultado.toString(),
                Toast.LENGTH_LONG).show();
    }
}
