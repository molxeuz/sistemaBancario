package com.myapp.sistemabancario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // instanciar la clase  clsBancoDataBase que contiene  SQLiteOpenholder sus tablas
    clsBancoDataBase sohCuenta = new clsBancoDataBase(this, "dbsistemabancario", null, 1);

    //
    String viejoidentificacioncliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    // declaracion de datos
        EditText identificacioncliente, nombrecompleto, correoelectronico, contraseña;
        TextView totalsaldo;
        ImageButton guardar, buscar, editar, borrar, transaccion, cuenta, regresarcliente, limpiar;

    // declaracion de variables

        // EditText
        identificacioncliente = findViewById(R.id.etidentificacioncliente);
        nombrecompleto = findViewById(R.id.etnombrecompleto);
        correoelectronico = findViewById(R.id.etcorreoelectronico);
        contraseña = findViewById(R.id.etcontraseña);

        // TextView
        totalsaldo = findViewById(R.id.tvtotalsaldo);

        // ImageButton
        guardar = findViewById(R.id.ibguardar);
        buscar = findViewById(R.id.ibbuscar);
        editar = findViewById(R.id.ibeditar);
        borrar = findViewById(R.id.ibborrar);
        transaccion = findViewById(R.id.ibtransaccion);
        cuenta = findViewById(R.id.ibcuenta);
        regresarcliente = findViewById(R.id.ibregresarcliente);
        limpiar = findViewById(R.id.iblimpiar);

        // botones desactivados
        borrar.setEnabled(false);
        editar.setEnabled(false);

        cuenta.setEnabled(false);
        transaccion.setEnabled(false);

        // boton limpiar
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                identificacioncliente.setText("");
                identificacioncliente.requestFocus();
                nombrecompleto.setText("");
                correoelectronico.setText("");
                contraseña.setText("");

            }
        });

        // boton regresar
        regresarcliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), MainActivity_inicio.class));

            }
        });

        // boton hacer cuenta
        cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!identificacioncliente.getText().toString().isEmpty() && !nombrecompleto.getText().toString().isEmpty() && !correoelectronico.getText().toString().isEmpty() && !contraseña.getText().toString().isEmpty()) {

                    SQLiteDatabase dbr = sohCuenta.getReadableDatabase();

                    String sql = "SELECT identificacioncliente FROM cliente WHERE identificacioncliente = ' " + identificacioncliente.getText().toString() + " ' ";

                    Cursor cursorCliente = dbr.rawQuery(sql,null);

                    if (cursorCliente.moveToFirst()) {

                        identificacioncliente.setText(cursorCliente.getString(0));

                        Intent iCuenta = new Intent(getApplicationContext(), MainActivity_cuenta.class);

                        iCuenta.putExtra("eidentificacioncliente", identificacioncliente.getText().toString());

                        startActivity(iCuenta);

                    } else {

                        Toast.makeText(getApplicationContext(),"Id Vendedor NO existe ...",Toast.LENGTH_SHORT).show();

                    }

                } else {

                    Toast.makeText(getApplicationContext(),"INGRESE TODOS LOS CAMPOS!!", Toast.LENGTH_SHORT).show();

                }

            }

        });

        // boton hacer transaccion
        transaccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!identificacioncliente.getText().toString().isEmpty() && !nombrecompleto.getText().toString().isEmpty() && !correoelectronico.getText().toString().isEmpty() && !contraseña.getText().toString().isEmpty()) {

                    SQLiteDatabase dbr = sohCuenta.getReadableDatabase();

                    String sql = "SELECT identificacioncliente FROM cliente WHERE identificacioncliente = ' " + identificacioncliente.getText().toString() + " ' ";

                    Cursor cursorTransaccion = dbr.rawQuery(sql,null);

                    if (cursorTransaccion.moveToFirst()) {

                        identificacioncliente.setText(cursorTransaccion.getString(0));

                        Intent iTransaccion = new Intent(getApplicationContext(), MainActivity_transaccion.class);

                        iTransaccion.putExtra("tidentificacioncliente", identificacioncliente.getText().toString());

                        startActivity(iTransaccion);

                    } else {

                        Toast.makeText(getApplicationContext(),"Id Vendedor NO existe ...",Toast.LENGTH_SHORT).show();

                    }

                } else {

                    Toast.makeText(getApplicationContext(),"INGRESE TODOS LOS CAMPOS!!", Toast.LENGTH_SHORT).show();

                }

            }

        });

        // boton borrar
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!identificacioncliente.getText().toString().isEmpty() && !nombrecompleto.getText().toString().isEmpty() && !correoelectronico.getText().toString().isEmpty() && !contraseña.getText().toString().isEmpty()) {

                    SQLiteDatabase dbr = sohCuenta.getReadableDatabase();
                    String sql = "SELECT identificacioncliente FROM cliente WHERE identificacioncliente = ' " + identificacioncliente.getText().toString() +  " ' ";
                    Cursor cursorcliente = dbr.rawQuery(sql, null);

                    if (cursorcliente.moveToFirst()){

                        String queryCuenta = "SELECT identificacioncliente FROM cuenta WHERE identificacioncliente = ' " + identificacioncliente.getText().toString() + " ' ";

                        Cursor cursorcuenta = dbr.rawQuery(queryCuenta, null);

                        if (!cursorcuenta.moveToFirst()) {

                            SQLiteDatabase dbw = sohCuenta.getReadableDatabase();

                            dbw.execSQL("DELETE FROM cliente  WHERE identificacioncliente = ' " + identificacioncliente.getText().toString() + " ' ");

                            dbw.close();
                            dbr.close();

                            identificacioncliente.setText("");
                            identificacioncliente.requestFocus();
                            nombrecompleto.setText("");
                            correoelectronico.setText("");
                            contraseña.setText("");
                            totalsaldo.setText("");

                            editar.setEnabled(false);
                            borrar.setEnabled(false);

                            cuenta.setEnabled(false);
                            transaccion.setEnabled(false);

                            Toast.makeText(getApplicationContext(),"CLIENTE ELIMINADO CON EXITO!!",Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(getApplicationContext(),"CLIENTE CON CUENTA, NO SE PUEDE ELIMINAR!!",Toast.LENGTH_SHORT).show();

                        }

                    } else {

                        Toast.makeText(getApplicationContext(),"IDENTIFICACION NO EXISTENTE!!",Toast.LENGTH_SHORT).show();

                    }

                } else {

                    Toast.makeText(getApplicationContext(),"INGRESE TODOS LOS CAMPOS!!", Toast.LENGTH_SHORT).show();

                }

            }

        });

        // boton editar
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db =  sohCuenta.getReadableDatabase();

                if (!identificacioncliente.getText().toString().isEmpty() && !nombrecompleto.getText().toString().isEmpty() && !correoelectronico.getText().toString().isEmpty() && !contraseña.getText().toString().isEmpty()) {

                    if (viejoidentificacioncliente.equals(identificacioncliente.getText().toString())) {

                        db.execSQL("UPDATE cliente SET nombrecompleto = ' " + nombrecompleto.getText().toString() + " ', " + " correoelectronico = ' " + correoelectronico.getText().toString() + " ', " + " contraseña = ' " + contraseña.getText().toString() + " ' WHERE identificacioncliente = ' " + viejoidentificacioncliente + " ' ");

                        db.close();

                        Toast.makeText(getApplicationContext() ,"CLIENTE ACTUALIZADO CON EXITO!!", Toast.LENGTH_SHORT).show();

                    } else {

                        SQLiteDatabase dbr = sohCuenta.getReadableDatabase();
                        String sql = "SELECT identificacioncliente FROM cliente WHERE identificacioncliente = ' " + identificacioncliente.getText().toString() + " ' ";

                        Cursor cursorCliente = dbr.rawQuery(sql, null);

                        if (!cursorCliente.moveToFirst()) {

                            db.execSQL("UPDATE cliente SET identificacioncliente = ' " + identificacioncliente.getText().toString() + " ', nombrecompleto = ' " + nombrecompleto.getText().toString() + " ', " + " correoelectronico = ' " + correoelectronico.getText().toString() + " ', " + " contraseña = ' " + contraseña.getText().toString() + " ' WHERE identificacioncliente = ' " + viejoidentificacioncliente + " ' ");

                            db.close();

                            Toast.makeText(getApplicationContext(),"CLIENTE ACTUALIZADO CON EXITO!!", Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(getApplicationContext(),"IDENTIFICACION ASIGNADA A OTRO CLIENTE, INTENTO CON OTRO!!", Toast.LENGTH_SHORT).show();

                        }

                    }

                } else {

                    Toast.makeText(getApplicationContext(),"INGRESE TODOS LOS CAMPOS!!", Toast.LENGTH_SHORT).show();

                }

            }
        });

        // boton buscar
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = sohCuenta.getReadableDatabase();
                String query = "SELECT nombrecompleto, correoelectronico, totalsaldo FROM cliente WHERE identificacioncliente = ' " + identificacioncliente.getText().toString() + " ' ";
                Cursor cursorCliente = db.rawQuery(query, null);

                if (cursorCliente.moveToFirst()) {

                    nombrecompleto.setText(cursorCliente.getString(0));
                    correoelectronico.setText(cursorCliente.getString(1));
                    totalsaldo.setText(cursorCliente.getString(2));

                    editar.setEnabled(true);
                    borrar.setEnabled(true);

                    cuenta.setEnabled(true);
                    transaccion.setEnabled(true);

                    viejoidentificacioncliente = identificacioncliente.getText().toString();

                    db.close();

                    Toast.makeText(getApplicationContext() ,"IDENTIFICACION ENCONTRADA!!", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(getApplicationContext() ,"IDENTIFICACION NO EXISTENTE, INTENTE CON OTRA!!", Toast.LENGTH_SHORT).show();

                }

            }
        });

        // boton guardar
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                guardarcliente (identificacioncliente.getText().toString(), nombrecompleto.getText().toString(), correoelectronico.getText().toString(), contraseña.getText().toString());

            }
        });

    }

    private void guardarcliente (String sIdentificacioncliente, String sNombrecompleto, String sCorreoelectronico, String sContraseña){

        if (!sIdentificacioncliente.isEmpty() && !sNombrecompleto.isEmpty() && !sCorreoelectronico.isEmpty() && !sContraseña.isEmpty()) {

            SQLiteDatabase db = sohCuenta.getReadableDatabase();

            String sql = "SELECT identificacioncliente FROM cliente WHERE identificacioncliente = ' " + sIdentificacioncliente + " ' ";
            Cursor cursorCliente = db.rawQuery(sql, null);

            if (!cursorCliente.moveToFirst()) {

                SQLiteDatabase dbw = sohCuenta.getReadableDatabase();

                ContentValues cvCliente = new ContentValues();

                cvCliente.put("identificacioncliente", sIdentificacioncliente);
                cvCliente.put("nombrecompleto", sNombrecompleto);
                cvCliente.put("correoelectronico", sCorreoelectronico);
                cvCliente.put("contraseña", sContraseña);
                cvCliente.put("totalsaldo", 0);

                // guardar el registro  en la tabla cliente
                dbw.insert("cliente", null, cvCliente);
                dbw.close();
                db.close();

                Toast.makeText(getApplicationContext(),"CLIENTE GUARDADO CON EXITO!!", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(getApplicationContext(),"IDENTIFICACION EXISTENTE!!", Toast.LENGTH_SHORT).show();

            }

        } else {

            Toast.makeText(getApplicationContext(),"INGRESE TODOS LOS CAMPOS!!", Toast.LENGTH_SHORT).show();

        }

    }

}