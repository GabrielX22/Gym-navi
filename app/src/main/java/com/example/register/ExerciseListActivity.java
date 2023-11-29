package com.example.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ExerciseListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);

        // Inicializa las vistas (UI elements) desde el diseño XML
        TextView tvGroupTitle = findViewById(R.id.tvGroupTitle);
        ListView listViewExercises = findViewById(R.id.listViewExercises);

        // Obtiene el nombre del grupo muscular de la actividad anterior
        String groupName = getIntent().getStringExtra("groupName");
        // Configura el título del grupo muscular en la vista
        tvGroupTitle.setText("Ejercicios para: " + groupName);

        // Obtiene la lista de ejercicios para el grupo muscular
        ArrayList<String> exercises = getExercisesForGroup(groupName);

        // Crea un ArrayAdapter para la lista de ejercicios
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exercises);
        listViewExercises.setAdapter(adapter);

        // Configura el clic en elementos de la lista
        listViewExercises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedExercise = exercises.get(position);
                openExerciseDetail(selectedExercise); // Abre una nueva actividad
            }
        });
    }

    // Método para abrir otra actividad (MainActivity)
    public void openOtherActivity(View view) {
        Intent intent = new Intent(this, ExeActivity.class);
        startActivity(intent);
        finish();
    }

    // Método para obtener la lista de ejercicios según el grupo muscular
    private ArrayList<String> getExercisesForGroup(String groupName) {

        ArrayList<String> exercises = new ArrayList<>();
// Lógica para agregar ejercicios según el grupo muscular
        if (groupName.equals("Pecho")) {
            exercises.add("Press de banca");
            exercises.add("Aperturas");
            exercises.add("Fondos en paralelas");
            //Nuevos ejercicios
            exercises.add("Press de banca inclinado");
            exercises.add("Press de banca declinado");
        } else if (groupName.equals("Espalda")) {
            exercises.add("Dominadas");
            exercises.add("Remo con barra");
            exercises.add("Pull-ups");
            //Nuevos ejercicios
            exercises.add("Remo en polea baja");
            exercises.add("Peso muerto");
        } else if (groupName.equals("Biceps")) {
            exercises.add("Curl de Bíceps con Barra");
            exercises.add("Curl de Bíceps con Mancuernas");
            exercises.add("Curl de Martillo con Mancuernas");
            //Nuevos ejercicios
            exercises.add("Curl de bíceps en polea baja");
            exercises.add("Curl de bíceps en banco inclinado");
        } else if (groupName.equals("Triceps")) {
            exercises.add("Fondos en Paralelas");
            exercises.add("Press de Tríceps con Barra");
            exercises.add("Lazo para Triceps");
            //Nuevos ejercicios
            exercises.add("Press francés");
            exercises.add("Patada de tríceps con mancuerna");
        } else if (groupName.equals("Pierna")) {
            exercises.add("Sentadillas");
            exercises.add("Desplantes");
            exercises.add("Prensa de Piernas");
            //Nuevos ejercicios
            exercises.add("Extensiones de cuádriceps en máquina");
            exercises.add("Curl femoral en máquina");
        } else if (groupName.equals("Hombro")) {
            exercises.add("Press militar");
            exercises.add("Remo de deltoides traseros");
            exercises.add("Elevaciones laterales");
        } else if (groupName.equals("Trapecio")) {
            exercises.add("Elevaciones laterales en inclinación alta");
            exercises.add("Remo al cuello");
            exercises.add("Encogimientos de hombros");
        } else if (groupName.equals("Abdomen")) {
            exercises.add("Crunch abdominal");
            exercises.add("Puente");
            exercises.add("Giros Sentado");
        }



        return exercises;
    }

    // Método para abrir la actividad ExerciseDetailActivity con el nombre del ejercicio
    private void openExerciseDetail(String exerciseName) {
        Intent intent = new Intent(this, ExerciseDetailActivity.class);
        intent.putExtra("exerciseName", exerciseName);
        startActivity(intent);
    }
}