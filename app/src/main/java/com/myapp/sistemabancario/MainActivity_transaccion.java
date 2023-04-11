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

public class MainActivity_transaccion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_transaccion);

    // declaracion de datos
        EditText identificaciontransaccion, fechatransaccion, horatransaccion, saldotransaccion;
        ImageButton regresartransaccion, consignar, retirar;

    // declaracion de variables

        // EditText
        identificaciontransaccion = findViewById(R.id.etidentificaciontransaccion);
        fechatransaccion = findViewById(R.id.etfechatransaccion);
        horatransaccion = findViewById(R.id.etfechatransaccion);
        saldotransaccion = findViewById(R.id.etsaldotransaccion);

        // ImageButton
        consignar = findViewById(R.id.ibconsignar);
        retirar = findViewById(R.id.ibretirar);
        regresartransaccion = findViewById(R.id.ibregresartransaccion);

        // Recibir los datos enviados desde el archivo MainActivity
        identificaciontransaccion.setText(getIntent().getStringExtra("tidentificacioncliente"));

        // boton regresar
        regresartransaccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        // boton consignar
        consignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!fechatransaccion.getText().toString().isEmpty() && !horatransaccion.getText().toString().isEmpty() && !saldotransaccion.getText().toString().isEmpty()) {



                        clsBancoDataBase sohtransaccion = new clsBancoDataBase(getApplicationContext(), "dbsistemabancario", null, 1);

                        SQLiteDatabase dbw = sohtransaccion.getWritableDatabase();

                        ContentValues cvTransaccion = new ContentValues();

                        cvTransaccion.put("identificacioncliente", identificaciontransaccion.getText().toString());
                        cvTransaccion.put("fechatransaccion", fechatransaccion.getText().toString());
                        cvTransaccion.put("horatransaccion", horatransaccion.getText().toString());
                        cvTransaccion.put("saldotransaccion", saldotransaccion.getText().toString());

                        double saldototal = parseDouble(saldotransaccion.getText().toString());

                        dbw.insert("transaccion", null, cvTransaccion);

                        dbw.execSQL("UPDATE cliente SET totalsaldo = totalsaldo + ' " + saldototal + " ' WHERE identificacioncliente = ' " + identificaciontransaccion.getText().toString() + " ' ");

                        dbw.close();

                        Toast.makeText(getApplicationContext(), "CONSIGNACION EXITOSA!!", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(getApplicationContext(),"INGRESE TODOS LOS CAMPOS!!",Toast.LENGTH_SHORT).show();

                }

            }

        });

        // boton retirar
        retirar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!fechatransaccion.getText().toString().isEmpty() && !horatransaccion.getText().toString().isEmpty() && !saldotransaccion.getText().toString().isEmpty()) {
                    
                        clsBancoDataBase sohtransaccion = new clsBancoDataBase(getApplicationContext(), "dbsistemabancario", null, 1);

                        SQLiteDatabase dbw = sohtransaccion.getWritableDatabase();

                        ContentValues cvTransaccion = new ContentValues();

                        cvTransaccion.put("identificacioncliente", identificaciontransaccion.getText().toString());
                        cvTransaccion.put("fechatransaccion", fechatransaccion.getText().toString());
                        cvTransaccion.put("horatransaccion", horatransaccion.getText().toString());
                        cvTransaccion.put("saldotransaccion", saldotransaccion.getText().toString());

                        double saldototal = parseDouble(saldotransaccion.getText().toString());

                        dbw.insert("transaccion", null, cvTransaccion);

                        dbw.execSQL("UPDATE cliente SET totalsaldo = totalsaldo - ' " + saldototal + " ' WHERE identificacioncliente = ' " + identificaciontransaccion.getText().toString() + " ' ");

                        dbw.close();

                        Toast.makeText(getApplicationContext(),"RETIRO EXITOSO!!",Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(getApplicationContext(),"INGRESE TODOS LOS CAMPOS!!",Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}