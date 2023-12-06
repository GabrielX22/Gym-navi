package com.example.register;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class manuteco extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manuteco);

        webView = findViewById(R.id.lafacus1);
        // Configurar el WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Establecer el cliente WebView para gestionar las redirecciones dentro del WebView
        webView.setWebViewClient(new WebViewClient());

        // Establecer la URL inicial (puedes cambiar la URL por la de tu archivo HTML)
        webView.loadUrl("file:///android_asset/PandU.html");
    }
}