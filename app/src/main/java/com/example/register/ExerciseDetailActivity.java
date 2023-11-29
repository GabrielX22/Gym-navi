package com.example.register;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExerciseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);

        // Obtiene el nombre del ejercicio de la actividad anterior
        String exerciseName = getIntent().getStringExtra("exerciseName");
        // Configura el título de la actividad con el nombre del ejercicio
        setTitle(exerciseName);

        // Inicializa la vista del texto de detalles del ejercicio
        TextView textViewExerciseDetail = findViewById(R.id.textViewExerciseDetail);
        // Configura el texto con los detalles del ejercicio
        textViewExerciseDetail.setText("Detalles del ejercicio: " + exerciseName);

        // Inicializa la vista de la imagen del ejercicio
        ImageView imageViewExercise = findViewById(R.id.imageViewExercise);
        // Obtiene el recurso de imagen correspondiente al ejercicio
        int imageResourceId = getExerciseImageResource(exerciseName);
        // Configura la imagen del ejercicio
        imageViewExercise.setImageResource(imageResourceId);
    }

    // Método para obtener el recurso de imagen según el nombre del ejercicio
    private int getExerciseImageResource(String exerciseName) {
        // Lógica para obtener el recurso de imagen según el nombre del ejercicio
        if (exerciseName.equals("Press de banca")) {
            return R.drawable.presbanco;
        } else if (exerciseName.equals("Aperturas")) {
            return R.drawable.aper;
        } else if (exerciseName.equals("Fondos en paralelas")) {
            return R.drawable.fondos;
        } else if (exerciseName.equals("Press de banca inclinado")) {
            return R.drawable.bancaincli;
        } else if (exerciseName.equals("Press de banca declinado")) {
            return R.drawable.bancadecli;
        } else if (exerciseName.equals("Dominadas")) {
            return R.drawable.dominadas;
        } else if (exerciseName.equals("Remo con barra")) {
            return R.drawable.remos;
        } else if (exerciseName.equals("Pull-ups")) {
            return R.drawable.pull;
        } else if (exerciseName.equals("Remo en polea baja")) {
            return R.drawable.remobaja;
        } else if (exerciseName.equals("Peso muerto")) {
            return R.drawable.pesomuerto;
        } else if (exerciseName.equals("Curl de Bíceps con Barra")) {
            return R.drawable.curlbarra;
        } else if (exerciseName.equals("Curl de Bíceps con Mancuernas")) {
            return R.drawable.curlman;
        } else if (exerciseName.equals("Curl de Martillo con Mancuernas")) {
            return R.drawable.martillo;
        } else if (exerciseName.equals("Curl de bíceps en polea baja")) {
            return R.drawable.curlbajo;
        } else if (exerciseName.equals("Curl de bíceps en banco inclinado")) {
            return R.drawable.curlinclinado;
        } else if (exerciseName.equals("Fondos en Paralelas")) {
            return R.drawable.fondos;
        } else if (exerciseName.equals("Press de Tríceps con Barra")) {
            return R.drawable.presstri;
        } else if (exerciseName.equals("Lazo para Triceps")) {
            return R.drawable.extriceps;
        } else if (exerciseName.equals("Press francés")) {
            return R.drawable.pressfran;
        } else if (exerciseName.equals("Patada de tríceps con mancuerna")) {
            return R.drawable.patadatricep;
        } else if (exerciseName.equals("Sentadillas")) {
            return R.drawable.senta;
        } else if (exerciseName.equals("Desplantes")) {
            return R.drawable.desplan;
        } else if (exerciseName.equals("Prensa de Piernas")) {
            return R.drawable.prensa;
        } else if (exerciseName.equals("Prensa de Piernas")) {
            return R.drawable.prensa;
        } else if (exerciseName.equals("Extensiones de cuádriceps en máquina")) {
            return R.drawable.excuadri;
        } else if (exerciseName.equals("Curl femoral en máquina")) {
            return R.drawable.curlfemo;
        } else if (exerciseName.equals("Press militar")) {
            return R.drawable.pressmi;
        } else if (exerciseName.equals("Remo de deltoides traseros")) {
            return R.drawable.remodel;
        } else if (exerciseName.equals("Elevaciones laterales")) {
            return R.drawable.elevala;
        } else if (exerciseName.equals("Elevaciones laterales en inclinación alta")) {
            return R.drawable.elevaalta;
        } else if (exerciseName.equals("Remo al cuello")) {
            return R.drawable.remocuello;
        } else if (exerciseName.equals("Encogimientos de hombros")) {
            return R.drawable.encogi;
        } else if (exerciseName.equals("Crunch abdominal")) {
            return R.drawable.crunch;
        } else if (exerciseName.equals("Puente")) {
            return R.drawable.puente;
        } else if (exerciseName.equals("Giros Sentado")) {
            return R.drawable.giros;
        } else
            return R.drawable.default_image;// Imagen por defecto si no coincide ningún ejercicio
    }

    // Método para cerrar la actividad actual
    public void cerraractividad(View view) {
        finish();
    }
}