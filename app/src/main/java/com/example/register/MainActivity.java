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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.GoogleAuthProvider;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 1;
    String TAG = "GoogleSignIn";
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private ImageButton btnSignIn;

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
        btnSignIn = findViewById(R.id.imagegugol1);

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
                            Intent intent = new Intent(MainActivity.this, Pagina_principal.class);
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

    public void abrirgoogler(View view) {
        Toast.makeText(MainActivity.this, "Esta función estará pronto en funcionamiento", Toast.LENGTH_SHORT).show();
    }

    public void abrirfacer(View view) {
        Toast.makeText(MainActivity.this, "Esta función estará pronto en funcionamiento", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        mAuth.addAuthStateListener(mAuthStateListener);
        super.onStart();
    }

    }
