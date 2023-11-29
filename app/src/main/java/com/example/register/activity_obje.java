package com.example.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class activity_obje extends AppCompatActivity {

    private Spinner spinnerFitnessGoals;
    private CheckBox checkBoxPeso, checkBoxMasaMuscular, checkBoxResistencia;
    private Button btnSaveGoals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obje);
        // Inicializa las vistas (UI elements) desde el diseño XML
        spinnerFitnessGoals = findViewById(R.id.spinnerFitnessGoals);
        checkBoxPeso = findViewById(R.id.checkboxpeso);
        checkBoxMasaMuscular = findViewById(R.id.checkboxmasamuscular);
        checkBoxResistencia = findViewById(R.id.checkboxresistencia);
        btnSaveGoals = findViewById(R.id.btnSaveGoals);

        // Configura un OnClickListener para el botón btnSaveGoals
        btnSaveGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> selectedGoals = new ArrayList<>();

                // Verifica si las casillas de verificación están marcadas y agrega los objetivos seleccionados a la lista
                if (checkBoxPeso.isChecked()) {
                    selectedGoals.add(checkBoxPeso.getText().toString());
                }

                if (checkBoxMasaMuscular.isChecked()) {
                    selectedGoals.add(checkBoxMasaMuscular.getText().toString());
                }

                if (checkBoxResistencia.isChecked()) {
                    selectedGoals.add(checkBoxResistencia.getText().toString());
                }

                // Puedes agregar más casillas de verificación y objetivos aquí

                // Muestra un mensaje Toast con los objetivos seleccionados o un mensaje de advertencia
                if (!selectedGoals.isEmpty()) {
                    String goalsText = "Objetivos seleccionados: " + String.join(", ", selectedGoals);
                    Toast.makeText(activity_obje.this, goalsText, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(activity_obje.this, "Selecciona al menos un objetivo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Método para regresar a la actividad anterior (Volver)
    public void Volver(View view) {
        finish(); // Cierra la actividad actual y vuelve a la actividad anterior
    }
}