package com.example.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ExeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exe);
    }
    // Método para regresar a la actividad anterior (Volver)
    public void vuelve(View view) {
        finish(); // Cierra la actividad actual y vuelve a la actividad anterior
    }

    // Método para abrir la actividad ExerciseListActivity al hacer clic en un elemento
    public void openExerciseList(View view) {
        // Crea un Intent para abrir la actividad ExerciseListActivity
        Intent intent = new Intent(this, ExerciseListActivity.class);
        // Obtiene el nombre del grupo muscular asociado al botón
        String groupName = view.getTag().toString();
        // Agrega el nombre del grupo muscular como dato extra al Intent
        intent.putExtra("groupName", groupName);
        // Inicia la actividad ExerciseListActivity
        startActivity(intent);
        finish(); // Cierra la actividad actual (opcional, dependiendo de tu flujo)
    }
}