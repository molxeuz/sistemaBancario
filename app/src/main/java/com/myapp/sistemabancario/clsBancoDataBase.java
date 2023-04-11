package com.myapp.sistemabancario;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class clsBancoDataBase extends SQLiteOpenHelper {

    String tblCliente = "CREATE TABLE cliente (identificacioncliente integer primary key, nombrecompleto text, correoelectronico text, contraseña text, totalsaldo integer)";
    String tblCuenta = "CREATE TABLE cuenta (identificacioncuenta integer primary key, identificacioncliente integer, fechacuenta text, saldocuenta integer)";
    String tblTransaccion = "CREATE TABLE transaccion (identificaciontransaccion integer primary key autoincrement, identicacioncuenta integer,  fechatransaccion text, horatransaccion text, saldotransaccion integer)";

    public clsBancoDataBase (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super (context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(tblCliente);
        db.execSQL(tblCuenta);
        db.execSQL(tblTransaccion);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE cliente");
        db.execSQL(tblCliente);

        db.execSQL("DROP TABLE cuenta");
        db.execSQL(tblCuenta);

        db.execSQL("DROP TABLE transaccion");
        db.execSQL(tblTransaccion);

    }

}
