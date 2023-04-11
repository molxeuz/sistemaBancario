package com.myapp.sistemabancario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity_inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_inicio);

    // declaracion de datos
        EditText usuario, contraseñainicio;
        Button ingresar;

    // declaracion de variables
        usuario = findViewById(R.id.etusuario);
        contraseñainicio = findViewById(R.id.etcontraseñainicio);
        ingresar = findViewById(R.id.btningresar);

    // boton ingresar
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Usuario = usuario.getText().toString();
                String Contraseña = contraseñainicio.getText().toString();

                if (Usuario.equals("user") && Contraseña.equals("12345")) {

                    Toast.makeText(getApplicationContext(),"BIENVENIDO!!",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    startActivity(intent);

                } else {

                    Toast.makeText(getApplicationContext(),"DATOS INCORRECTOS!!",Toast.LENGTH_SHORT).show();

                }

            }

        });

    }

}