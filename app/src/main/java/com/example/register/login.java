package com.example.register;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        EditText emailEditText = findViewById(R.id.correo);
        EditText passwordEditText = findViewById(R.id.contraseña);
        TextView registro = findViewById(R.id.Registro);
        Button loginButton = findViewById(R.id.iniciosesion);
        CheckBox mostrarcontra = findViewById(R.id.checkBoxShowPassword);


        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, MainActivity.class);
                startActivity(intent);
                finish(); // Opcional, para cerrar la actividad de inicio de sesión si no se desea volver a ella
            }
        });

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

        loginButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(login.this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Inicio de sesión exitoso, redirige a la siguiente actividad
                                // Inicio de sesión exitoso, redirige a la siguiente actividad después del inicio de sesión
                                Intent intent = new Intent(login.this, Pagina_principal.class);
                                startActivity(intent);
                                finish(); // Opcional, para cerrar la actividad de inicio de sesión si no se desea volver a ella

                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user != null) {
                                    // Aquí puedes obtener el ID de usuario, etc.
                                    emailEditText.setText("");
                                    passwordEditText.setText("");
                                }
                            } else {
                                Toast.makeText(login.this, "Error al iniciar sesion, intenta de nuevo", Toast.LENGTH_SHORT).show();
                                // Fallo en el inicio de sesión, maneja los errores
                            }
                        });
            }

   });
}

    public void abrirgoogle(View view) {
        Toast.makeText(login.this, "Esta función estará pronto en funcionamiento", Toast.LENGTH_SHORT).show();
    }

    public void abrirface(View view) {
        Toast.makeText(login.this, "Esta función estará pronto en funcionamiento", Toast.LENGTH_SHORT).show();
    }

}
