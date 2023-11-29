package com.example.register;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
/* Aun no usadas
import android.app.DownloadManager;
import android.os.Environment;
import android.content.Context;*/

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Pagina_principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal);
    }

    // Método para abrir la actividad "ExeActivity" al hacer clic en un botón
    public void openejercicios(View view) {
        Intent intent = new Intent(this, ExeActivity.class); // Crea un Intent para la actividad ExeActivity
        startActivity(intent); // Inicia la actividad ExeActivity
    }

    public void closes(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Cerrar sesión")
                .setMessage("¿Estás seguro de que quieres cerrar la sesión?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Pagina_principal.this, login.class);
                        startActivity(intent);
                        FirebaseAuth.getInstance().signOut();
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void openfaqs(View view) {
        Intent intent = new Intent(this, faqs.class);
        startActivity(intent);
    }


    public void abrirobje(View view) {
        Intent intent = new Intent(this, activity_obje.class);
        startActivity(intent); // Inicia la actividad ObjeActivity
    }

    // Método para abrir la actividad "ObjeActivity" al hacer clic en un botón
    public void abriperfil(View view) {
        Intent intent = new Intent(this, activity_perfil.class);
        startActivity(intent); // Inicia la actividad PerfilActivity
    }

    public void abrirmanual(View view) {
        String url = "https://www.mediafire.com/file/j7meaymmgn1ipew/Manual+de+usuario+app+Gymnavi.pdf/file";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}