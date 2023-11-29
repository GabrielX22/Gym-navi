package com.example.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        // Obtener referencias a las vistas
        EditText nombreEditText = findViewById(R.id.Edtnombre);
        EditText emailEditText = findViewById(R.id.Edtemail);
        EditText passwordEditText = findViewById(R.id.EdtContra);
        Button registerButton = findViewById(R.id.btnregistro);
        TextView login = findViewById(R.id.logiar);
        CheckBox mostrarcontra = findViewById(R.id.checkBoxShowPassword2);

        mostrarcontra.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Para mostrar la contraseña, puedes usar null como método de transformación
                    passwordEditText.setTransformationMethod(null);
                } else {
                    // Para ocultar la contraseña, puedes usar PasswordTransformationMethod
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

                // Mueve el cursor al final del texto
                passwordEditText.setSelection(passwordEditText.getText().length());
            }
        });

        login.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);
            finish();
        });

        registerButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
            } else if (!email.contains("@")) {
                Toast.makeText(MainActivity.this, "El correo electrónico debe contener un '@'", Toast.LENGTH_SHORT).show();
            } else if (!email.matches(".*\\d.*")) {
                Toast.makeText(MainActivity.this, "El correo electrónico debe contener al menos un número", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 6) {
                Toast.makeText(MainActivity.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            } else {
                // Registrar al usuario en Firebase
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // El registro fue exitoso
                                // Puedes redirigir al usuario a la siguiente actividad o hacer lo que necesites
                                emailEditText.setText("");
                                passwordEditText.setText("");
                                Intent intent = new Intent(MainActivity.this, login.class);
                                startActivity(intent);
                                Toast.makeText(this, "Registro compleatado", Toast.LENGTH_SHORT).show();
                            } else {
                                // Hubo un error en el registro
                                Toast.makeText(this, "error fatal", Toast.LENGTH_SHORT).show();
                            }
                        });
            }

});
    }

    public void abrirgoogler(View view) {
        Toast.makeText(MainActivity.this, "Esta función estará pronto en funcionamiento", Toast.LENGTH_SHORT).show();
    }

    public void abrirfacer(View view) {
        Toast.makeText(MainActivity.this, "Esta función estará pronto en funcionamiento", Toast.LENGTH_SHORT).show();
    }

    }
