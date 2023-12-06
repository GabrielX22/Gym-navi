package com.example.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 1;
    String TAG = "GoogleSignIn";

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private ImageButton btnSignIn;

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
        btnSignIn = findViewById(R.id.imagegugol);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null){ //si no es null redirigir
                    Intent intentp = new Intent(getApplicationContext(), Pagina_principal.class);
                    intentp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentp);
                }
            }
        };


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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Resultado devuelto al iniciar el Intent de GoogleSignInApi.getSignInIntent (...);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            if(task.isSuccessful()){
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                    firebaseAuthWithGoogle(account.getIdToken());
                } catch (ApiException e) {
                    // Google Sign In fallido, actualizar GUI
                    Log.w(TAG, "Google sign in failed", e);
                }
            }else{
                Log.d(TAG, "Error, login no exitoso:" + task.getException().toString());
                Toast.makeText(this, "Ocurrio un error. "+ task.getException().toString(),
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            //FirebaseUser user = mAuth.getCurrentUser();
                            //Iniciar DASHBOARD u otra actividad luego del SigIn Exitoso
                            Intent intent = new Intent(login.this, Pagina_principal.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }
                    }
                });
    }

    private void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void abrirgoogle(View view) {
        Toast.makeText(login.this, "Esta función estará pronto en funcionamiento", Toast.LENGTH_SHORT).show();
    }

    public void abrirface(View view) {
        Toast.makeText(login.this, "Esta función estará pronto en funcionamiento", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        mAuth.addAuthStateListener(mAuthStateListener);
        super.onStart();
    }

}
