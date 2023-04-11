package com.myapp.sistemabancario;

import static java.lang.Double.parseDouble;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity_cuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cuenta);

    // declaracion de datos
        EditText identificacioncuenta, fechacuenta, saldocuenta;
        Button guardarcuenta;
        ImageButton regresarcuenta;

    // declaracion de variables

        // EditText
        identificacioncuenta = findViewById(R.id.etidentificacioncuenta);
        fechacuenta = findViewById(R.id.etfechacuenta);
        saldocuenta = findViewById(R.id.etsaldocuenta);

        // Button
        guardarcuenta = findViewById(R.id.btnguardarcuenta);

        // imageButton
        regresarcuenta = findViewById(R.id.ibnregresarcuenta);

    // Recibir los datos enviados desde el archivo MainActivity
        identificacioncuenta.setText(getIntent().getStringExtra("eidentificacioncliente"));

        // boton regresar
        regresarcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        // boton guardar
        guardarcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!fechacuenta.getText().toString().isEmpty() && !saldocuenta.getText().toString().isEmpty()) {

                    if (parseDouble (saldocuenta.getText().toString()) >= 1000000 && parseDouble (saldocuenta.getText().toString()) <= 50000000) {

                        clsBancoDataBase sohCuenta = new clsBancoDataBase(getApplicationContext(), "dbsistemabancario", null,1);

                        SQLiteDatabase dbw = sohCuenta.getWritableDatabase();

                        ContentValues cvCuenta = new ContentValues();

                        cvCuenta.put("identificacioncliente", identificacioncuenta.getText().toString());
                        cvCuenta.put("fechacuenta", fechacuenta.getText().toString());
                        cvCuenta.put("saldocuenta", saldocuenta.getText().toString());

                        double saldototal = parseDouble(saldocuenta.getText().toString());

                        dbw.insert("cuenta", null, cvCuenta);

                        dbw.execSQL("UPDATE cliente SET totalsaldo = ' " + saldototal + " ' WHERE identificacioncliente = ' " + identificacioncuenta.getText().toString() + " ' ");

                        dbw.close();

                        Toast.makeText(getApplicationContext(),"CUENTA GUARDADA CON EXITO!!",Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(getApplicationContext(), "EL SALDO DE CUENTA DEBE DE ESTAR ENTRE 1 A 50 MILLONES!!",Toast.LENGTH_SHORT).show();

                    }

                } else {

                    Toast.makeText(getApplicationContext(),"INGRESE TODOS LOS CAMPOS!!",Toast.LENGTH_SHORT).show();

                }

            }

        });


    }
}