package com.example.quiz_app_equipoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Pantalla_Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);
    }

    //Metodo para cambiar a las Preferencias de Modalidad
    public void Modalidad (View v){
        Intent preferencias = new Intent(this, Pref_modalidad.class);
        startActivity(preferencias);
    }
}