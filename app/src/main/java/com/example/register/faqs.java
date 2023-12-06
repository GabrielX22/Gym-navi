package com.example.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class faqs extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);

        webView = findViewById(R.id.lafacus);
        // Configurar el WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Establecer el cliente WebView para gestionar las redirecciones dentro del WebView
        webView.setWebViewClient(new WebViewClient());

        // Establecer la URL inicial (puedes cambiar la URL por la de tu archivo HTML)
        webView.loadUrl("file:///android_asset/manualT.html");
    }

    /*public void elfacundo(View view){
        WebView myWebView = (WebView) findViewById(R.id.lafacus);
        myWebView.loadUrl("file:///android_asset/manualT.html");
    }*/
    public void cierrefaqs(View view) {
        finish();
    }
}